package com.wy.model.service;

import com.github.pagehelper.Page;
import com.wy.model.entity.Form;

public interface FormService {

	public Page<Form> getFormList(String formName, int currentPage, int pageSize);

	public Form getFormInfo(Integer id);

}
