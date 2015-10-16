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
//---------------------------htt修改---------------------------
    //htt updateUserLoLa
    int updateUserLoLa(@Param("userID") Integer userID_param,
                       @Param("lo") String lo_param, @Param("la") String la_param);
    //htt queryMap
    //查询农机手
    List<Map<String,Object>> queryMapDriver(@Param("lo") String lo_param, @Param("la") String la_param, @Param("kmNumber") String kmNumber_param);
    //查询农户
    List<Map<String,Object>> queryMapFarmer(@Param("lo") String lo_param, @Param("la") String la_param, @Param("kmNumber") String kmNumber_param);

    List<Map<String,Object>> queryUserByTel(@Param("userTel") String tel);
    int updatePassword(@Param("userID") int userID, @Param("new_password") String new_password);
    int deleteByTel(@Param("tel") String atel);

//---------------------------SteinbockA--------------------------
//通过对应的类型查农机手
List<Map<String,Object>> queryMapDriverByType(@Param("lo") String lo_param, @Param("la") String la_param, @Param("kmNumber") String kmNumber_param, @Param("carType") int carType);

    //通过手机号查找农机手
    Map<String,Object> queryMapDriverByPhone(@Param("lo") String lo_param, @Param("la") String la_param, @Param("phone") String phone);

    //通过手机号查找农户
    Map<String,Object> queryMapFarmerByPhone(@Param("lo") String lo_param, @Param("la") String la_param, @Param("phone") String phone);

    List<Map<String,Object>> queryMapAll(@Param("lo") String lo_param, @Param("la") String la_param, @Param("kmNumber") String kmNumber_param);

    Map<String,Object> queryMapAllByPhone(@Param("lo") String lo_param, @Param("la") String la_param, @Param("phone") String phone);


    int updateUser(@Param("phone") String phone, @Param("CID") String CID, @Param("lo") String lo, @Param("la") String la,@Param("gpsTime") String gpsTime);

    Map<String,Object> selectUserByPhone(@Param("opphone") String opphone, @Param("lo") String lo, @Param("la") String la);

    String selectUserTelByUserID(String userID);

    int updateCarType(@Param("type")int type,@Param("phone")String phone);

    String selectPhoneByUserID(Integer userID);

    //注册时如果是农机手先查询tcar表是否有对应手机号
    Map<String,Object> selectUserFromTcar(String phone);

    Map<String,Object> queryUserByPhone(String phone);

}