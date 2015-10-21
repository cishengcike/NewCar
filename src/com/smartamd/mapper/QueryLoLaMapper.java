package com.smartamd.mapper;

import com.smartamd.model.QueryLoLa;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QueryLoLaMapper {
     List<QueryLoLa> queryLoLa(@Param("carID") String carID,@Param("startTime")String startTime,@Param("lastTime")String lastTime);
     int queryCarID(String driver1tel);

}
