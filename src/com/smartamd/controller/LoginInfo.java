package com.smartamd.controller;

import java.util.List;
import java.util.Map;

import com.smartamd.mapper.LoginMapper;
import com.smartamd.service.LoginServletDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//用户登录
@Controller//用来标注当前类是springmvc的控制层类
public class LoginInfo {

    @Autowired//注入，完成自动装配，使用set，get方法
    private LoginMapper loginMapper;

    @Autowired
    private LoginServletDao loginServletDao;

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
                            ",'userId':"+ data.get(0).get("USERID") +
                            ",'userType':"+ data.get(0).get("TYPE") +
                            ",'userName':" + data.get(0).get("USERNAME") +
                            ",'mobile':"+ data.get(0).get("TEL") +
                            ",'password':"+ data.get(0).get("PASSWORD") +
                            "}");
        }
        else
        {
            response.getWriter().print("{'success':" + data.size() + "}");//登录用户失败则size（）=0
        }
    }

    //手机端注册
    @RequestMapping("addUserPhone.do")
    public void addUserPhone(HttpServletRequest request,
                             HttpServletResponse response, String phone, String pwd,
                             String userName, int userType,String carType)
            throws Exception {
        List<Map<String, Object>> dataa = loginMapper.loginCommomPhone(phone);
        //手机号已被注册
        if (dataa.size() > 0) {
            response.getWriter().print("{'success':3}");
            return;
        }
        int cou = loginServletDao.addUser(userName, pwd, phone, userType, carType);
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
                                   HttpServletResponse response, int userID, String new_password)
            throws Exception {
        int res = loginServletDao.updateUserPwd(userID, new_password);
        response.getWriter().print("{'success':" + res + "}");
    }
    //手机端完善用户信息
    @RequestMapping("alterUserInformationphone.do")
    public void alterUserInformationphone(HttpServletRequest request,
                                          HttpServletResponse response, int userId, int userType, String address)
            throws Exception {
        int res = loginServletDao.alterUserInformationphone(userId, userType, address);
        response.getWriter().print("{'success':" + res + "}");
    }

    @RequestMapping("test.do")
    public String printhello(){
        return "hello";
    }

    @RequestMapping("test2.do")
    public void test2(){
        System.out.println("hello");
    }

}