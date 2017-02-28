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

	List<PostTableVO> selectMenuPostList(Long menuId);

	PostTableVO selectPostDetail(Long id);

	void updatePostSeq(PostTableVO postTableVO);

	void insertPost(PostTableVO postTableVO);

	void updatePost(PostTableVO postTableVO);

	void deletePost(Long id);

	List<ImageTableVO> selectPostImageList(Long id);

	void insertImage(ImageTableVO imageTableVO);

	void updateImage(ImageTableVO imageTableVO);

	void deleteImage(Long id);

	void deletePostImage(Long id);
}
