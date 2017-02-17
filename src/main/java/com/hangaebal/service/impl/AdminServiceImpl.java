package com.hangaebal.service.impl;

import com.hangaebal.dao.AdminDAO;
import com.hangaebal.service.AdminService;
import com.hangaebal.vo.MenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hcs on 2017. 2. 16..
 */
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO adminDAO;

	@Override
	public List<MenuVO> selectMenuList() {
		List<MenuVO> menuList = adminDAO.selectMenuList();
		return menuList;
	}

	@Override
	public void updateMenu(List<MenuVO> menuList) {
		adminDAO.updateMenu(menuList);
	}

	@Override
	public void deleteMenu(Long id) {
		adminDAO.deleteMenu(id);
	}
}
