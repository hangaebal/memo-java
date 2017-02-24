package com.hangaebal.dao;

import com.hangaebal.vo.ImageTableVO;
import com.hangaebal.vo.MenuTableVO;
import com.hangaebal.vo.PostTableVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hcs on 2017. 2. 16..
 */
@Repository
public class AdminDAO {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public List<MenuTableVO> selectMenuList() {
		return sqlSessionTemplate.selectList("selectMenuList");
	}

	public void updateMenu(List<MenuTableVO> menuList) {
		for (MenuTableVO menuVO : menuList) {
			if (menuVO.getId() == null) {
				sqlSessionTemplate.insert("insertMenu", menuVO);
			} else {
				sqlSessionTemplate.insert("updateMenu", menuVO);
			}
		}
	}

	public void deleteMenu(Long id) {
		sqlSessionTemplate.update("deleteMenu", id);
	}

	public void insertImage(ImageTableVO imageTableVO) {
		sqlSessionTemplate.insert("insertImage", imageTableVO);
	}

	public void insertPost(PostTableVO postTableVO) {
		sqlSessionTemplate.insert("insertPost", postTableVO);
	}

	public void updateImage(ImageTableVO imageTableVO) {
		sqlSessionTemplate.update("updateImage", imageTableVO);
	}

	public List<PostTableVO> selectMenuPostList(Long menuId) {
		return sqlSessionTemplate.selectList("selectMenuPostList", menuId);
	}

	public void updatePostSeq(PostTableVO postTableVO) {
		sqlSessionTemplate.update("updatePostSeq", postTableVO);
	}

	public PostTableVO selectPostDetail(Long id) {
		return sqlSessionTemplate.selectOne("selectPostDetail", id);
	}

	public List<ImageTableVO> selectPostImageList(Long id) {
		return sqlSessionTemplate.selectList("selectPostImageList", id);
	}

	public void deleteImage(Long id) {
		sqlSessionTemplate.update("deleteImage", id);
	}

	public void deletePost(Long id) {
		sqlSessionTemplate.update("deletePost", id);
	}

	public void deletePostImage(Long id) {
		sqlSessionTemplate.update("deletePostImage", id);
	}

	public void updatePost(PostTableVO postTableVO) {
		sqlSessionTemplate.update("updatePost", postTableVO);
	}
}
