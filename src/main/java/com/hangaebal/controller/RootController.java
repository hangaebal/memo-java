package com.hangaebal.controller;

import com.hangaebal.service.AdminService;
import com.hangaebal.service.MainService;
import com.hangaebal.vo.ImageTableVO;
import com.hangaebal.vo.PostTableVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RootController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MainService mainService;

	@Autowired
	AdminService adminService;


	private final CacheManager cacheManager;

	public RootController(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@RequestMapping("/")
	public String index() {
		logger.info("\n\n" + "=========================================================\n"
				+ "Using cache manager: " + this.cacheManager.getClass().getName() + "\n"
				+ "=========================================================\n\n");

		return "home/index";
	}

	@RequestMapping("/post/{id}")
	public ModelAndView post(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView("home/post");

		PostTableVO post = adminService.selectPostDetail(id);
		mav.addObject("post", post);

		if (!"text".equals(post.getType())) {
			List<ImageTableVO> imageList = adminService.selectPostImageList(id);
			mav.addObject("imageList", imageList);
		}

		return mav;
	}

}
