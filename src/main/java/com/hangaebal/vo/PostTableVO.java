package com.hangaebal.vo;

/**
 * Created by hcs on 2017. 2. 22..
 */
public class PostTableVO {
	private Long id;
	private Long seq;
	private Long menuId;
	private String type;
	private String title;
	private String year;
	private String contents;
	private String delYn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	@Override
	public String toString() {
		return "PostTableVO{" +
				"id=" + id +
				", seq=" + seq +
				", menuId=" + menuId +
				", type='" + type + '\'' +
				", title='" + title + '\'' +
				", year='" + year + '\'' +
				", contents='" + contents + '\'' +
				", delYn='" + delYn + '\'' +
				'}';
	}
}
