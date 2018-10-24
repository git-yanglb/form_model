package com.wy.model.mapper;

import com.github.pagehelper.Page;
import com.wy.model.entity.Form;

public interface FormMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Form record);

    int insertSelective(Form record);

    Form selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Form record);

    int updateByPrimaryKey(Form record);

    Page<Form> getFormList(String formName);
}