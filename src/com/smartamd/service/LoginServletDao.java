package com.smartamd.service;

import com.smartamd.mapper.LoginMapper;
import com.smartamd.mapper.TcarMapper;
import com.smartamd.mapper.TpositionMapper;
import com.smartamd.mapper.TuserMapper;
import com.smartamd.model.Tcar;
import com.smartamd.model.Tposition;
import com.smartamd.model.Tuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("loginServletDao")
@Transactional
public class LoginServletDao {

    @Autowired  TuserMapper  tuserMapper;
    //@Autowired  TusersMapper  tusersMapper;
    @Autowired  LoginMapper loginMapper;
    @Autowired  TpositionMapper tpositionMapper;
    // @Autowired  HislolaMapper hislolaMapper;
    @Autowired  TcarMapper tcar;
    //@Autowired  UsertypeMapper ut;


    //注册时，使用
    public int addUser( String userName,String pwd, String phone, int userType,String carType){
        int res=0;
        int temp1=0,temp2=0;
        try {
            Tuser tuser=new Tuser();
            tuser.setUsername(userName);
            tuser.setTel(phone);
            tuser.setUsertype(userType);
            tuser.setPassword(pwd);
            temp1= tuserMapper.insertSelective(tuser);//新用户已经插入Tuser表

            if(userType==0)
            {
                res=temp1;
            }
            else if(userType==1)//农机手，增加处理步骤
            {
                //temp2=loginMapper.updateCarType(phone);
                if(temp1==1 && temp2==1)
                {
                    res=1;
                }
            }

        }
        catch (Exception e) {
            return res;
        }
        return res;
    }
    public int alterUserInformationphone(int userId ,int userType ,String address){
        int res=0;
        try {
            Tuser tus=tuserMapper.selectByPrimaryKey(userId);
            tus.setUsertype(userType);
            tus.setAddress(address);
            res=tuserMapper.updateByPrimaryKey(tus);
        } catch (Exception e) {
            return res;
        }
        return res ;
    }
    //修改用户密码和用户名
    public int updateUserPwd(int userID ,String new_password ){
        int res=0;
        try {
            Tuser tus=tuserMapper.selectByPrimaryKey(userID);//根据id查找出登录用户
            tus.setPassword(new_password);
            res=tuserMapper.updateByPrimaryKey(tus);
        } catch (Exception e) {
            return res;
        }
        return res ;
    }
}
