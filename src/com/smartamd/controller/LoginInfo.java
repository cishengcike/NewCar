package com.smartamd.controller;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.smartamd.mapper.LoginMapper;
import com.smartamd.mapper.TuserMapper;
import com.smartamd.model.Tuser;
import com.smartamd.service.LoginServletDao;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//用户登录
@Controller//用来标注当前类是springmvc的控制层类
public class LoginInfo {

    @Autowired//注入，完成自动装配，使用set，get方法
    private LoginMapper loginMapper;

    @Autowired
    private LoginServletDao loginServletDao;

    @Autowired
    private TuserMapper tuserMapper;

//    @Autowired
//    private UsertypeMapper usertypeMapper;
//
//    @Autowired
//    private TusersMapper  tusersMapper;


    //手机端登录
    @RequestMapping("loginInfoPhone.do")//手机端的登陆servlet
    public void loginInfoPhone(HttpServletRequest request,
                               HttpServletResponse response, String phone, String pwd)
            throws Exception {
        List<Map<String, Object>> data = loginMapper.loginTrue(phone, pwd);//取出指定电话和密码的用户信息
        if (data.size() > 0)
        {
            request.getSession().setAttribute("user", data.get(0));//用户的信息放入到id=user
            response.getWriter().print(
                    "{'success':" + data.size() +
                            ",'userID':" + data.get(0).get("USERID") +
                            ",'userType':" + data.get(0).get("TYPE") +
                            ",'userName':" + data.get(0).get("USERNAME") +
                            ",'mobile':" + data.get(0).get("TEL") +
                            ",'password':" + data.get(0).get("PASSWORD") +
                            "}");
        }
        else
        {
            response.getWriter().print("{'success':" + data.size() + "}");//登录用户失败则size（）=0
        }
    }

    //手机端注册
    @RequestMapping(value = "addUserPhone.do",method = RequestMethod.POST)
    public void addUserPhone(HttpServletRequest request,
                             HttpServletResponse response, String phone, String pwd,
                             String userName, String userType,String carType)
            throws Exception {
        System.out.println(userType+"  "+phone+" "+pwd+" "+userName+" "+carType);

        List<Map<String, Object>> dataa = loginMapper.loginCommomPhone(phone);
        //System.out.println(userType+phone+pwd+userName);
        //手机号已被注册
        if (dataa.size() > 0) {
            response.getWriter().print("{'success':3}");
            return;
        }
        int cou = loginServletDao.addUser(userName, pwd, phone, userType, carType);
        System.out.println("cou="+cou);
        if (cou > 0) {
            response.getWriter().print("{'success':1}");
            return;
        } else {
            response.getWriter().print("{'success':0}");
            return;
        }
    }
    //手机端修改密码
    @RequestMapping("alterPasswordPhone.do")//updateUserPwdPhone.do修改为alterPassword.do
    public void updateUserPwdPhone(HttpServletRequest request,
                                   HttpServletResponse response, String userID, String new_password)
            throws Exception {
        System.out.printf("%s  %S",userID,new_password);
        int res = loginServletDao.updateUserPwd(Integer.parseInt(userID), new_password);
        response.getWriter().print("{'success':" + res + "}");
    }
    //手机端完善用户信息
    @RequestMapping(value = "alterUserInformationPhone.do")
    public void alterUserInformationphone(HttpServletRequest request,
                                          HttpServletResponse response, String userID, String userType, String address)
            throws Exception {
        int res = loginServletDao.alterUserInformationPhone(Integer.parseInt(userID), userType, address);
        response.getWriter().print("{'success':" + res + "}");
    }


    @RequestMapping("queryMapNoddessUserPhone.do")
    public void  queryMapNoddessUserPhone(HttpServletRequest request,HttpServletResponse response,
                                          String userID,String userType,String kmNumber,String phone,String carType,String lo,String la,String flag)throws Exception {//flag--0:查询农机手，1：查询农户，2：查询全部，
        System.out.printf("userID=%s,userType=%s,kmNumber=%s,phone=%s,lo=%s,la=%s", userID, userType, kmNumber, phone, lo, la);
        //先区别是否为guest用户ut
        if (!userType.isEmpty()) {
            int userType_int = Integer.parseInt(userType);
            switch (userType_int) {
                case 0://农户
                    //更新Tuser表
                    tuserMapper.updateUserLoLa(Integer.parseInt(userID), lo, la);//修改用户的经纬度--------------填写
                    break;
                case 1://农机手
                    tuserMapper.updateUserLoLa(Integer.parseInt(userID), lo, la);//修改用户的经纬度
                    break;
                case -1://guest
                    break;
            }
        }
        List<Map<String, Object>> data = null;
        List<Map<String, Object>> data1 = null;
        List<Map<String, Object>> data2 = null;
        int flag_int = Integer.parseInt(flag);
        if (flag_int == 0)//查询农机手
        {
            data = tuserMapper.queryMapDriver(lo, la, kmNumber);//查询userType=1农机手
            response.getWriter().print("{'driver':" + data + "}");
            System.out.println("农户为="+data);
        } else if (flag_int == 1)//查询农户
        {
            data = tuserMapper.queryMapFarmer(lo, la, kmNumber);//查询userType=0农户
            response.getWriter().print("{'farmer':" + data + "}");
            System.out.println("农机手为=" + data);

        } else if (flag_int == 2)//查询全部--农户+农机手
        {
            data1 = tuserMapper.queryMapFarmer(lo, la, kmNumber);//查询userType=-1--全部农户+农机手//查询userType=0农户
            data2 = tuserMapper.queryMapDriver(lo, la, kmNumber);//查询userType=-1--全部农户+农机手//查询userType=1农机手
            response.getWriter().print("{'farmer':" + data1 +",'driver':"+data2 +"}");
            System.out.println("农户为：" + data1);
            System.out.println("农机手为：" + data2);

        }
    }

}