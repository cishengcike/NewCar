package com.smartamd.controller;

import com.smartamd.mapper.LoginMapper;
import com.smartamd.mapper.QueryLoLaMapper;
import com.smartamd.mapper.TuserMapper;
import com.smartamd.model.QueryLoLa;
import com.smartamd.service.CIDResolver;
import com.smartamd.service.Distance;
import com.smartamd.service.LoginServletDao;
import com.smartamd.service.PushToList;
import com.smartamd.utils.JsonUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//用户登录
@Controller//用来标注当前类是springmvc的控制层类
public class LoginInfo {

    @Autowired//注入，完成自动装配，使用set，get方法
    private LoginMapper loginMapper;

    @Autowired
    private LoginServletDao loginServletDao;

    @Autowired
    private TuserMapper tuserMapper;

    @Autowired
    private QueryLoLaMapper queryLoLaMapper;

    private static Map<String, String> s_content = new HashMap<>();

    private int MAX_DISTANCE=5000;

//    @Autowired
//    private UsertypeMapper usertypeMapper;
//
//    @Autowired
//    private TusersMapper  tusersMapper;


    //手机端登录
    @RequestMapping("loginInfoPhone.do")//手机端的登陆servlet
    public void loginInfoPhone(HttpServletRequest request,
                               HttpServletResponse response, String phone, String pwd,String CID,String lo,String la)
            throws Exception {
        System.out.println("loginInfoPhone.do");
        System.out.println(lo+la);
        System.out.println(CID);
        tuserMapper.updateUser(phone, CID, lo, la);
        List<Map<String, Object>> data = loginMapper.loginTrue(phone, pwd);//取出指定电话和密码的用户信息
        if (data.size() > 0) {
            request.getSession().setAttribute("user", data.get(0));//用户的信息放入到id=user
            response.getWriter().print(
                    "{'success':" + data.size() +
                            ",'userID':" + data.get(0).get("USERID") +
                            ",'userType':" + data.get(0).get("TYPE") +
                            ",'userName':" + data.get(0).get("USERNAME") +
                            ",'mobile':" + data.get(0).get("TEL") +
                            ",'password':" + data.get(0).get("PASSWORD") +
                            "}");
        } else {
            response.getWriter().print("{'success':" + data.size() + "}");//登录用户失败则size（）=0
            response.getWriter().print("{'fail':" + "马琳" + "}");//登录用户失败则size（）=0
        }
    }


    //手机端注册
    @RequestMapping(value = "addUserPhone.do", method = RequestMethod.POST)
    public void addUserPhone(HttpServletRequest request,
                             HttpServletResponse response, String phone, String pwd,
                             String userName, String userType, String carType)
            throws Exception {
        System.out.println(userType + "  " + phone + " " + pwd + " " + userName + " " + carType);

        List<Map<String, Object>> dataa = loginMapper.loginCommomPhone(phone);
        //System.out.println(userType+phone+pwd+userName);
        //手机号已被注册
        if (dataa.size() > 0) {
            response.getWriter().print("{'success':3}");
            return;
        }
        int cou = loginServletDao.addUser(userName, pwd, phone, userType, carType);
        System.out.println("cou=" + cou);
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
        System.out.printf("%s  %S", userID, new_password);
        int res = loginServletDao.updateUserPwd(Integer.parseInt(userID), new_password);
        response.getWriter().print("{'success':" + res + "}");
    }

    //手机端完善用户信息
    @RequestMapping(value = "alterUserInformationPhone.do")
    public void alterUserInformationphone(HttpServletRequest request,
                                          HttpServletResponse response, String userID, String userType, String address, String carType)
            throws Exception {
        if("0".equals(userType)&&carType!=null)
            response.getWriter().print("{'success':" + 2 + "}");
        if(!"2".equals(userType)) {
            int res = loginServletDao.alterUserInformationPhone(Integer.parseInt(userID), userType, address, carType);
            response.getWriter().print("{'success':" + res + "}");}
    }

//    @RequestMapping("queryMapNoddessUserPhone.do")
//    public void queryMapNoddessUserPhone(String userID,String userType,String lo,String la,String kmNumber,String phone,String carType) {
//        System.out.println(userID + userType + lo + la + kmNumber+phone );
//    }

