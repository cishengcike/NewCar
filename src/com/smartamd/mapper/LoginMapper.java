package com.smartamd.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Aaron on 2015/7/30.
 */
public interface LoginMapper {
    List<Map<String, Object>> loginTrue(@Param("phone") String start, @Param("pwd") String end);

    // 管理员添加用户操作
    void addUser(@Param("phone") String start, @Param("pwd") String end, @Param("uName") String userName, @Param("uType") String uType);

    // 判断是否手机重复
    List<Map<String, Object>> loginCommomPhone(@Param("phone") String start);

    // �ж��Ƿ�
    List<Map<String, Object>> loginCommomUser(@Param("carid") String start);

    // 显示车队下属下的所有员工
    List<Map<String, Object>> queryLogsUsers(@Param("userid") int userid);

    // 显示车所有nonjiojm员工
    List<Map<String, Object>> queryLogsAllUsers(@Param("userid") int userid);

    // 显示所有的用户
    List<Map<String, Object>> queryAllLogUser();

    // 农户注册数
    int usersNumber();

    // 农机手注册数
    int usersTwoNumber();

    // 工作农机数
    int workUserNumber();

    // 闲置农机数
    int freeUserNumber();

    // 故障农机数
    int bugUserNumber();

    // 车队注册数
    int CarNumber();

    /**
     * 查询用户id
     *
     * @return
     */
    int queryUserId();

//    /**
//     * 查询uid
//     *
//     * @return
//     */
//    int queryNumbers(@Param("nid") int userid);

    /**
     * 查询心跳数量，默认中心点经纬度
     *
     * @return
     */
    List<Map<String, Object>> queryNumbers();

    List<Map<String, Object>> queryMap(@Param("loo") String loo,
                                       @Param("lao") String lao, @Param("kmnumber") String kmnumber,
                                       @Param("typeid") String typeid,
                                       @Param("yeshu") int yeshu,
                                       @Param("yeNumber") int yeNumber, @Param("state") int state);

    /**
     * 查询一个用户的最新位置
     *
     * @userid用户的id
     * @return
     */
    List<Map<String, Object>> queryMapUser(@Param("userid") String userid);

    /**
     查询用户显示到地图上面（输入了用户名和车牌号码的）
     * @param username 用户名
     * @param phone 电话号码
     * @param carid 车牌号码
     * @param kmnumber 距离范围
     * @return
     */
    List<Map<String, Object>> queryMapUserName(@Param("loo") String loo,
                                               @Param("lao") String lao,
                                               @Param("username") String username, @Param("phone") String phone,
                                               //@Param("carid") String carid,
                                               @Param("kmnumber") String kmnumber, @Param("typeid") String typeid
                                               ,
                                               @Param("yeshu") int yeshu,
                                               @Param("yeNumber") int yeNumber, @Param("state") int state);

    /**
     * 查询用户显示到地图上面（输入了用户名和车牌号码的）
     * @param username 用户名
     * @param phone 电话号码
     * @param carid 车牌号码
     * @param kmnumber 距离范围
     * @return
     */
    List<Map<String, Object>> queryMapUserNamea(@Param("loo") String loo,
                                                @Param("lao") String lao,
                                                @Param("username") String username, @Param("phone") String phone,
                                                //@Param("carid") String carid,
                                                @Param("kmnumber") String kmnumber, @Param("typeid") String typeid
                                                , @Param("state") int state);


    List<Map<String, Object>> queryMapa(@Param("loo") String loo,
                                        @Param("lao") String lao, @Param("kmnumber") String kmnumber,
                                        @Param("typeid") String typeid, @Param("state") int state);


    String queryMapUseraa(@Param("userid") int username);


    List<Map<String, Object>> queryMapaCar(@Param("loo") String loo,
                                           @Param("lao") String lao, @Param("kmnumber") String kmnumber,
                                           @Param("phone") String phone, @Param("carid") String carid,
                                           @Param("yeshu") int yeshu, @Param("yeNumber") int yeNumber);


    List<Map<String, Object>> queryMapCarPhoneNumber(@Param("loo") String loo,
                                                     @Param("lao") String lao, @Param("kmnumber") String kmnumber,
                                                     @Param("phone") String phone, @Param("carid") String carid);


    List<Map<String, Object>> queryCarInfo();

    List<Map<String, Object>> queryCarTypeInfo();

    List<Map<String, Object>> queryCarTypeInfoid(@Param("id") String carid);

//    /**
//     * 查询农户类型，新数据库没有对应表，删除
//     * @param carid
//     * @return
//     */
//    List<Map<String, Object>> queryNongHuType();

//    /**
//     * 查询农户类型，新数据库没有对应表，删除
//     * @param carid
//     * @return
//     */
//    List<Map<String, Object>> queryNongHuTypea(@Param("id") String id);

    int queryMaxUserid();

//    /**
//     * 查询农户类型
//     * @param carid
//     * @return
//     */
//    List<Map<String, Object>> queryUserTypeInfo(@Param("userid") String carid);

//    /**
//     * 查询农户类型
//     * @param carid
//     * @return
//     */
//    List<Map<String, Object>> queryUserTypeInfoA(@Param("userid") String carid);
//
//
//    List<Map<String, Object>> queryUserTypeID(@Param("usertypeid") int carid);

//    List<Map<String, Object>> queryCaridForId(@Param("id") int id);

    int queryUpdatePwdAA(@Param("phone") String id);

    //int updateCarType(Map<String,String> map);
    int updateCarType(@Param("phone") String phone,@Param("carType") String carType);

    //根传来的车辆名称查询数据库得到其对应的类型ID
    Integer queryCarType(String carType);

    //测试ubantu下插入数据库汉字能否正常显示
    void insertTuser(String username);

}