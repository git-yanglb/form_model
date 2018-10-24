package com.wy.model.entity;

import java.util.Date;

public class Menu {
	private Integer menuId;

	private String menuName;

	private String menuIcon;

	private String menuUrl;

	private Integer upMenuId;

	private Integer isUse;

	private Date createTime;

	private Integer orderNum;

	private Menu parent;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon == null ? null : menuIcon.trim();
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl == null ? null : menuUrl.trim();
	}

	public Integer getUpMenuId() {
		return upMenuId;
	}

	public void setUpMenuId(Integer upMenuId) {
		this.upMenuId = upMenuId;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", menuName=" + menuName + ", menuIcon=" + menuIcon + ", menuUrl=" + menuUrl
				+ ", upMenuId=" + upMenuId + ", isUse=" + isUse + ", createTime=" + createTime + ", orderNum="
				+ orderNum + "]";
	}

}