package com.smartamd.mapper;

import com.smartamd.model.Tposition;

public interface TpositionMapper {
    int deleteByPrimaryKey(Integer positionid);

    int insert(Tposition record);

    int insertSelective(Tposition record);

    Tposition selectByPrimaryKey(Integer positionid);

    int updateByPrimaryKeySelective(Tposition record);

    int updateByPrimaryKey(Tposition record);
}