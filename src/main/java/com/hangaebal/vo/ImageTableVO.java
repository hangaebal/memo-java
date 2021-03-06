package com.hangaebal.vo;

/**
 * Created by hcs on 2017. 2. 17..
 */
public class ImageTableVO {
	private Long id;
	private Long postId;
	private Long seq;
	private String title;
	private String path;
	private String delYn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}


	/**
	 * 썸네일 path
	 * @return
	 */
	public String getThumbPath() {
		String ext = path.substring(path.lastIndexOf('.'));
		String thumbPath = path.substring(0, path.lastIndexOf('.')) + "_thumb" + ext;
		return thumbPath;
	}
}
