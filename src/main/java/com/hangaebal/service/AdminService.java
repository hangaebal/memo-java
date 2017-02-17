package com.hangaebal.service;

import com.hangaebal.vo.MenuVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hcs on 2017. 2. 16..
 */
public interface AdminService {

	List<MenuVO> selectMenuList();

	void updateMenu(List<MenuVO> menuList);

	void deleteMenu(Long id);
}
