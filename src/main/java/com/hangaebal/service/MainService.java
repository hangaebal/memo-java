package com.hangaebal.service;

import com.hangaebal.vo.MenuTableVO;
import com.hangaebal.vo.root.MainMenuVO;

import java.util.List;

public interface MainService {
	List<MainMenuVO> selectMainMenu();
	List<MenuTableVO> selectSubMenu();
}
