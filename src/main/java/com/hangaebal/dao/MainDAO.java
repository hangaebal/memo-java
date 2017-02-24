package com.hangaebal.dao;

import com.hangaebal.vo.PostTableVO;
import com.hangaebal.vo.dto.MenuDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MainDAO {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public List<MenuDTO> selectMenuList() {
		return sqlSessionTemplate.selectList("selectMainMenuList");
	}

	//@Cacheable(value = "mainMenuCache")
	public List<PostTableVO> selectPostList() {
		return sqlSessionTemplate.selectList("selectMainPostList");
	}


}
