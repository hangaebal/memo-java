package com.hangaebal.controller;

import com.hangaebal.service.MainService;
import com.hangaebal.service.SampleService;
import com.hangaebal.vo.MenuTableVO;
import com.hangaebal.vo.root.MainMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RootController {

	@Autowired
	SampleService sampleService;

	@Autowired
	MainService mainService;

	@RequestMapping("/")
	public ModelAndView index() {

		List<MainMenuVO> mainMenuList = mainService.selectMainMenu();
		List<MenuTableVO> subMenuList = mainService.selectSubMenu();

		ModelAndView mav = new ModelAndView("index");
		mav.addObject("mainMenuList", mainMenuList);
		mav.addObject("subMenuList", subMenuList);
		return mav;
	}

	@RequestMapping("/memoList")
	public ModelAndView memoList() {

		List<MenuTableVO> memoList = sampleService.selectMemoList();
		ModelAndView mav = new ModelAndView("memo");
		mav.addObject("memoList", memoList);
		return mav;
	}

}
