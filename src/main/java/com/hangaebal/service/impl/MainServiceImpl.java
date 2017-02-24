package com.hangaebal.service.impl;

import com.hangaebal.dao.MainDAO;
import com.hangaebal.service.MainService;
import com.hangaebal.vo.PostTableVO;
import com.hangaebal.vo.dto.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainServiceImpl implements MainService {

	@Autowired
	MainDAO mainDAO;

	@Override
	public List<MenuDTO> selectMenuList() {
		return mainDAO.selectMenuList();
	}

	@Override
	public List<PostTableVO> selectPostList() {
		return mainDAO.selectPostList();
	}
}
