package com.hangaebal.interceptor;

import com.hangaebal.service.MainService;
import com.hangaebal.vo.MenuTableVO;
import com.hangaebal.vo.root.MainMenuVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
		List<MenuTableVO> subMenuList = mainService.selectSubMenu();

		modelAndView.addObject("mainMenuList", mainMenuList);
		modelAndView.addObject("subMenuList", subMenuList);
	}*/
}
