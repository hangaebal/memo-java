package com.hangaebal.controller;

import com.hangaebal.service.AdminService;
import com.hangaebal.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hcs on 2017. 2. 16..
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

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

}
