package com.hangaebal.dao;

import com.hangaebal.vo.PostTableVO;
import com.hangaebal.vo.dto.MenuDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MainDAO {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Cacheable(value = "menuListCache")
	public List<MenuDTO> selectMenuList() {
		return sqlSessionTemplate.selectList("selectMainMenuList");
	}

	@Cacheable(value = "postListCache")
	public List<PostTableVO> selectPostList() {
		return sqlSessionTemplate.selectList("selectMainPostList");
	}


}
