package com.hangaebal.service.impl;

import com.hangaebal.dao.SampleDAO;
import com.hangaebal.service.SampleService;
import com.hangaebal.vo.MenuTableVO_old;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hcs on 2017. 1. 6..
 */
@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	SampleDAO sampleDAO;

	@Override
	public List<MenuTableVO_old> selectMemoList() {
		return sampleDAO.selectMemoList();
	}
}
