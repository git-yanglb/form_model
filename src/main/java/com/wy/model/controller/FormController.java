package com.wy.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.wy.model.core.PageBean;
import com.wy.model.core.ResultMapper;
import com.wy.model.entity.Form;
import com.wy.model.service.FormService;

@Controller
public class FormController {

	@Autowired
	private FormService formService;

	@RequestMapping("/form/list")
	@ResponseBody
	public ResultMapper formList(@RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize,
			@RequestParam(name = "formName", required = false) String formName,
			@RequestParam(name = "menuName_", required = false) String menuName_) {
		Page<Form> forms = formService.getFormList(formName, currentPage, pageSize);
		PageBean<Form> pageInfo = new PageBean<Form>(forms);
		ResultMapper result = new ResultMapper();
		result.setAck_code(ResultMapper.SUCCESS);
		result.setData(pageInfo);
		return result;
	}

	@GetMapping("/form/edit/{id}")
	public String muenEdit(@PathVariable(name = "id", required = false) Integer id, ModelMap model) {
		Form form;
		if (id != -1) {
			form = formService.getFormInfo(id);
			model.addAttribute("formInfo", form);
		} else {
			form = new Form();
			form.setId(-1);
			model.addAttribute("formInfo", form);
		}
		return "/form/formEdit";
	}

}
