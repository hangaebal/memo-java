package com.hangaebal.service.impl;

import com.hangaebal.dao.AdminDAO;
import com.hangaebal.service.AdminService;
import com.hangaebal.vo.ImageTableVO;
import com.hangaebal.vo.MenuTableVO;
import com.hangaebal.vo.PostTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hcs on 2017. 2. 16..
 */
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO adminDAO;

	@Override
	public List<MenuTableVO> selectMenuList() {
		return adminDAO.selectMenuList();
	}

	@Override
	public void updateMenu(List<MenuTableVO> menuList) {
		adminDAO.updateMenu(menuList);
	}

	@Override
	public void deleteMenu(Long id) {
		adminDAO.deleteMenu(id);
	}

	@Override
	public List<PostTableVO> selectMenuPostList(Long menuId) {
		return adminDAO.selectMenuPostList(menuId);
	}

	@Override
	public PostTableVO selectPostDetail(Long id) {
		return adminDAO.selectPostDetail(id);
	}

	@Override
	public void updatePostSeq(PostTableVO postTableVO) {
		adminDAO.updatePostSeq(postTableVO);
	}

	@Override
	public void insertPost(PostTableVO postTableVO) {
		adminDAO.insertPost(postTableVO);
	}

	@Override
	public void updatePost(PostTableVO postTableVO) {
		adminDAO.updatePost(postTableVO);
	}

	@Override
	public void deletePost(Long id) {
		adminDAO.deletePost(id);
	}

	@Override
	public List<ImageTableVO> selectPostImageList(Long id) {
		return adminDAO.selectPostImageList(id);
	}

	@Override
	public void insertImage(ImageTableVO imageTableVO) {
		adminDAO.insertImage(imageTableVO);
	}

	@Override
	public void updateImage(ImageTableVO imageTableVO) {
		adminDAO.updateImage(imageTableVO);
	}

	@Override
	public void deleteImage(Long id) {
		adminDAO.deleteImage(id);
	}

	@Override
	public void deletePostImage(Long id) {
		adminDAO.deletePostImage(id);
	}
}
