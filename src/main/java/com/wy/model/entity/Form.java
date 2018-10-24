package com.wy.model.entity;

import java.util.Date;

public class Form {
	private Integer id;

	private String tableCname;

	private String tableEname;

	private Integer status;

	private Integer type;

	private String description;

	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTableCname() {
		return tableCname;
	}

	public void setTableCname(String tableCname) {
		this.tableCname = tableCname == null ? null : tableCname.trim();
	}

	public String getTableEname() {
		return tableEname;
	}

	public void setTableEname(String tableEname) {
		this.tableEname = tableEname == null ? null : tableEname.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}