package com.hangaebal.dao;

import com.hangaebal.vo.ImageTableVO;
import com.hangaebal.vo.MenuTableVO;
import com.hangaebal.vo.PostTableVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

	@CacheEvict(value = "menuListCache", allEntries = true)
	public void updateMenu(List<MenuTableVO> menuList) {
		for (MenuTableVO menuVO : menuList) {
			if (menuVO.getId() == null) {
				sqlSessionTemplate.insert("insertMenu", menuVO);
			} else {
				sqlSessionTemplate.insert("updateMenu", menuVO);
			}
		}
	}

	@CacheEvict(value = "menuListCache", allEntries = true)
	public void deleteMenu(Long id) {
		sqlSessionTemplate.update("deleteMenu", id);
	}

	public List<PostTableVO> selectMenuPostList(Long menuId) {
		return sqlSessionTemplate.selectList("selectMenuPostList", menuId);
	}

	public PostTableVO selectPostDetail(Long id) {
		return sqlSessionTemplate.selectOne("selectPostDetail", id);
	}

	public List<ImageTableVO> selectPostImageList(Long id) {
		return sqlSessionTemplate.selectList("selectPostImageList", id);
	}

	@CacheEvict(value = "postListCache", allEntries = true)
	public void updatePostSeq(PostTableVO postTableVO) {
		sqlSessionTemplate.update("updatePostSeq", postTableVO);
	}

	@CacheEvict(value = "postListCache", allEntries = true)
	public void insertPost(PostTableVO postTableVO) {
		sqlSessionTemplate.insert("insertPost", postTableVO);
	}

	@CacheEvict(value = "postListCache", allEntries = true)
	public void updatePost(PostTableVO postTableVO) {
		sqlSessionTemplate.update("updatePost", postTableVO);
	}

	@CacheEvict(value = "postListCache", allEntries = true)
	public void deletePost(Long id) {
		sqlSessionTemplate.update("deletePost", id);
	}

	public void insertImage(ImageTableVO imageTableVO) {
		sqlSessionTemplate.insert("insertImage", imageTableVO);
	}

	public void updateImage(ImageTableVO imageTableVO) {
		sqlSessionTemplate.update("updateImage", imageTableVO);
	}

	public void deleteImage(Long id) {
		sqlSessionTemplate.update("deleteImage", id);
	}

	public void deletePostImage(Long id) {
		sqlSessionTemplate.update("deletePostImage", id);
	}

}
