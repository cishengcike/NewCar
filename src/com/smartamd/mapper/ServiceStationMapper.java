package com.smartamd.mapper;

import com.smartamd.model.ServiceStation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ServiceStationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ServiceStation record);

    int insertSelective(ServiceStation record);

    ServiceStation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ServiceStation record);

    int updateByPrimaryKey(ServiceStation record);

    List<Map<String,Object>> queryServiceStation(@Param("lo") String lo,@Param("la") String la,@Param("kmNumber")String kmNumber);

    List<Map<String,Object>> queryServiceStationByPhone(@Param("lo") String lo,@Param("la") String la,@Param("phone")String phone);
}