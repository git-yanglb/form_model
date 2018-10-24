package com.wy.model.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.wy.model.entity.Menu;

public interface MenuService {

	public List<Menu> getMenuList();

	public Page<Menu> getMenuPage(int currentPage, int pageSize,String menuName);

	public Menu getMenuInfo(int id);

	public void updateMenu(Menu menu);

	public void saveMenu(Menu menu);

	public void deleteMenu(int id);

}
