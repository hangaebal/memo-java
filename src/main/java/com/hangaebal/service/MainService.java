package com.hangaebal.service;

import com.hangaebal.vo.PostTableVO;
import com.hangaebal.vo.dto.MenuDTO;

import java.util.List;

public interface MainService {

	List<MenuDTO> selectMenuList();

	List<PostTableVO> selectPostList();
}
