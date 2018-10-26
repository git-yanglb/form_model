package com.wy.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wy.model.entity.Form;
import com.wy.model.mapper.FormMapper;
import com.wy.model.service.FormService;

@Service("formService")
public class FormServiceImpl implements FormService {

	@Autowired
	private FormMapper formMapper;

	@Override
	public Page<Form> getFormList(String formName, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		return formMapper.getFormList(formName);
	}

	@Override
	public Form getFormInfo(Integer id) {
		return formMapper.selectByPrimaryKey(id);
	}

}