    @RequestMapping("queryMapNoddessUserPhone.do")
    public void queryMapNoddessUserPhone(HttpServletRequest request, HttpServletResponse response,
                                         String userID, String userType, String kmNumber, String phone, String carType, String lo, String la, String flag) throws Exception {//flag--0:查询农机手，1：查询农户，2：查询全部，
        System.out.println("url:queryMapNoddessUserPhone.do");
        System.out.printf("userID=%s,userType=%s,kmNumber=%s,phone=%s,lo=%s,la=%s,carType=%s,flag=%s\n", userID, userType, kmNumber, phone, lo, la, carType, flag);
        //先区别是否为guest用户ut
        if ("".equals(phone)) phone = null;
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
            if (phone == null) {
                if (carType == null || "-1".equals(carType)) {
                    data = tuserMapper.queryMapDriver(lo, la, kmNumber);
                    response.getWriter().print("{'driver':" + data + "}");
                    System.out.println("catType=null");
                    System.out.println(data);
                } else {
                    int type = loginMapper.queryCarType(carType);
                    data = tuserMapper.queryMapDriverByType(lo, la, kmNumber, type);
                    response.getWriter().print("{'driver':" + data + "}");
                    System.out.println("carType!=null");
                    System.out.println(data);
                }
            } else {
                Map<String, Object> map = tuserMapper.queryMapDriverByPhone(lo, la, phone);
                if ((int) map.get("USERTYPE") == 1)
                    response.getWriter().print("{'driver':[" + map + "]}");

            }
        } else if (flag_int == 1)//查询农户
        {
            if (phone == null) {

                data = tuserMapper.queryMapFarmer(lo, la, kmNumber);//查询userType=0农户
                response.getWriter().print("{'farmer':" + data + "}");
            } else {
                Map<String, Object> map = tuserMapper.queryMapFarmerByPhone(lo, la, phone);
                if ((int) map.get("USERTYPE") == 0)
                    response.getWriter().print("{'farmer':[" + map + "]}");
            }


        } else if (flag_int == 2)//查询全部--农户+农机手
        {
            System.out.println("查询农户和农机手");
            if (phone == null) {
                data1 = tuserMapper.queryMapFarmer(lo, la, kmNumber);//查询userType=-1--全部农户+农机手//查询userType=0农户
                data2 = tuserMapper.queryMapDriver(lo, la, kmNumber);//查询userType=-1--全部农户+农机手//查询userType=1农机手
                response.getWriter().print("{'farmer':" + data1 + ",'driver':" + data2 + "}");
            } else {
                System.out.println("phone不为空");
                Map<String, Object> map1 = tuserMapper.queryMapFarmerByPhone(lo, la, phone);
                Map<String, Object> map2 = tuserMapper.queryMapDriverByPhone(lo, la, phone);
                if (map1 != null && map2 == null)
                    response.getWriter().print("{'farmer':[" + map1 + "]," + "'driver':[{" + "}]}");
                if (map1 == null && map2 != null)
                    response.getWriter().print("{'driver':[" + map2 + "]," + "'farmer':[{" + "}]}");
            }

        }
    }

    @RequestMapping("push.do")
    public void push(String userID,String userName,String phone,String kmNumber,String lo,String la,String carType,String flag,String userType,String content){
        System.out.println("url:push.do");
        System.out.printf("userID=%s,username=%s,userType=%s,phone=%s,kmNumber=%s,lo=%s,la=%s,flag=%s,content=%s\n", userID, userName, userType, phone, kmNumber, lo, la, flag, content);

        s_content.put(phone, content);
        if ("".equals(phone)) phone = null;
        List<Map<String, Object>> data = null;
        List<Map<String, Object>> data1 = null;
        List<Map<String, Object>> data2 = null;
        List<String> cid_list=null;
        int flag_int = Integer.parseInt(flag);
        String push_phone = tuserMapper.selectUserTelByUserID(userID);
        if (flag_int == 0)//查询农机手
        {
            if (phone == null) {
                if (carType == null || "-1".equals(carType)) {
                    data = tuserMapper.queryMapDriver(lo, la, kmNumber);
//                    response.getWriter().print("{'driver':" + data + "}");
                    System.out.println("catType=null");
                    System.out.println(data);
                } else {
                    int type = loginMapper.queryCarType(carType);
                    data = tuserMapper.queryMapDriverByType(lo, la, kmNumber, type);
//                    response.getWriter().print("{'driver':" + data + "}");
                    System.out.println("carType!=null");
                    System.out.println(data);
                }

                cid_list= CIDResolver.getCIDList(data, userID);
                System.out.println("cid_list="+cid_list);
                System.out.println(userName);
                PushToList.PushToPhone(userName, userType, content, push_phone, cid_list);
            } else {
                Map<String, Object> map = tuserMapper.queryMapDriverByPhone(lo, la, phone);
//                if ((int) map.get("USERTYPE") == 1)
//                    response.getWriter().print("{'driver':[" + map + "]}");

                cid_list= CIDResolver.getCIDList(map);
                System.out.println("cid_list="+cid_list);
                PushToList.PushToPhone(userName, userType, content, push_phone, cid_list);


            }
        } else if (flag_int == 1)//查询农户
        {
            if (phone == null) {

                data = tuserMapper.queryMapFarmer(lo, la, kmNumber);//查询userType=0农户
//                response.getWriter().print("{'farmer':" + data + "}");
                cid_list= CIDResolver.getCIDList(data, userID);
                System.out.println("cid_list="+cid_list);
                PushToList.PushToPhone(userName, userType, content, push_phone, cid_list);

            } else {
                Map<String, Object> map = tuserMapper.queryMapFarmerByPhone(lo, la, phone);
                cid_list= CIDResolver.getCIDList(map);
                System.out.println("cid_list="+cid_list);
                PushToList.PushToPhone(userName, userType, content, push_phone, cid_list);

//                if ((int) map.get("USERTYPE") == 0)
//                    response.getWriter().print("{'farmer':[" + map + "]}");
            }


        } else if (flag_int == 2)//查询全部--农户+农机手
        {
            System.out.println("查询农户和农机手");
            if (phone == null) {
                System.out.println("查询所有");
                data = tuserMapper.queryMapAll(lo, la, kmNumber);//查询userType=-1--全部农户+农机手//查询userType=0农户
//                response.getWriter().print("{'farmer':" + data1 + ",'driver':" + data2 + "}");
                cid_list= CIDResolver.getCIDList(data, userID);
                System.out.println("cid_list="+cid_list);
                PushToList.PushToPhone(userName, userType, content, push_phone, cid_list);

            } else {
                System.out.println("phone不为空");
                Map<String, Object> map = tuserMapper.queryMapAllByPhone(lo, la, phone);
                cid_list= CIDResolver.getCIDList(map);
                System.out.println("cid_list="+cid_list);
                PushToList.PushToPhone(userName, userType, content, push_phone, cid_list);

//                if (map1 != null && map2 == null)
////                    response.getWriter().print("{'farmer':[" + map1 + "]," + "'driver':[{" + "}]}");
//                if (map1 == null && map2 != null)
////                    response.getWriter().print("{'driver':[" + map2 + "]," + "'farmer':[{" + "}]}");
            }

        }

        System.out.println("个推请求完成后存入的s_content为"+s_content);

    }

    @RequestMapping("queryUserDetailsPhone.do")
    public void queryUserDetailsPhone(HttpServletRequest request,HttpServletResponse response,String opphone,String lo,String la) throws Exception{
        System.out.println("url:qurtyUserDerailsPhone");
        System.out.printf("opphone=%s,lo=%s,la=%s\n", opphone, lo, la);
        System.out.println("个推请求后再次detail请求时的s_content为" + s_content);
        Map<String,Object> map=tuserMapper.selectUserByPhone(opphone,lo,la);
        System.out.println("content内容为："+s_content.get(opphone));
        map.put("CONTENT", s_content.get(opphone));
        System.out.println(map);
        response.getWriter().print("{'user':[" + map + "]}");

    }



    /**
     * 查询历史经纬度
     * @return
     */
    @RequestMapping(value = "historyRoute.do")
    public String historyRoute(String drivertel,Model model){
        int carID=queryLoLaMapper.queryCarID(drivertel);
        List<QueryLoLa> list =queryLoLaMapper.queryLoLa(carID);
        model.addAttribute("FIRST_LENGTH", list.size());

        int length=list.size();
        model.addAttribute("flo", list.get(length - 1).getLo());
        model.addAttribute("fla", list.get(length - 1).getLa());
        Distance distance=new Distance();
        int i=0;
        System.out.println("初始长度"+list.size());

        //两个坐标距离相差少于MAX_DISTANCE不再地图上显示
        while(i<length-1){
            double lo1,la1,lo2,la2;
            lo1=list.get(i).getLo();
            la1=list.get(i).getLa();
            lo2=list.get(i+1).getLo();
            la2=list.get(i+1).getLa();
            if(distance.getDistance(lo1,la1,lo2,la2)<MAX_DISTANCE){
                list.remove(i+1);
                length=length-1;
            }
            else i++;
        }
        for(QueryLoLa queryLoLa:list){
            System.out.println(queryLoLa.toString());
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        model.addAttribute("historyLoLa", jsonArray);
        model.addAttribute("FINAL_LENGTH", list.size());
        return "history";
    }

    /**
     * 跳转到历史轨迹页面
     * @return
     */
    @RequestMapping(value = "historyPage.do")
    public String historyPage() {
        return "historyPage";
    }





 //-----------------------------------------web端功能-----------------------------------------------------
    //web端登录
    @RequestMapping("loginInfo.do")
    public void loginInfo(HttpServletRequest request,
            HttpServletResponse response, String phone, String pwd)
            		throws Exception {
        System.out.println("url:loginInfo.do");
        System.out.printf("phone=%s,pwd=%s",phone,pwd);
        List<Map<String, Object>> data_phone=loginMapper.loginCommomPhone(phone);
        //System.out.println(data_phone.get(0));
        if(data_phone==null || data_phone.size()<=0){//手机号不存在时
 			response.getWriter().print(-5);
 			return;
 		}
    	 List<Map<String, Object>> data = loginMapper.loginTrue(phone, pwd);//取出指定电话和密码的用户信息
         if (data.size() > 0)
         {
             request.getSession().setAttribute("user", data.get(0));//用户的信息放入到id=user
             request.getSession().setAttribute("KMNUMBER","15");//初始化查询的默认公里数
             request.getSession().setAttribute("farmer","");
             request.getSession().setAttribute("driver","");
             request.getSession().setAttribute("MAPUSERTYPEQUERY", -1);
             response.getWriter().print(data.size() );
         }
         else
         {
             response.getWriter().print( data.size());//登录用户失败则size（）=0
         }
    	
    }
    //web注册
    @RequestMapping("queryCarTypeInfo.do")
    public String queryCarTypeInfo(HttpServletRequest request,
            HttpServletResponse response, String phone, String pwd,
            String userName, String userType,String carType)throws Exception
    {
    	List<Map<String,Object>> data=loginMapper.queryCarTypeInfo();
    	request.getSession().setAttribute("CARTYPE", data);
        return "register";
    }
    @RequestMapping("addUser.do")
    public void  addUser(HttpServletRequest request,HttpServletResponse response, String phone, String pwd,
                         String userName, String userType,String carType) throws Exception {

        System.out.println(userType+"  "+phone+" "+pwd+" "+userName+" "+carType);

        List<Map<String, Object>> data = loginMapper.loginCommomPhone(phone);
        System.out.println(data.size());
        //手机号已被注册
        if (data.size() > 0) {
            response.getWriter().print("手机号码已经被注册!!!");
            return ;
        }
        int cou = loginServletDao.addUser(userName, pwd, phone, userType, carType);
        System.out.println("cou=" + cou);
        if (cou > 0) {
            response.getWriter().print("注册成功!!!");
            return ;
        }
        else if (cou==-1)
        {
            response.getWriter().print("手机号未注册农机!!!");
            return ;
        }
        else {
            response.getWriter().print("注册失败!!!");
            return;
        }
    }
    //返回登录页面
    @RequestMapping("returnIndex.do")
    public  String returnIndex(HttpServletRequest request,
                                    HttpServletResponse response)throws Exception{
        return "index";
    }

    //web端      注销用户
    @RequestMapping("deleteUserByTel.do")
    public  String deleteUserByTel(HttpServletRequest request,
                                   HttpServletResponse response, String tel)throws Exception{
        int res=tuserMapper.deleteByTel(tel);
        return "index";
    }
    //web端   跳转到地图页面mapList.jsp
    @RequestMapping("queryAllUserCar")
    public String queryAllUserCar(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        return "mapList";
    }
    @RequestMapping("updateUserPwd.do")
    public void updateUserPwd(HttpServletRequest request,
                                   HttpServletResponse response, String userID, String new_password)
            throws Exception {
        System.out.println(userID);
        System.out.println("htt3"+new_password);
        int res = loginServletDao.updateUserPwd(Integer.parseInt(userID), new_password);
        System.out.println(res);
        response.getWriter().print(res);
    }
    @RequestMapping("updateUserType.do")//原来updateUserZhuanHuan.do改为updateUserType.do
    public void updateUserType(HttpServletRequest request,
                                    HttpServletResponse response, int userID, int userType)
            throws Exception {
        int res=loginServletDao.updateUserType(userID, userType);
    }
    @RequestMapping("queryMapNoddessUser.do")//web端地图查询用户
    public String queryMapNoddessUser(HttpServletRequest request,HttpServletResponse response,
                                     String userID,String userTypeQuery,String kmNumber,String tel,String lo,String la)throws Exception{
        //将查询结果保存到List对象，将List保存在会话属性中，返回 mapList

        List<Map<String,Object>> data = null;
        List<Map<String,Object>> data1 = null;
        List<Map<String,Object>> data2 = null;
        int flag_int=Integer.parseInt(userTypeQuery);//userTypeQuery待查询的用户类型
        request.getSession().setAttribute("KMNUMBER", kmNumber);
        if(!tel.isEmpty())//按手机查询
        {
            data=tuserMapper.queryUserByTel(tel);
            request.setAttribute("userByPhoneWeb", JsonUtil.toJsonString(data));
            System.out.println(data.size()+"手机");
            System.out.println(JsonUtil.toJsonString(data) +"手机");
        }
        else
        {
            if (flag_int==0)//查询农机手
            {
                data=tuserMapper.queryMapDriver(lo,la,kmNumber);//查询userType=1农机手
                request.setAttribute("driver", JsonUtil.toJsonString(data));
                request.setAttribute("driverSize", data.size());
                request.setAttribute("MAPUSERTYPEQUERY", 1);
                System.out.println(data.size()+"农机手");
                System.out.println(JsonUtil.toJsonString(data)+"农机手");
                //response.getWriter().print("{'driver':" + data + "}");
            }
            else if(flag_int==1)//查询农户
            {
                data=tuserMapper.queryMapFarmer(lo,la,kmNumber);//查询userType=0农户
                request.setAttribute("farmer", JsonUtil.toJsonString(data));
                request.setAttribute("farmerSize", data.size());
                request.setAttribute("MAPUSERTYPEQUERY", 0);
                System.out.println(JsonUtil.toJsonString(data)+"农户");
                System.out.println(data.size()+"农户");
                //response.getWriter().print("{'farmer':" + data + "}");
            }
            else if(flag_int==2)//查询全部--农户+农机手
            {
                data1=tuserMapper.queryMapFarmer(lo, la, kmNumber);//查询userType=-1--全部农户+农机手//查询userType=0农户
                data2=tuserMapper.queryMapDriver(lo, la, kmNumber);//查询userType=-1--全部农户+农机手//查询userType=1农机手
                request.setAttribute("farmer", JsonUtil.toJsonString(data1));
                request.setAttribute("farmerSize", data1.size());
                request.setAttribute("driver", JsonUtil.toJsonString(data2));
                request.setAttribute("driverSize", data2.size());
                request.setAttribute("MAPUSERTYPEQUERY", -1);
                //response.getWriter().print("{'farmer':" + data1 + "}");
                //response.getWriter().print("{'driver':" + data2 + "}");
                System.out.println("农户为："+JsonUtil.toJsonString(data1));
                System.out.println("农机手为："+JsonUtil.toJsonString(data2));

            }
        }

        System.out.println("htt  "+userID+userTypeQuery+kmNumber+tel+lo+la);
        return "mapList";
    }
}