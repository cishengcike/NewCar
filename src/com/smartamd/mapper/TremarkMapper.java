package com.smartamd.mapper;

import com.smartamd.model.Tremark;

public interface TremarkMapper {
    int deleteByPrimaryKey(Integer remarkid);

    int insert(Tremark record);

    int insertSelective(Tremark record);

    Tremark selectByPrimaryKey(Integer remarkid);

    int updateByPrimaryKeySelective(Tremark record);

    int updateByPrimaryKey(Tremark record);
}