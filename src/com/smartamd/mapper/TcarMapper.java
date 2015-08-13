package com.smartamd.mapper;

import com.smartamd.model.Tcar;

public interface TcarMapper {
    int deleteByPrimaryKey(Integer carid);

    int insert(Tcar record);

    int insertSelective(Tcar record);

    Tcar selectByPrimaryKey(Integer carid);

    int updateByPrimaryKeySelective(Tcar record);

    int updateByPrimaryKey(Tcar record);
}