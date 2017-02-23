package com.hangaebal.interceptor;

import com.hangaebal.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Created by hcs on 2017. 1. 11..
 */
@Component
public class MenuInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MainService mainService;

	/*@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.info("============= postHandle ============");

		List<MainMenuVO> mainMenuList = mainService.selectMainMenu();
		List<MenuTableVO_old> subMenuList = mainService.selectSubMenu();

		modelAndView.addObject("mainMenuList", mainMenuList);
		modelAndView.addObject("subMenuList", subMenuList);
	}*/
}
