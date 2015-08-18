package com.smartamd.mapper;

import com.smartamd.model.Tuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TuserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(Tuser record);

    int insertSelective(Tuser record);

    Tuser selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(Tuser record);

    int updateByPrimaryKey(Tuser record);

    //htt updateUserLoLa
    int updateUserLoLa(@Param("userID") Integer userID_param,
                       @Param("lo") String lo_param,@Param("la") String la_param);
    //htt queryMap
    //查询农机手
    List<Map<String,Object>> queryMapDriver(@Param("lo") String lo_param,@Param("la") String la_param,@Param("kmNumber") String kmNumber_param);
    //查询农户
    List<Map<String,Object>> queryMapFarmer(@Param("lo") String lo_param,@Param("la") String la_param,@Param("kmNumber") String kmNumber_param);
}
