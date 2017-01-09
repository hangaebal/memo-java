package com.hangaebal.dao;

import com.hangaebal.vo.MenuTableVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SampleDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<MenuTableVO> selectMemoList() {
		return sqlSessionTemplate.selectList("selectMemoList");
	}
}
