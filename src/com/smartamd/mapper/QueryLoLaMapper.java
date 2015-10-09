package com.smartamd.mapper;

import com.smartamd.model.QueryLoLa;

import java.util.List;

public interface QueryLoLaMapper {
     List<QueryLoLa> queryLoLa(Integer carID);
     int queryCarID(String driver1tel);

}
