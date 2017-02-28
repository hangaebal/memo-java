package com.hangaebal.vo.dto;

import com.hangaebal.vo.MenuTableVO;

import java.io.Serializable;

/**
 * Created by hcs on 2017. 2. 23..
 */
public class MenuDTO extends MenuTableVO implements Serializable {

	private String hasYear;

	public String getHasYear() {
		return hasYear;
	}

	public void setHasYear(String hasYear) {
		this.hasYear = hasYear;
	}
}
