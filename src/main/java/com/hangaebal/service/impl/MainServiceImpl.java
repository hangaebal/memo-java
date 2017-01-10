package com.hangaebal.service.impl;

import com.hangaebal.dao.MainDAO;
import com.hangaebal.service.MainService;
import com.hangaebal.vo.MenuTableVO;
import com.hangaebal.vo.root.MainMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	MainDAO mainDAO;

	@Override
	public List<MainMenuVO> selectMainMenu() {
		return mainDAO.selectMainMenu();
	}

	@Override
	public List<MenuTableVO> selectSubMenu() {
		return mainDAO.selectSubMenu();
	}
}
