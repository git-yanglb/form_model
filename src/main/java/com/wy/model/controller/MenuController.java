package com.wy.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

	@PostMapping("/menuList/edit")
	@ResponseBody
	public ResultMapper menuListEdit(@RequestParam("currentPage") int currentPage,
			@RequestParam("pageSize") int pageSize,
			@RequestParam(name = "menuName", required = false) String menuName) {
		System.out.println("pageSize = " + pageSize + "   currentPage = " + currentPage);
		Page<Menu> menuPage = menuService.getMenuPage(currentPage, pageSize,menuName);
		PageBean<Menu> pageInfo = new PageBean<Menu>(menuPage);
		ResultMapper result = new ResultMapper();
		result.setAck_code(ResultMapper.SUCCESS);
		result.setData(pageInfo);
		return result;
	}

	@GetMapping("/menuListPage")
	public String menuListPage() {
		return "/menu/menuList";
	}
}
