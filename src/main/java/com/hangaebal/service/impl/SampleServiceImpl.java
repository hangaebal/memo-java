package com.hangaebal.service.impl;

import com.hangaebal.dao.SampleDAO;
import com.hangaebal.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hcs on 2017. 1. 6..
 */
@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	SampleDAO sampleDAO;


}
