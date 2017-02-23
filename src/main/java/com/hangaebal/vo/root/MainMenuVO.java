package com.hangaebal.vo.root;

import com.hangaebal.vo.MenuTableVO_old;

import java.io.Serializable;

/**
 * Created by hcs on 2017. 1. 6..
 */
public class MainMenuVO extends MenuTableVO_old implements Serializable {

	private String hasShortcut;

	public String getHasShortcut() {
		return hasShortcut;
	}

	public void setHasShortcut(String hasShortcut) {
		this.hasShortcut = hasShortcut;
	}
}
