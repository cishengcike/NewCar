package com.smartamd.mapper;

import com.smartamd.model.Tcar;
import org.apache.ibatis.annotations.Param;

public interface TcarMapper {
    int deleteByPrimaryKey(Integer carid);

    int insert(Tcar record);
    int insertSelective(Tcar record);

    Tcar selectByPrimaryKey(Integer carid);

    int updateByPrimaryKeySelective(Tcar record);

    int updateByPrimaryKey(Tcar record);
    //htt
    String selectCarNameByCarId(@Param("TEL")String tel);
}