package com.hangaebal.dao;

import com.hangaebal.vo.MenuTableVO_old;
import com.hangaebal.vo.root.MainMenuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MainDAO {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Cacheable(value = "mainMenuCache")
	public List<MainMenuVO> selectMainMenu() {
		return sqlSessionTemplate.selectList("selectMainMenu");
	}

	@Cacheable(value = "subMenuCache")
	public List<MenuTableVO_old> selectSubMenu() {
		return sqlSessionTemplate.selectList("selectSubMenu");
	}
}
