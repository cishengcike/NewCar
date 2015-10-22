package com.smartamd.service;

import com.smartamd.mapper.LoginMapper;
import com.smartamd.mapper.TcarMapper;
import com.smartamd.mapper.TpositionMapper;
import com.smartamd.mapper.TuserMapper;
import com.smartamd.model.Tuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service("loginServletDao")
@Transactional
public class LoginServletDao {

    @Autowired
    TuserMapper tuserMapper;
    //@Autowired  TusersMapper  tusersMapper;
    @Autowired
    LoginMapper loginMapper;
    @Autowired
    TpositionMapper tpositionMapper;
    // @Autowired  HislolaMapper hislolaMapper;
    @Autowired
    TcarMapper tcar;
    //@Autowired  UsertypeMapper ut;


    //ע��ʱ��ʹ��
    public int addUser( String userName,String pwd, String phone, String userType,String carType){
        int res=0;
        int temp1=0,temp2=0;
        System.out.printf("ע��ʱuserType=%s,phone=%s,carType=%s\n", userType, phone, carType);
        try {
            Tuser tuser=new Tuser();
            tuser.setUsername(userName);
            tuser.setTel(phone);
            tuser.setUsertype(userType);
            tuser.setPassword(pwd);

            if("0".equals(userType))
            {
                System.out.println("userType==0");
                System.out.println(tuser.getUsername());
                temp1= tuserMapper.insertSelective(tuser);//���û��Ѿ�����Tuser��
                System.out.println("temp1="+temp1);
                res=temp1;
            }
            else if ("1".equals(userType))//ũ���֣����Ӵ�����
            {
                System.out.println("usertype==1");
                Map<String,Object> driver=tuserMapper.selectUserFromTcar(phone);
                System.out.println("driver="+driver);
                if(driver!=null) {
                    temp1 = tuserMapper.insertSelective(tuser);
                    temp2 = loginMapper.updateCarType(phone, (loginMapper.queryCarType(carType)));
                }
                else{
                    System.out.println("ũ����ע��û�г�");
                    temp1=tuserMapper.insertSelective(tuser);
                    temp2 = tuserMapper.insertTcarWhileRegister(phone);
                }
                System.out.println("temp1=" + temp1);
                System.out.println("temp2=" + temp2);


                if (temp1 == 1 && temp2 == 1) {
                    res = 1;
                }
            }
        }
        catch (Exception e) {
            System.out.println("exception");
            return res;
        }
        return res;
    }

    //�����û���Ϣ
    public int alterUserInformationPhone(int userId ,String userType ,String address,String carType){
        int res=0,res2;
        try {
            System.out.println("userid="+userId);
            Tuser tus=tuserMapper.selectByPrimaryKey(userId);
            if(!"����".equals(carType)){
                int car_type=loginMapper.queryCarType(carType);
                String phone = tuserMapper.selectPhoneByUserID(userId);
                res2 = tuserMapper.updateCarType(car_type,phone);
            }


            tus.setUsertype(userType);
            tus.setAddress(address);
            System.out.println(tus.getUsertype());
            res=tuserMapper.updateByPrimaryKey(tus);
        } catch (Exception e) {
            System.out.println("error");
            return res;
        }
        return res ;
    }

    //�޸��û�������û���
    public int updateUserPwd(int userID ,String new_password ){
        int res=0;
        try {
            Tuser tus=tuserMapper.selectByPrimaryKey(userID);//����id���ҳ���¼�û�
            tus.setPassword(new_password);
            res=tuserMapper.updateByPrimaryKey(tus);
        } catch (Exception e) {
            return res;
        }
        return res ;
    }


    public int  updateUserType(int userID, int userType)
    {
        int res=0;
        try
        {
            if (userType==0)//ũ��
            {
                Tuser tuser=tuserMapper.selectByPrimaryKey(userID);
            }
        }
        catch (Exception e)
        {
            return  res;
        }
        return res;
    }
}
