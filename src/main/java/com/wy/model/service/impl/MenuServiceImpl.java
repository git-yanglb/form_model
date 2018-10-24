package com.wy.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wy.model.entity.Menu;
import com.wy.model.mapper.MenuMapper;
import com.wy.model.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> getMenuList() {
		return menuMapper.getMenuList();
	}

	@Override
	public Page<Menu> getMenuPage(int currentPage,int pageSize,String menuName) {
		PageHelper.startPage(currentPage,pageSize);
		return menuMapper.getMenuPage(menuName);
	}

	@Override
	public Menu getMenuInfo(int id) {
		return menuMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateMenu(Menu menu) {	
		menuMapper.updateByPrimaryKeySelective(menu);
	}

	@Override
	public void saveMenu(Menu menu) {
		menuMapper.insertSelective(menu);
	}

	@Override
	public void deleteMenu(int muneId) {
		menuMapper.deleteByPrimaryKey(muneId);
	}

	
}
