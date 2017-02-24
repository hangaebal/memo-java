package com.hangaebal.controller;

import com.hangaebal.service.AdminService;
import com.hangaebal.vo.ImageTableVO;
import com.hangaebal.vo.MenuTableVO;
import com.hangaebal.vo.PostTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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

	@Value("${upload.directory}")
	String UPLOAD_DERECTORY;

	@Autowired
	AdminService adminService;

	@RequestMapping("/")
	public String index() {
		return "admin/index";
	}

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public ModelAndView menuList() {

		ModelAndView mav = new ModelAndView("admin/menu");

		List<MenuTableVO> menuList = adminService.selectMenuList();
		mav.addObject("menuList", menuList);

		return mav;
	}

	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public String updateMenu(
			@RequestParam("id") List<Long> idList
			,@RequestParam("title") List<String> titleList
			,@RequestParam("path") List<String> pathList
	) {
		List<MenuTableVO> menuList = new ArrayList<>();

		for (int i = 0; i < titleList.size(); i++) {
			MenuTableVO menuVo = new MenuTableVO();
			if (idList.size() > i) {
				menuVo.setId(idList.get(i));
			}
			menuVo.setSeq(i + 1L);
			menuVo.setTitle(titleList.get(i));
			menuVo.setPath(pathList.get(i));
			menuList.add(menuVo);
		}

		adminService.updateMenu(menuList);

		return "redirect:/admin/menu";
	}

	@RequestMapping(value = "/menu/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteMenu(@PathVariable("id") Long id) {
		adminService.deleteMenu(id);
		return "success";
	}


	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public ModelAndView postList(@RequestParam(required = false) Long menuId) {

		ModelAndView mav = new ModelAndView("admin/post/list");

		List<MenuTableVO> menuList = adminService.selectMenuList();
		List<PostTableVO> postList = adminService.selectMenuPostList(menuId);

		mav.addObject("menuList", menuList);
		mav.addObject("postList", postList);

		return mav;
	}

	@RequestMapping(value = "/post/create", method = RequestMethod.GET)
	public ModelAndView postCreateView(@RequestParam(required = false) Long menuId) {

		ModelAndView mav = new ModelAndView("admin/post/create");

		List<MenuTableVO> menuList = adminService.selectMenuList();
		mav.addObject("menuList", menuList);
		mav.addObject("menuId", menuId);

		return mav;
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String postCreate(
			@ModelAttribute PostTableVO postTableVO
			,@RequestParam(value = "imgId", required = false) List<Long> imgIdList
	) {
		// 포스트 등록
		adminService.insertPost(postTableVO);

		// 미디어 등록
		if (imgIdList != null) {
			Long postId = postTableVO.getId();
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

		// 해당 포스트가 속한 메뉴의 포스트 리스트 페이지로 리다이렉트
		return "redirect:/admin/post?menuId=" + postTableVO.getMenuId();
	}

	@RequestMapping(value = "/post/image", method = RequestMethod.POST)
	public @ResponseBody Map imageUpload(
			@RequestParam("type") String type
			,@RequestParam("mFile") MultipartFile multipartFile
			,@RequestParam(value = "imgTitle", required = false) String imgTitle
	) {
		Map<String, Object> returnMap = new HashMap<>();
		if (multipartFile.isEmpty()) {
			returnMap.put("status","error");
			return returnMap;
		}

		String extension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
		String saveFileName = UUID.randomUUID().toString().replace("-", "")  + extension;
		Path path = Paths.get(UPLOAD_DERECTORY + File.separator + type + File.separator + saveFileName);
		try {
			Files.createDirectories(path.getParent());
			Files.write(path, multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			returnMap.put("status","error");
			return returnMap;
		}

		ImageTableVO imageTableVO = new ImageTableVO();
		imageTableVO.setTitle(imgTitle);
		imageTableVO.setPath(type + File.separator + saveFileName);

		adminService.insertImage(imageTableVO);

		returnMap.put("status", "success");
		returnMap.put("id", imageTableVO.getId());
		returnMap.put("path", imageTableVO.getPath());
		if ("image".equals(type)) {
			returnMap.put("title", imageTableVO.getTitle());
		}

		return returnMap;
	}

	@RequestMapping(value = "/post/image/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteImage(@PathVariable("id") Long id) {
		adminService.deleteImage(id);
		return "success";
	}

	@RequestMapping(value = "/post/seq", method = RequestMethod.POST)
	public @ResponseBody String postSeq(@RequestParam("id") List<Long> idList) {
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

	@RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deletePost(@PathVariable("id") Long id) {
		PostTableVO postTableVO = adminService.selectPostDetail(id);
		adminService.deletePostImage(id);	// 포스트에 속한 이미지 삭제
		adminService.deletePost(id);		// 포스트 삭제

		// 해당 포스트가 속한 메뉴의 포스트 리스트 페이지로 리다이렉트
		return "/admin/post?menuId=" + postTableVO.getMenuId();
	}

	@RequestMapping(value = "/post/edit/{id}", method = RequestMethod.GET)
	public ModelAndView postEditView(@PathVariable("id") Long id) {

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
	public String postEdit(
			@ModelAttribute PostTableVO postTableVO
			,@RequestParam(value = "imgId", required = false) List<Long> imgIdList
	) {
		// 포스트 수정
		adminService.updatePost(postTableVO);

		// 미디어 등록
		if (imgIdList != null) {
			Long postId = postTableVO.getId();
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

		// 해당 포스트가 속한 메뉴의 포스트 리스트 페이지로 리다이렉트
		return "redirect:/admin/post?menuId=" + postTableVO.getMenuId();
	}




}
