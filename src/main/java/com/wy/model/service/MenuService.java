package com.wy.model.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.wy.model.entity.Menu;

public interface MenuService {

	public List<Menu> getMenuList();

	public Page<Menu> getMenuPage(int currentPage, int pageSize,String menuName);

}
