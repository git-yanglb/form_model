package com.wy.model.mapper;

import java.util.List;

import com.github.pagehelper.Page;
import com.wy.model.entity.Menu;

public interface MenuMapper {

	int deleteByPrimaryKey(Integer muneId);

	int insert(Menu record);

	int insertSelective(Menu record);

	Menu selectByPrimaryKey(Integer muneId);

	int updateByPrimaryKeySelective(Menu record);

	int updateByPrimaryKey(Menu record);

	List<Menu> getMenuList();

	Page<Menu> getMenuPage(String menuName);

}