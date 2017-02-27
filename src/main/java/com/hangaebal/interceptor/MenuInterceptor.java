package com.hangaebal.interceptor;

import com.hangaebal.service.AdminService;
import com.hangaebal.service.MainService;
import com.hangaebal.vo.MenuTableVO;
import com.hangaebal.vo.PostTableVO;
import com.hangaebal.vo.dto.MenuDTO;
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

	@Autowired
	AdminService adminService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
		logger.info("============= postHandle ============");

		if (!request.getRequestURI().contains("admin") && mav != null) {
			List<MenuDTO> menuList = mainService.selectMenuList();
			List<PostTableVO> postList = mainService.selectPostList();

			mav.addObject("menuList", menuList);
			mav.addObject("postList", postList);
		}
	}

}
