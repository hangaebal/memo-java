package com.hangaebal.service;

import com.hangaebal.vo.ImageTableVO;
import com.hangaebal.vo.MenuTableVO;
import com.hangaebal.vo.PostTableVO;

import java.util.List;

/**
 * Created by hcs on 2017. 2. 16..
 */
public interface AdminService {

	List<MenuTableVO> selectMenuList();

	void updateMenu(List<MenuTableVO> menuList);

	void deleteMenu(Long id);

	void insertImage(ImageTableVO imageTableVO);

	void insertPost(PostTableVO postTableVO);

	void updateImage(ImageTableVO imageTableVO);

	List<PostTableVO> selectPostList(Long menuId);

	void updatePostSeq(PostTableVO postTableVO);

	PostTableVO selectPostDetail(Long id);

	List<ImageTableVO> selectPostImageList(Long id);

	void deleteImage(Long id);

	void deletePost(Long id);

	void deletePostImage(Long id);

	void updatePost(PostTableVO postTableVO);
}
