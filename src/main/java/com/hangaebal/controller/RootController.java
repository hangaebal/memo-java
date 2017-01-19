package com.hangaebal.controller;

import com.hangaebal.service.MainService;
import com.hangaebal.service.SampleService;
import com.hangaebal.vo.MenuTableVO;
import com.hangaebal.vo.root.MainMenuVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
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

	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	SampleService sampleService;

	@Autowired
	MainService mainService;


	private final CacheManager cacheManager;

	public RootController(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@RequestMapping("/")
	public String index() {
		logger.info("\n\n" + "=========================================================\n"
				+ "Using cache manager: " + this.cacheManager.getClass().getName() + "\n"
				+ "=========================================================\n\n");

		return "home.index";
	}



	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "site.homepage";
	}

	@RequestMapping(value = "/greet", method = RequestMethod.GET)
	public ModelAndView greet(@RequestParam(value = "name", required = false, defaultValue = "World!") final String name, final Model model) {
		return new ModelAndView("site.greeting", "name", name);
	}

	@RequestMapping(value = "/greet/{name}", method = RequestMethod.GET)
	public ModelAndView greetTwoWays(@PathVariable(value = "name") final String name, final Model model) {
		return new ModelAndView("site.greeting", "name", name);
	}
}
