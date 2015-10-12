package com.smartamd.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Aaron on 2015/7/30.
 */
public interface LoginMapper {
    List<Map<String, Object>> loginTrue(@Param("phone") String start, @Param("pwd") String end);

    // ����Ա����û�����
    void addUser(@Param("phone") String start, @Param("pwd") String end, @Param("uName") String userName, @Param("uType") String uType);

    // �ж��Ƿ��ֻ��ظ�
    List<Map<String, Object>> loginCommomPhone(@Param("phone") String start);

    // ?��????
    List<Map<String, Object>> loginCommomUser(@Param("carid") String start);

    // ��ʾ���������µ�����Ա��
    List<Map<String, Object>> queryLogsUsers(@Param("userid") int userid);

    // ��ʾ������nonjiojmԱ��
    List<Map<String, Object>> queryLogsAllUsers(@Param("userid") int userid);

    // ��ʾ���е��û�
    List<Map<String, Object>> queryAllLogUser();

    // ũ��ע����
    int usersNumber();

    // ũ����ע����
    int usersTwoNumber();

    // ����ũ����
    int workUserNumber();

    // ����ũ����
    int freeUserNumber();

    // ����ũ����
    int bugUserNumber();

    // ����ע����
    int CarNumber();

    /**
     * ��ѯ�û�id
     *
     * @return
     */
    int queryUserId();

//    /**
//     * ��ѯuid
//     *
//     * @return
//     */
//    int queryNumbers(@Param("nid") int userid);

    /**
     * ��ѯ����������Ĭ�����ĵ㾭γ��
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
     * ��ѯһ���û�������λ��
     *
     * @userid�û���id
     * @return
     */
    List<Map<String, Object>> queryMapUser(@Param("userid") String userid);

    /**
     ��ѯ�û���ʾ����ͼ���棨�������û����ͳ��ƺ���ģ�
     * @param username �û���
     * @param phone �绰����
     * @param carid ���ƺ���
     * @param kmnumber ���뷶Χ
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
     * ��ѯ�û���ʾ����ͼ���棨�������û����ͳ��ƺ���ģ�
     * @param username �û���
     * @param phone �绰����
     * @param carid ���ƺ���
     * @param kmnumber ���뷶Χ
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
//     * ��ѯũ�����ͣ������ݿ�û�ж�Ӧ��ɾ��
//     * @param carid
//     * @return
//     */
//    List<Map<String, Object>> queryNongHuType();

//    /**
//     * ��ѯũ�����ͣ������ݿ�û�ж�Ӧ��ɾ��
//     * @param carid
//     * @return
//     */
//    List<Map<String, Object>> queryNongHuTypea(@Param("id") String id);

    int queryMaxUserid();

//    /**
//     * ��ѯũ������
//     * @param carid
//     * @return
//     */
//    List<Map<String, Object>> queryUserTypeInfo(@Param("userid") String carid);

//    /**
//     * ��ѯũ������
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
    int updateCarType(@Param("phone") String phone, @Param("carType") Integer carType);

    //�������ĳ������Ʋ�ѯ���ݿ�õ����Ӧ������ID
    Integer queryCarType(String carType);

    //����ubantu�²������ݿ⺺���ܷ�������ʾ
    void insertTuser(String username);

}