package com.smartamd.mapper;

import com.smartamd.model.Tcar;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TcarMapper {
    int deleteByPrimaryKey(Integer carid);

    int insert(Tcar record);
    int insertSelective(Tcar record);

    Tcar selectByPrimaryKey(Integer carid);

    int updateByPrimaryKeySelective(Tcar record);

    int updateByPrimaryKey(Tcar record);
    //htt
    String selectCarNameByCarId(@Param("TEL")String tel);


    //  StreinbockA
    Map<String,Object> queryCarByTel(@Param("tel") String tel);

    List<Map<String,Object>> queryCar(@Param("lo") String lo,@Param("la")String la,@Param("kmNumber") String kmNumber);
}