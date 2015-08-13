package com.smartamd.mapper;

import com.smartamd.model.ServiceStation;

public interface ServiceStationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServiceStation record);

    int insertSelective(ServiceStation record);

    ServiceStation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServiceStation record);

    int updateByPrimaryKey(ServiceStation record);
}