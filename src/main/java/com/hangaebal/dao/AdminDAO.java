package com.hangaebal.dao;

import com.hangaebal.vo.ImageTableVO;
import com.hangaebal.vo.MenuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hcs on 2017. 2. 16..
 */
@Repository
public class AdminDAO {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public List<MenuVO> selectMenuList() {
		return sqlSessionTemplate.selectList("selectMenuList");
	}

	public void updateMenu(List<MenuVO> menuList) {
		for (MenuVO menuVO : menuList) {
			if (menuVO.getId() == null) {
				sqlSessionTemplate.insert("insertMenu", menuVO);
			} else {
				sqlSessionTemplate.insert("updateMenu", menuVO);
			}
		}
	}

	public void deleteMenu(Long id) {
		sqlSessionTemplate.update("deleteMenu", id);
	}

	public void insertImage(ImageTableVO imageTableVO) {
		sqlSessionTemplate.insert("insertImage", imageTableVO);
	}
}
