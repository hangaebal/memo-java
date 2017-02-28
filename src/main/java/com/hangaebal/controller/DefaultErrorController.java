package com.hangaebal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hcs on 2017. 2. 27..
 */
@Controller
public class DefaultErrorController implements ErrorController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/error")
	public String error(HttpServletRequest request, HttpServletResponse response) {
		return (404 == response.getStatus())?"home/404":"home/error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
