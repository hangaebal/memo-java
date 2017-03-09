package com.hangaebal.controller;

import com.hangaebal.service.AdminService;
import com.hangaebal.vo.ImageTableVO;
import com.hangaebal.vo.MenuTableVO;
import com.hangaebal.vo.PostTableVO;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by hcs on 2017. 2. 16..
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final int THUMBNAIL_MAX_SIZE = 700;

	@Value("${upload.directory}")
	String UPLOAD_DERECTORY;

	@Autowired
	AdminService adminService;

	@RequestMapping("/")
	public String index() {
		return "admin/index";
	}

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public ModelAndView selectMenuList() {

		ModelAndView mav = new ModelAndView("admin/menu");

		List<MenuTableVO> menuList = adminService.selectMenuList();
		mav.addObject("menuList", menuList);

		return mav;
	}

	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public String updateMenu(
			@RequestParam(value = "id", required = false) List<Long> idList
			,@RequestParam("title") List<String> titleList
	) {
		List<MenuTableVO> menuList = new ArrayList<>();

		for (int i = 0; i < titleList.size(); i++) {
			MenuTableVO menuVo = new MenuTableVO();
			if (idList != null && idList.size() > i) {
				menuVo.setId(idList.get(i));
			}
			menuVo.setSeq(i + 1L);
			menuVo.setTitle(titleList.get(i));
			menuList.add(menuVo);
		}

		adminService.updateMenu(menuList);

		return "redirect:/admin/menu";
	}

	@RequestMapping(value = "/menu/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteMenu(@PathVariable("id") Long id) {
		adminService.deleteMenu(id);
		return "success";
	}

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public ModelAndView selectPostList(@RequestParam(required = false) Long menuId) {

		ModelAndView mav = new ModelAndView("admin/post/list");

		List<MenuTableVO> menuList = adminService.selectMenuList();
		List<PostTableVO> postList = adminService.selectMenuPostList(menuId);

		mav.addObject("menuList", menuList);
		mav.addObject("postList", postList);

		return mav;
	}

	@RequestMapping(value = "/post/seq", method = RequestMethod.POST)
	@ResponseBody
	public String updatePostSeq(@RequestParam("id") List<Long> idList) {
		Long seq = 0L;
		PostTableVO postTableVO;
		for (Long id : idList) {
			postTableVO = new PostTableVO();
			postTableVO.setId(id);
			postTableVO.setSeq(++seq);

			adminService.updatePostSeq(postTableVO);
		}

		return "success";
	}

	@RequestMapping(value = "/post/create", method = RequestMethod.GET)
	public ModelAndView createPostView(@RequestParam(required = false) Long menuId) {

		ModelAndView mav = new ModelAndView("admin/post/create");

		List<MenuTableVO> menuList = adminService.selectMenuList();
		mav.addObject("menuList", menuList);
		mav.addObject("menuId", menuId);

		return mav;
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String createPost(
			@ModelAttribute PostTableVO postTableVO
			,@RequestParam(value = "imgId", required = false) List<Long> imgIdList
	) {
		// 포스트 등록
		adminService.insertPost(postTableVO);

		// 미디어 등록
		_updateImageList(imgIdList, postTableVO.getId());

		return "redirect:/admin/post?menuId=" + postTableVO.getMenuId();
	}

	@RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deletePost(@PathVariable("id") Long id) {
		PostTableVO postTableVO = adminService.selectPostDetail(id);
		adminService.deletePostImage(id);	// 포스트에 속한 이미지 삭제
		adminService.deletePost(id);		// 포스트 삭제

		// location.href 대상 주소
		return "admin/post?menuId=" + postTableVO.getMenuId();
	}

	@RequestMapping(value = "/post/edit/{id}", method = RequestMethod.GET)
	public ModelAndView updatePostView(@PathVariable("id") Long id) {

		ModelAndView mav = new ModelAndView("admin/post/edit");

		PostTableVO post = adminService.selectPostDetail(id);
		List<MenuTableVO> menuList = adminService.selectMenuList();

		mav.addObject("post", post);
		mav.addObject("menuList", menuList);

		if (!"text".equals(post.getType())) {
			List<ImageTableVO> imageList = adminService.selectPostImageList(id);
			mav.addObject("imageList", imageList);
		}

		return mav;
	}

	@RequestMapping(value = "/post/edit", method = RequestMethod.POST)
	public String updatePost(
			@ModelAttribute PostTableVO postTableVO
			,@RequestParam(value = "imgId", required = false) List<Long> imgIdList
	) {
		// 포스트 수정
		adminService.updatePost(postTableVO);

		// 미디어 등록
		_updateImageList(imgIdList, postTableVO.getId());

		return "redirect:/admin/post?menuId=" + postTableVO.getMenuId();
	}

	@RequestMapping(value = "/post/image", method = RequestMethod.POST)
	@ResponseBody
	public Map imageUpload(
			@RequestParam("type") String type
			,@RequestParam("mFile") MultipartFile multipartFile
			,@RequestParam(value = "imgTitle", required = false) String imgTitle
	) {
		Map<String, Object> returnMap = new HashMap<>();
		if (multipartFile.isEmpty()) {
			returnMap.put("status","error");
			return returnMap;
		}

		// ===== 파일 저장
		String randomId = UUID.randomUUID().toString().replace("-", "");
		String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")).toLowerCase();
		String newFileName = randomId + extension;
		String thumbFileName = randomId + "_thumb" + extension;
		String fullPath = UPLOAD_DERECTORY + File.separator + type + File.separator + newFileName;
		Path path = Paths.get(fullPath);
		try {
			Files.createDirectories(path.getParent());
			Files.write(path, multipartFile.getBytes());

			if ("image".equals(type)) {
				// ===== 썸네일 생성
				BufferedImage bufferedImage = ImageIO.read(new File(fullPath));
				double scale;
				int width = bufferedImage.getWidth();
				int height = bufferedImage.getHeight();

				// 가로 기준 리사이즈
				scale = Math.min(THUMBNAIL_MAX_SIZE, width) / (double) width;
				/*if (width >= height) {
					scale = Math.min(THUMBNAIL_MAX_SIZE, width) / (double) width;
				} else {
					scale = Math.min(THUMBNAIL_MAX_SIZE, height) / (double) height;
				}*/

				Thumbnails.of(fullPath)
						.size((int)(scale * width), (int)(scale * height))
						.toFile(UPLOAD_DERECTORY + File.separator + type + File.separator + thumbFileName);
			}
		} catch (IOException e) {
			e.printStackTrace();
			returnMap.put("status", "error");
			return returnMap;
		}

		// ===== DB insert
		ImageTableVO imageTableVO = new ImageTableVO();
		imageTableVO.setTitle(imgTitle);
		imageTableVO.setPath(type + "/" + newFileName);

		adminService.insertImage(imageTableVO);

		// ===== 미리보기 데이터 리턴
		returnMap.put("status", "success");
		returnMap.put("id", imageTableVO.getId());
		returnMap.put("path", imageTableVO.getPath());
		if ("image".equals(type)) {
			returnMap.put("thumbPath", type + "/" + thumbFileName);
			returnMap.put("title", imageTableVO.getTitle());
		}

		return returnMap;
	}

	@RequestMapping(value = "/post/image/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteImage(@PathVariable("id") Long id) {
		adminService.deleteImage(id);
		return "success";
	}

	public void _updateImageList(List<Long> imgIdList, Long postId) {
		if (imgIdList != null) {
			ImageTableVO imageTableVO;
			Long seq = 0L;
			for (Long imgId : imgIdList) {
				imageTableVO = new ImageTableVO();
				imageTableVO.setId(imgId);
				imageTableVO.setSeq(++seq);
				imageTableVO.setPostId(postId);

				adminService.updateImage(imageTableVO);
			}
		}
	}
}
