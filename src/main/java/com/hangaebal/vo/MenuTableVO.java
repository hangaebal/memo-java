package com.hangaebal.vo;

/**
 * Created by hcs on 2017. 1. 6..
 */
public class MenuTableVO {
	private Long id;
	private Long parentId;
	private Long seq;
	private String name;
	private String shortcut;
	private String url;
	private String lineYn;
	private String disableYn;
	private String delYn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLineYn() {
		return lineYn;
	}

	public void setLineYn(String lineYn) {
		this.lineYn = lineYn;
	}

	public String getDisableYn() {
		return disableYn;
	}

	public void setDisableYn(String disableYn) {
		this.disableYn = disableYn;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
}
