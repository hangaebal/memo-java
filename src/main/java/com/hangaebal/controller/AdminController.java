package com.hangaebal.controller;

import com.hangaebal.service.AdminService;
import com.hangaebal.vo.ImageTableVO;
import com.hangaebal.vo.MenuVO;
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

		List<MenuVO> menuList = adminService.selectMenuList();

		ModelAndView mav = new ModelAndView("admin/menu");
		mav.addObject("menuList", menuList);

		return mav;
	}

	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public String updateMenu(
			@RequestParam("id") List<Long> idList
			,@RequestParam("title") List<String> titleList
			,@RequestParam("path") List<String> pathList
	) {
		List<MenuVO> menuList = new ArrayList<>();

		for (int i = 0; i < titleList.size(); i++) {
			MenuVO menuVo = new MenuVO();
			if (idList.size() > i) {
				menuVo.setId(idList.get(i));
			}
			menuVo.setSeq(i + 1L);
			menuVo.setTitle(titleList.get(i));
			menuVo.setPath(pathList.get(i));
			menuList.add(menuVo);
		}

		for (MenuVO menuVO : menuList) {
			System.out.println("id:"+menuVO.getId()+" seq:"+menuVO.getSeq()+" title:"+menuVO.getTitle()+" path:"+menuVO.getPath());
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
	public ModelAndView postList() {

		//List<MenuVO> postList = adminService.selectPostList();

		ModelAndView mav = new ModelAndView("admin/post/list");
		//mav.addObject("postList", postList);

		return mav;
	}

	@RequestMapping(value = "/post/create", method = RequestMethod.GET)
	public ModelAndView postCreate() {
		System.out.println(UPLOAD_DERECTORY);

		List<MenuVO> menuList = adminService.selectMenuList();

		ModelAndView mav = new ModelAndView("admin/post/create");
		mav.addObject("menuList", menuList);

		return mav;
	}

	@RequestMapping(value = "/post/image", method = RequestMethod.POST)
	public @ResponseBody Map imageUpload(
			@RequestParam("imgTitle") String imgTitle
			,@RequestParam("imgFile") MultipartFile imgFile
	) {
		Map<String, Object> returnMap = new HashMap<>();
		if (imgFile.isEmpty()) {
			returnMap.put("status","error");
			return returnMap;
		}

		String extension = imgFile.getOriginalFilename().substring(imgFile.getOriginalFilename().lastIndexOf("."));
		String saveFileName = UUID.randomUUID().toString().replace("-", "")  + extension;
		Path path = Paths.get(UPLOAD_DERECTORY + File.separator + "image" + File.separator + saveFileName);
		try {
			Files.write(path, imgFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			returnMap.put("status","error");
			return returnMap;
		}

		ImageTableVO imageTableVO = new ImageTableVO();
		imageTableVO.setTitle(imgTitle);
		imageTableVO.setPath("image" + File.separator + saveFileName);

		adminService.insertImage(imageTableVO);
		System.out.println("imageTableVO.getId() : " + imageTableVO.getId());

		returnMap.put("status", "success");
		returnMap.put("id", imageTableVO.getId());
		returnMap.put("path", imageTableVO.getPath());
		returnMap.put("title", imageTableVO.getTitle());



		return returnMap;
	}
}
