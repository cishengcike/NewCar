package com.smartamd.mapper;

import com.smartamd.model.TcarType;
import org.apache.ibatis.annotations.Param;

public interface TcarTypeMapper {
    int deleteByPrimaryKey(Integer typeid);

    int insert(TcarType record);

    int insertSelective(TcarType record);

    TcarType selectByPrimaryKey(Integer typeid);

    int updateByPrimaryKeySelective(TcarType record);

    int updateByPrimaryKey(TcarType record);
}