package com.hangaebal.dao;

import com.hangaebal.vo.MenuTableVO;
import com.hangaebal.vo.root.MainMenuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MainDAO {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public List<MainMenuVO> selectMainMenu() {
		return sqlSessionTemplate.selectList("selectMainMenu");
	}

	public List<MenuTableVO> selectSubMenu() {
		return sqlSessionTemplate.selectList("selectSubMenu");
	}
}
