package com.wy.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.Page;
import com.wy.model.core.PageBean;
import com.wy.model.core.ResultMapper;
import com.wy.model.entity.Menu;
import com.wy.model.service.MenuService;

@Controller
public class MenuController {

	@Autowired
	private MenuService menuService;

	@PostMapping("/menuList")
	@ResponseBody
	public String menuList() {
		List<Menu> menuList = menuService.getMenuList();
		return JSONObject.toJSONString(menuList, SerializerFeature.WriteMapNullValue);
	}

	@PostMapping("/menu/list")
	@ResponseBody
	public ResultMapper menuListEdit(@RequestParam("currentPage") int currentPage,
			@RequestParam("pageSize") int pageSize, @RequestParam(name = "menuName", required = false) String menuName,
			@RequestParam(name = "menuName_", required = false) String menuName_) {
		if (menuName == null || menuName.isEmpty()) {
			if (menuName_ != null) {
				menuName = menuName_;
			} else {
				menuName = "";
			}
		}
		Page<Menu> menuPage = menuService.getMenuPage(currentPage, pageSize, menuName);
		PageBean<Menu> pageInfo = new PageBean<Menu>(menuPage);
		ResultMapper result = new ResultMapper();
		result.setAck_code(ResultMapper.SUCCESS);
		result.setData(pageInfo);
		return result;
	}

	@GetMapping("/menu/edit/{id}")
	public String muenEdit(@PathVariable(name = "id", required = false) Integer id, ModelMap model) {

		Menu menu;
		if (id != -1) {
			menu = menuService.getMenuInfo(id);
			model.addAttribute("menuInfo", menu);
		} else {
			menu = new Menu();
			menu.setMenuId(-1);
			model.addAttribute("menuInfo", menu);
		}
		return "/menu/editMenu";
	}

	@PostMapping("/menu/updates/{id}")
	@ResponseBody
	public ResultMapper muenUpdate(@PathVariable("id") Integer id, Menu menu) {
		if (id == -1) {
			menu.setMenuId(null);
			menuService.saveMenu(menu);
		} else {
			menuService.updateMenu(menu);
		}
		ResultMapper result = new ResultMapper();
		return result;
	}

	@GetMapping("/menu/edit/{id}/parent")
	public String selectParent(@PathVariable("id") int id, ModelMap model) {
		Menu menu = menuService.getMenuInfo(id);
		model.addAttribute("menuInfo", menu);
		return "/menu/selectParent";
	}

	@PostMapping("/menu/delete/{id}")
	@ResponseBody
	public ResultMapper deleteMenu(@PathVariable("id") int id) {
		menuService.deleteMenu(id);
		return new ResultMapper();
	}

}
