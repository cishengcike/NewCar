package com.smartamd.controller;

import com.smartamd.mapper.*;
import com.smartamd.model.QueryLoLa;
import com.smartamd.service.*;
import com.smartamd.utils.JsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//用户登录
@Controller//用来标注当前类是springmvc的控制层类
public class LoginInfo {

    private static Map<String, String> s_content = new HashMap<String, String>();
    private static Date dt = new Date();
    private static DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private final int MAX_DISTANCE = 5000;
    @Autowired//注入，完成自动装配，使用set，get方法
    private LoginMapper loginMapper;
    @Autowired
    private LoginServletDao loginServletDao;
    @Autowired
    private TuserMapper tuserMapper;
    @Autowired
    private TcarMapper tcarMapper;
    @Autowired
    private QueryLoLaMapper queryLoLaMapper;
    @Autowired
    private ServiceStationMapper serviceStationMapper;

//    @Autowired
//    private UsertypeMapper usertypeMapper;
//
//    @Autowired
//    private TusersMapper  tusersMapper;

    //手机端登录
    @RequestMapping("loginInfoPhone.do")//手机端的登陆servlet
    public void loginInfoPhone(HttpServletRequest request,
                               HttpServletResponse response, String phone, String pwd, String CID, String lo, String la)
            throws Exception {
        System.out.println("loginInfoPhone.do");
//        System.out.println(lo + la);
        System.out.println(CID);
        System.out.println(df.format(dt));
        if ("".equals(CID) || CID == null) CID = "0";
        tuserMapper.updateUser(phone, CID, lo, la, df.format(dt));
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
            response.getWriter().print("{'success':2}");
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
        System.out.printf(",userID=%s,userType=%s,carType=%s\n", userID, userType, carType);
//        if("0".equals(userType)&&carType!=null)
//            response.getWriter().print("{'success':" + 2 + "}");
        if ("4".equals(userType))
            response.getWriter().print("{'success':" + 4 + "}");

        else {
            if ("错误".equals(carType)) {
                int res = loginServletDao.alterUserInformationPhone(Integer.parseInt(userID), userType, address, carType);
                response.getWriter().print("{'success':" + res + "}");
                System.out.println("WAG SUCCESS = " + res);

            } else {
                if ("0".equals(userType))
                    response.getWriter().print("{'success':" + 2 + "}");
                else {
                    int res = loginServletDao.alterUserInformationPhone(Integer.parseInt(userID), userType, address, carType);
                    System.out.println("res=" + res);
                    response.getWriter().print("{'success':" + res + "}");
                }
            }
        }

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
            System.out.println("查询农机手此时phone="+phone);
            if (phone == null) {
                if (carType == null || "-1".equals(carType)) {
                    data = tuserMapper.queryMapDriverPhone(lo, la, kmNumber);
                    response.getWriter().print("{'driver':" + data + "}");
                    System.out.println("catType=null");
                    System.out.println(data.size());
                    for (Map<String, Object> mm :data) {
                        System.out.println(mm);
                    }
                } else {
                    int type = loginMapper.queryCarType(carType);
                    data = tuserMapper.queryMapDriverByType(lo, la, kmNumber, type);
                    response.getWriter().print("{'driver':" + data + "}");
                    System.out.println("carType!=null");
                    System.out.println(data.size());
                    for (Map<String, Object> mm :data) {
                        System.out.println(mm);
                    }
                }
            } else {
                List<Map<String, Object>> map = tuserMapper.queryMapDriverByPhone(lo, la, phone);
                if ((Integer) map.get(0).get("USERTYPE") == 1)
                    response.getWriter().print("{'driver':[" + map.get(0) + "]}");

            }
        } else if (flag_int == 1)//查询农户
        {
            if (phone == null) {
                data = tuserMapper.queryMapFarmerPhone(lo, la, kmNumber);//查询userType=0农户
                for (Map<String, Object> test : data)
                    System.out.println(test);
                response.getWriter().print("{'farmer':" + data + "}");
            } else {
                Map<String, Object> map = tuserMapper.queryMapFarmerByPhone(lo, la, phone);
                if ((Integer) map.get("USERTYPE") == 0)
                    response.getWriter().print("{'farmer':[" + map + "]}");
            }


        } else if (flag_int == 2)//查询全部--农户+农机手
        {
            System.out.println("查询农户和农机手");
            if (phone == null) {
                data1 = tuserMapper.queryMapFarmerPhone(lo, la, kmNumber);//查询userType=-1--全部农户+农机手//查询userType=0农户
                data2 = tuserMapper.queryMapDriverPhone(lo, la, kmNumber);//查询userType=-1--全部农户+农机手//查询userType=1农机手
                System.out.println("全部农户数量为:" + data1.size());
                System.out.println("全部农机数量为:" + data2.size());
                for (Map<String, Object> test1 : data1)
                    System.out.println(test1);
                System.out.println("农机手");
                System.out.println();
                for (Map<String, Object> test2 : data2)
                    System.out.println(test2);


                response.getWriter().print("{'farmer':" + data1 + ",'driver':" + data2 + "}");
            } else {
                System.out.println("phone不为空");
                Map<String, Object> map1 = tuserMapper.queryMapFarmerByPhone(lo, la, phone);
                List<Map<String, Object>> map2 = tuserMapper.queryMapDriverByPhone(lo, la, phone);
                if (map1 != null && map2 == null)
                    response.getWriter().print("{'farmer':[" + map1 + "]," + "'driver':[{" + "}]}");
                if (map1 == null && map2 != null)
                    response.getWriter().print("{'driver':[" + map2.get(0) + "]," + "'farmer':[{" + "}]}");
            }

        }
    }

    @RequestMapping("push.do")
    public void push(String userID, String userName, String phone, String kmNumber, String lo, String la, String carType, String flag, String userType, String content) {
        System.out.println("url:push.do");
        System.out.printf("userID=%s,username=%s,userType=%s,phone=%s,kmNumber=%s,lo=%s,la=%s,flag=%s,content=%s\n", userID, userName, userType, phone, kmNumber, lo, la, flag, content);

        if ("".equals(phone)) phone = null;
        List<Map<String, Object>> data = null;
        List<Map<String, Object>> data1 = null;
        List<Map<String, Object>> data2 = null;
        List<String> cid_list = null;
        int flag_int = Integer.parseInt(flag);
        String push_phone = tuserMapper.selectUserTelByUserID(userID);
        s_content.put(push_phone, content);
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

                cid_list = CIDResolver.getCIDList(data, userID);
                System.out.println("cid_list=" + cid_list);
                System.out.println(userName);
                PushToList.PushToPhone(userName, userType, content, push_phone, cid_list);
            } else {
                List<Map<String, Object>> map = tuserMapper.queryMapDriverByPhone(lo, la, phone);
//                if ((int) map.get("USERTYPE") == 1)
//                    response.getWriter().print("{'driver':[" + map + "]}");

                cid_list = CIDResolver.getCIDList(map.get(0));
                System.out.println("cid_list=" + cid_list);
                PushToList.PushToPhone(userName, userType, content, push_phone, cid_list);


            }
        } else if (flag_int == 1)//查询农户
        {
            if (phone == null) {

                data = tuserMapper.queryMapFarmer(lo, la, kmNumber);//查询userType=0农户
//                response.getWriter().print("{'farmer':" + data + "}");
                cid_list = CIDResolver.getCIDList(data, userID);
                System.out.println("cid_list=" + cid_list);
                PushToList.PushToPhone(userName, userType, content, push_phone, cid_list);

            } else {
                Map<String, Object> map = tuserMapper.queryMapFarmerByPhone(lo, la, phone);
                cid_list = CIDResolver.getCIDList(map);
                System.out.println("cid_list=" + cid_list);
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
                cid_list = CIDResolver.getCIDList(data, userID);
                System.out.println("cid_list=" + cid_list);
                PushToList.PushToPhone(userName, userType, content, push_phone, cid_list);

            } else {
                System.out.println("phone不为空");
                Map<String, Object> map = tuserMapper.queryMapAllByPhone(lo, la, phone);
                cid_list = CIDResolver.getCIDList(map);
                System.out.println("cid_list=" + cid_list);
                PushToList.PushToPhone(userName, userType, content, push_phone, cid_list);

//                if (map1 != null && map2 == null)
////                    response.getWriter().print("{'farmer':[" + map1 + "]," + "'driver':[{" + "}]}");
//                if (map1 == null && map2 != null)
////                    response.getWriter().print("{'driver':[" + map2 + "]," + "'farmer':[{" + "}]}");
            }

        }

        System.out.println("个推请求完成后存入的s_content为" + s_content);

    }

    @RequestMapping("queryUserDetailsPhone.do")
    public void queryUserDetailsPhone(HttpServletRequest request, HttpServletResponse response, String opphone, String lo, String la) throws Exception {
        System.out.println("url:qurtyUserDerailsPhone");
        System.out.printf("opphone=%s,lo=%s,la=%s\n", opphone, lo, la);
        System.out.println("个推请求后再次detail请求时的s_content为" + s_content);
        Map<String, Object> map = tuserMapper.selectUserByPhone(opphone, lo, la);
        System.out.println("content内容为：" + s_content.get(opphone));
        map.put("CONTENT", s_content.get(opphone));
        System.out.println(map);
        response.getWriter().print("{'user':[" + map + "]}");

    }


    /**
     * 查询历史经纬度
     *
     * @return
     */
    @RequestMapping(value = "historyRoute.do")
    public String historyRoute(String simNo, String startTime, String lastTime, Model model) {
        System.out.println("url:historyRoute.do");
        try {
            System.out.println("simNo=" + simNo);
            System.out.println("startTime=" + startTime);
            System.out.println("lastTime=" + lastTime);
            int carID = queryLoLaMapper.queryCarID(simNo);
            List<QueryLoLa> list = queryLoLaMapper.queryLoLa(String.valueOf(carID), startTime, lastTime);
            model.addAttribute("FIRST_LENGTH", list.size());
            int length = list.size();
            Distance distance = new Distance();
            int i = 0, j = 1;
            System.out.println("初始长度" + list.size());

            if (length != 0) {
                model.addAttribute("flo", list.get(length - 1).getLo());
                model.addAttribute("fla", list.get(length - 1).getLa());

                //两个坐标距离相差少于MAX_DISTANCE不再地图上显示
                while (j <= length - 1) {
                    double lo1, la1, lo2, la2;
                    lo1 = list.get(i).getLo();
                    la1 = list.get(i).getLa();
                    lo2 = list.get(j).getLo();
                    la2 = list.get(j).getLa();
                    if (distance.getDistance(lo1, la1, lo2, la2) < MAX_DISTANCE) {
                        list.remove(j);

                        length = length - 1;
                    } else {
                        i++;
                        j++;
                    }
                }
            }
            else {
                System.out.println("没有经纬度");
                model.addAttribute("errorCode", 0);
                return "error";
            }
            System.out.println("处理后长度" + list.size());
//        for (QueryLoLa ll : list)
//            System.out.println(ll.toString());

            JSONArray jsonArray = JSONArray.fromObject(list);
            model.addAttribute("historyLoLa", jsonArray);
            model.addAttribute("FINAL_LENGTH", list.size());

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorCode",1);
            return "error";
        }
        return "history";

    }

    /**
     * 跳转到历史轨迹页面
     *
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
        System.out.println("phone,pwd" + phone + pwd);
        List<Map<String, Object>> data_phone = loginMapper.loginCommomPhone(phone);
        //System.out.println(data_phone.get(0));
        if (data_phone == null || data_phone.size() <= 0) {//手机号不存在时
            response.getWriter().print(-5);
            return;
        }
        String oldpwd = pwd;
        pwd = MD5.MD5Trans(pwd);
        List<Map<String, Object>> data = loginMapper.loginTrue(phone, pwd);//取出指定电话和密码的用户信息
        if (data.size() > 0) {
            data.get(0).put("PASSWORD", oldpwd);
            request.getSession().setAttribute("user", data.get(0));//用户的信息放入到id=user
            request.getSession().setAttribute("KMNUMBER", "15");//初始化查询的默认公里数
            request.getSession().setAttribute("farmer", "");
            request.getSession().setAttribute("driver", "");
            request.getSession().setAttribute("MAPUSERTYPEQUERY", 1);
            request.getSession().setAttribute("MAPTEAMTYPEQUERY", 0);
            response.getWriter().print(data.size());
        } else {
            response.getWriter().print(data.size());//登录用户失败则size（）=0
        }

    }

    //web注册

    @RequestMapping("queryCarTypeInfo.do")
    public String queryCarTypeInfo(HttpServletRequest request,
                                   HttpServletResponse response, String phone, String pwd,
                                   String userName, String userType, String carType) throws Exception {
        System.out.println("url=queryCarTypeInfo.do");
        List<Map<String, Object>> data = loginMapper.queryCarTypeInfo();
        request.getSession().setAttribute("CARTYPE", data);
        return "register";
    }

    @RequestMapping("addUser.do")
    public void addUser(HttpServletRequest request, HttpServletResponse response, String phone, String pwd,
                        String userName, String userType, String carType) throws Exception {

        System.out.println(userType + "  " + phone + " " + pwd + " " + userName + " " + carType);

        List<Map<String, Object>> data = loginMapper.loginCommomPhone(phone);
        System.out.println(data.size());
        //手机号已被注册
        if (data.size() > 0) {
            response.getWriter().print("手机号码已经被注册!!!");
            return;
        }
        pwd = MD5.MD5Trans(pwd);
        int cou = loginServletDao.addUser(userName, pwd, phone, userType, carType);
        System.out.println("cou=" + cou);
        if (cou > 0) {
            response.getWriter().print("注册成功!!!");
            return;
        } else if (cou == -1) {
            response.getWriter().print("手机号未注册农机!!!");
            return;
        } else {
            response.getWriter().print("注册失败!!!");
            return;
        }
    }

    //返回登录页面
    @RequestMapping("returnIndex.do")
    public String returnIndex(HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        return "index";
    }

    //web端      注销用户
    @RequestMapping("deleteUserByTel.do")
    public String deleteUserByTel(HttpServletRequest request,
                                  HttpServletResponse response, String tel) throws Exception {
        int res = tuserMapper.deleteByTel(tel);
        return "index";
    }

    //web端   跳转到地图页面mapList.jsp
    @RequestMapping("queryAllUserCar")
    public String queryAllUserCar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "mapList";
    }

    @RequestMapping("updateUserPwd.do")
    public void updateUserPwd(HttpServletRequest request,
                              HttpServletResponse response, String userID, String new_password)
            throws Exception {
        System.out.println("url:updateUserPwd.do");
        System.out.println(userID);
        System.out.println("htt3" + new_password);
        new_password = MD5.MD5Trans(new_password);
        int res = loginServletDao.updateUserPwd(Integer.parseInt(userID), new_password);
        System.out.println(res);
        response.getWriter().print(res);
    }

    @RequestMapping("updateUserType.do")//原来updateUserZhuanHuan.do改为updateUserType.do
    public void updateUserType(HttpServletRequest request,
                               HttpServletResponse response, int userID, int userType)
            throws Exception {
        int res = loginServletDao.updateUserType(userID, userType);
    }

    @RequestMapping("queryMapNoddessUser.do")//web端地图查询用户
    public String queryMapNoddessUser(HttpServletRequest request, HttpServletResponse response,
                                      String userID, String userTypeQuery, String kmNumber, String tel, String lo, String la) throws Exception {
        //将查询结果保存到List对象，将List保存在会话属性中，返回 mapList
        System.out.printf("kmNumber=%s,userTypeQuery=%s\n",kmNumber,userTypeQuery);
        List<Map<String, Object>> data = null;
        List<Map<String, Object>> data1 = null;
        List<Map<String, Object>> data2 = null;
        int flag_int = Integer.parseInt(userTypeQuery);//userTypeQuery待查询的用户类型
        request.getSession().setAttribute("KMNUMBER", kmNumber);
        /*if (!tel.isEmpty())//按手机查询，查tuser表  应该改成查car表
        {
            data = tuserMapper.queryUserByTel(tel);
            request.setAttribute("userByPhoneWeb", JsonUtil.toJsonString(data));
            System.out.println(data.size() + "手机");
            System.out.println(JsonUtil.toJsonString(data) + "手机");
        } */
        if (!tel.isEmpty())//按手机查询，改成查car表
        {
            data = tcarMapper.queryCarByTel(tel);
            request.setAttribute("userByPhoneWeb", JsonUtil.toJsonString(data));
            System.out.println(data.size() + "手机");
            System.out.println(JsonUtil.toJsonString(data) + "手机");
        } else {
            if (flag_int == 0)//查询农机手
            {
                data = tuserMapper.queryMapDriver(lo, la, kmNumber);//查询userType=1农机手
                request.setAttribute("driver", JsonUtil.toJsonString(data));

                request.setAttribute("MAPUSERTYPEQUERY", 1);
                System.out.println(data.size() + "农机手");
                System.out.println(JsonUtil.toJsonString(data) + "农机手");
                //response.getWriter().print("{'driver':" + data + "}");
            } else if (flag_int == 1)//查询农户
            {
                data = tuserMapper.queryMapFarmer(lo, la, kmNumber);//查询userType=0农户
                request.setAttribute("farmer", JsonUtil.toJsonString(data));
                request.setAttribute("farmerSize", data.size());
                request.setAttribute("MAPUSERTYPEQUERY", 0);
                System.out.println(JsonUtil.toJsonString(data) + "农户");
                System.out.println(data.size() + "农户");
                //response.getWriter().print("{'farmer':" + data + "}");
            } else if (flag_int == 2)//查询农机
            {
                data = tuserMapper.queryMapDriver(lo, la, kmNumber);
                request.setAttribute("machine", JsonUtil.toJsonString(data));
                request.setAttribute("machineSize", data.size());
                request.setAttribute("MAPUSERTYPEQUERY", 2);
                data.forEach(o->System.out.println(o));
                System.out.println(data.size() + "农机");
            } else if (flag_int == 3)//查询全部--农户+农机手
            {
                System.out.println("全部");
                data1 = tuserMapper.queryMapFarmer(lo, la, kmNumber);//查询userType=-1--全部农户+农机手//查询userType=0农户
                data2 = tuserMapper.queryMapDriver(lo, la, kmNumber);//查询userType=-1--全部农户+农机手//查询userType=1农机手
                request.setAttribute("farmer", JsonUtil.toJsonString(data1));
                request.setAttribute("farmerSize", data1.size());
                request.setAttribute("driver", JsonUtil.toJsonString(data2));
                request.setAttribute("driverSize", data2.size());
                request.setAttribute("MAPUSERTYPEQUERY", -1);
                //response.getWriter().print("{'farmer':" + data1 + "}");
                //response.getWriter().print("{'driver':" + data2 + "}");
            }
        }

        return "mapList";
    }

    @RequestMapping("showDataGrid.do")
    @ResponseBody
    //用户列表功能已隐藏
    //@ResponseBody默认返回数据类型Content-Type不带编码信息
    //已修改，在配置文件dispatcher-servlet.xml中,使注解的返回类型改为Content-Type:text/plain;charset=UTF-8
    public String showDataGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        String userId, userTypeQuery, kmNumber, tel, lo, la, teamId;
        List<Map<String, Object>> data = null;
        List<Map<String, Object>> data1 = null;
        List<Map<String, Object>> data2 = null;
        int i, j;
        //获取页面参数
        userId = request.getParameter("userID");
        userTypeQuery = request.getParameter("userTypeQuery");
        kmNumber = request.getParameter("kmNumber");
        tel = request.getParameter("tel");
        lo = request.getParameter("lo");
        la = request.getParameter("la");
        teamId = request.getParameter("teamID");

        System.out.println("teamId:" + teamId + "  userID:" + userId + "  userTypeQuery:" + userTypeQuery +
                "  kmNumber:" + kmNumber + "  tel:" + tel + "  lo:" + lo + "  la:" + la);

        int flag_int = Integer.parseInt(userTypeQuery);//userTypeQuery待查询的用户类型

        if (!tel.isEmpty())//按手机查询
        {
            JSONArray jsonArray = new JSONArray();
            data = tuserMapper.queryUserByTel(tel);//更改10.13
            if (data.size() == 0) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("USERNAME", "无");
                jsonObject.put("PHONE", "无");
                jsonObject.put("LOGINTIME", "无");
                jsonObject.put("MACHINENO", "无");
                jsonArray.add(jsonObject);
                result.put("total", data.size());
                result.put("rows", jsonArray);
                JSONObject fromObject = JSONObject.fromObject(result);
                return fromObject.toString();
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("USERNAME", data.get(0).get("USERNAME"));
            jsonObject.put("PHONE", data.get(0).get("PHONE"));
            //按手机号查询，显示时，考虑农户农机手
            if (data.get(0).get("LOGINTIME") == null) {
                jsonObject.put("LOGINTIME", "无");
            } else {
                jsonObject.put("LOGINTIME", data.get(0).get("LOGINTIME").toString());
            }
            if (((Integer) data.get(0).get("USERTYPE")).intValue() == 1)//农机手
            {
                String str = tcarMapper.selectCarNameByCarId(tel);
                jsonObject.put("CARTYPENAME", str);
            } else {
                jsonObject.put("CARTYPENAME", "无");
            }
            jsonArray.add(jsonObject);
            result.put("total", data.size());
            result.put("rows", jsonArray);
            JSONObject fromObject = JSONObject.fromObject(result);
            return fromObject.toString();
        } else {
            if (flag_int == 0)//查询农机手
            {
                JSONArray jsonArray = new JSONArray();
                data = tuserMapper.queryMapDriver(lo, la, kmNumber);//查询userType=1农机手
                if (data.size() == 0) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("USERNAME", "无");
                    jsonObject.put("PHONE", "无");
                    jsonObject.put("LOGINTIME", "无");
                    jsonObject.put("MACHINENO", "无");
                    jsonArray.add(jsonObject);
                    result.put("total", data.size());
                    result.put("rows", jsonArray);
                    JSONObject fromObject = JSONObject.fromObject(result);
                    return fromObject.toString();
                }
                if (Long.parseLong(teamId) == -1)//此情况为，查询用户类型为农机手，且车队类型为全部，此时teamId=全部的id号 -1
                {
                    for (j = 0; j < data.size(); j++) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("USERNAME", data.get(j).get("USERNAME"));
                        jsonObject.put("PHONE", data.get(j).get("PHONE"));
                        if (data.get(j).get("LOGINTIME") == null) {
                            jsonObject.put("LOGINTIME", "无");
                        } else {
                            jsonObject.put("LOGINTIME", data.get(j).get("LOGINTIME").toString());
                        }
                        jsonObject.put("CARTYPENAME", data.get(j).get("CARTYPENAME"));
                        jsonArray.add(jsonObject);
                    }
                } else//                  有teamid
                {
                    for (i = 0; i < data.size(); i++) {
                        if ((Long) data.get(i).get("TEAMID") == Long.parseLong(teamId))//按车队显示
                        {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("USERNAME", data.get(i).get("USERNAME"));
                            jsonObject.put("PHONE", data.get(i).get("PHONE"));
                            jsonObject.put("CARTYPENAME", data.get(i).get("CARTYPENAME"));
                            if (data.get(i).get("LOGINTIME") == null) {
                                jsonObject.put("LOGINTIME", "无");
                            } else {
                                jsonObject.put("LOGINTIME", data.get(i).get("LOGINTIME").toString());
                            }
                            jsonArray.add(jsonObject);
                        }
                    }
                }
                result.put("total", data.size());
                result.put("rows", jsonArray);
                JSONObject fromObject = JSONObject.fromObject(result);
                System.out.println("农机手，车队或者全部" + fromObject);
                return fromObject.toString();
            } else if (flag_int == 1)//查询农户
            {
                int t = 0;
                JSONArray jsonArray = new JSONArray();
                data = tuserMapper.queryMapFarmer(lo, la, kmNumber);//查询userType=0农户
                if (data.size() == 0) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("USERNAME", "无");
                    jsonObject.put("PHONE", "无");
                    jsonObject.put("LOGINTIME", "无");
                    jsonObject.put("MACHINENO", "无");
                    jsonArray.add(jsonObject);
                    result.put("total", data.size());
                    result.put("rows", jsonArray);
                    JSONObject fromObject = JSONObject.fromObject(result);
                    return fromObject.toString();
                }
                for (i = 0; i < data.size(); i++) {                                   //农户不属于车队
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("USERNAME", data.get(i).get("USERNAME"));
                    jsonObject.put("PHONE", data.get(i).get("PHONE"));
                    if (data.get(i).get("LOGINTIME") == null) {
                        jsonObject.put("LOGINTIME", "无");
                    } else {
                        jsonObject.put("LOGINTIME", data.get(i).get("LOGINTIME").toString());
                    }
                    jsonObject.put("CARTYPENAME", "农户");
                    jsonArray.add(jsonObject);
                }
                result.put("total", data.size());
                result.put("rows", jsonArray);
                JSONObject fromObject = JSONObject.fromObject(result);
                return fromObject.toString();
            } else if (flag_int == 2)//农机
            {
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("USERNAME", "请点击农机显示");
                jsonObject.put("PHONE", "");
                jsonObject.put("LOGINTIME", "");
                jsonObject.put("MACHINENO", "");
                jsonArray.add(jsonObject);
                result.put("total", 1);
                result.put("rows", jsonArray);
                JSONObject fromObject = JSONObject.fromObject(result);
                return fromObject.toString();
            } else if (flag_int == 3)//查询全部--农户+农机手
            {
                JSONArray jsonArray = new JSONArray();
                data1 = tuserMapper.queryMapFarmer(lo, la, kmNumber);//查询userType=-1--全部农户+农机手//查询userType=0农户
                data2 = tuserMapper.queryMapDriver(lo, la, kmNumber);//查询userType=-1--全部农户+农机手//查询userType=1农机手
                if ((data1.size() + data2.size()) == 0) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("USERNAME", "无");
                    jsonObject.put("PHONE", "无");
                    jsonObject.put("LOGINTIME", "无");
                    jsonObject.put("MACHINENO", "无");
                    jsonArray.add(jsonObject);
                    result.put("total", data.size());
                    result.put("rows", jsonArray);
                    JSONObject fromObject = JSONObject.fromObject(result);
                    return fromObject.toString();
                }
                if (Long.parseLong(teamId) == -1)//此情况为，查询用户类型为全部，且车队类型也为全部，此时teamId=全部的id号 -1
                {
                    for (j = 0; j < data2.size(); j++) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("USERNAME", data2.get(j).get("USERNAME"));
                        jsonObject.put("PHONE", data2.get(j).get("PHONE"));
                        if (data2.get(j).get("LOGINTIME") == null) {
                            jsonObject.put("LOGINTIME", "无");
                        } else {
                            jsonObject.put("LOGINTIME", data2.get(j).get("LOGINTIME").toString());
                        }
                        jsonObject.put("CARTYPENAME", data2.get(j).get("CARTYPENAME"));
                        jsonArray.add(jsonObject);
                    }
                } else //此情况为，查询的用户类型为全部，但车队类型为teamId
                {
                    for (j = 0; j < data2.size(); j++) {
                        if ((Long) data2.get(j).get("TEAMID") == Long.parseLong(teamId)) {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("USERNAME", data2.get(j).get("USERNAME"));
                            jsonObject.put("PHONE", data2.get(j).get("PHONE"));
                            if (data2.get(j).get("LOGINTIME") == null) {
                                jsonObject.put("LOGINTIME", "无");
                            } else {
                                jsonObject.put("LOGINTIME", data2.get(j).get("LOGINTIME").toString());
                            }
                            jsonObject.put("CARTYPENAME", data2.get(j).get("CARTYPENAME"));
                            jsonArray.add(jsonObject);
                        }

                    }
                }
                for (i = 0; i < data1.size(); i++) {            //列表显示农户
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("USERNAME", data1.get(i).get("USERNAME"));
                    jsonObject.put("PHONE", data1.get(i).get("PHONE"));
                    if (data1.get(i).get("LOGINTIME") == null) {
                        jsonObject.put("LOGINTIME", "无");
                    } else {
                        jsonObject.put("LOGINTIME", data1.get(i).get("LOGINTIME").toString());
                    }
                    jsonObject.put("CARTYPENAME", "农户");
                    jsonArray.add(jsonObject);
                }

                result.put("total", data1.size() + data2.size());
                result.put("rows", jsonArray);
                JSONObject fromObject = JSONObject.fromObject(result);
                return fromObject.toString();
            } else {
                return "";
            }
        }
    }

    @RequestMapping("showCarDataGrid.do")
    @ResponseBody
    //@ResponseBody默认返回数据类型Content-Type不带编码信息
    //已修改，在配置文件dispatcher-servlet.xml中,使注解的返回类型改为Content-Type:text/plain;charset=UTF-8
    public String showCarDataGrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        String userId, kmNumber, tel, lo, la, teamId;
        List<Map<String, Object>> data = null;
        List<Map<String, Object>> data1 = null;
        List<Map<String, Object>> data2 = null;
        int i, j;
        //获取页面参数
        userId = request.getParameter("userID");
        kmNumber = request.getParameter("kmNumber");
        tel = request.getParameter("tel");
        lo = request.getParameter("lo");
        la = request.getParameter("la");
        teamId = request.getParameter("teamID");

        System.out.println("农机  " + "teamId:" + teamId + "  userID:" + userId + "  kmNumber:" + kmNumber + "  tel:" + tel + "  lo:" + lo + "  la:" + la);

        if (!tel.isEmpty())//按车辆手机查询
        {
            JSONArray jsonArray = new JSONArray();
            data = tcarMapper.queryCarByTel(tel);//查询范围内，查询农机
            if (data.size() == 0) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("USERNAME", "无");
                jsonObject.put("PHONE", "无");
                jsonObject.put("LOGINTIME", "无");
                jsonObject.put("MACHINENO", "无");
                jsonArray.add(jsonObject);
            } else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("USERNAME", data.get(0).get("USERNAME"));
                jsonObject.put("PHONE", data.get(0).get("PHONE"));
                if (data.get(0).get("LOGINTIME") == null) {
                    jsonObject.put("LOGINTIME", "无");
                } else {
                    jsonObject.put("LOGINTIME", data.get(0).get("LOGINTIME").toString());
                }
                jsonObject.put("MACHINENO", data.get(0).get("SIMNO"));
                jsonArray.add(jsonObject);
            }

            result.put("total", data.size());
            result.put("rows", jsonArray);
            JSONObject fromObject = JSONObject.fromObject(result);
            return fromObject.toString();
        } else//不按手机号查询
        {
            JSONArray jsonArray = new JSONArray();
            data = tcarMapper.queryCar(lo, la, kmNumber);//查询范围内，查询农机
            if (data.size() == 0) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("USERNAME", "无");
                jsonObject.put("PHONE", "无");
                jsonObject.put("LOGINTIME", "无");
                jsonObject.put("MACHINENO", "无");
                jsonArray.add(jsonObject);
                result.put("total", data.size());
                result.put("rows", jsonArray);
                JSONObject fromObject = JSONObject.fromObject(result);
                return fromObject.toString();
            }
            if (Long.parseLong(teamId) == -1)//此情况为，查询农机，且车队类型为全部，此时teamId=全部的id号 -1
            {
                for (j = 0; j < data.size(); j++) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("USERNAME", data.get(j).get("USERNAME"));
                    jsonObject.put("PHONE", data.get(j).get("PHONE"));
                    if (data.get(j).get("LOGINTIME") == null) {
                        jsonObject.put("LOGINTIME", "无");
                    } else {
                        jsonObject.put("LOGINTIME", data.get(j).get("LOGINTIME").toString());
                    }
                    jsonObject.put("MACHINENO", data.get(j).get("SIMNO"));
                    jsonArray.add(jsonObject);
                }
            } else//                  有teamid
            {
                for (i = 0; i < data.size(); i++) {
                    if ((Long) data.get(i).get("TEAMID") == Long.parseLong(teamId))//按车队显示
                    {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("USERNAME", data.get(i).get("USERNAME"));
                        jsonObject.put("PHONE", data.get(i).get("PHONE"));
                        jsonObject.put("MACHINENO", data.get(i).get("SIMNO"));
                        if (data.get(i).get("LOGINTIME") == null) {
                            jsonObject.put("LOGINTIME", "无");
                        } else {
                            jsonObject.put("LOGINTIME", data.get(i).get("LOGINTIME").toString());
                        }
                        jsonArray.add(jsonObject);
                    }
                }
            }
            result.put("total", data.size());
            result.put("rows", jsonArray);
            JSONObject fromObject = JSONObject.fromObject(result);
            return fromObject.toString();
        }
    }

    //手机端查询维修点
    @RequestMapping("service.do")
    public void service(HttpServletRequest request, HttpServletResponse response, String lo, String la, String kmNumber, String phone) throws Exception {
        System.out.println("service.do");
        System.out.printf("phone=%s,lo=%s,la=%s,knNumber=%s\n", phone, lo, la, kmNumber);

        if ("".equals(phone)) {
            List<Map<String, Object>> ss = serviceStationMapper.queryServiceStation(lo, la, kmNumber);
            for (Map<String, Object> map : ss)
                System.out.println(map);
            response.getWriter().print("{'service':" + ss + "}");
        } else {
            List<Map<String, Object>> ss = serviceStationMapper.queryServiceStationByPhone(lo, la, phone);
            for (Map<String, Object> map : ss)
                System.out.println(map);
            response.getWriter().print("{'service':" + ss + "}");
        }
    }

    @RequestMapping("serviceWeb.do")
    public String serviceWeb(HttpServletRequest request, HttpServletResponse response,Model model, String lo, String la, String kmNumber, String phone) throws Exception {
        System.out.println("service.do");
        System.out.printf("phone=%s,lo=%s,la=%s,kmNumber=%s\n", phone, lo, la, kmNumber);
        if (phone==null) {
            System.out.println("phone==null");
            List<Map<String, Object>> ss = serviceStationMapper.queryServiceStation(lo, la, kmNumber);
            System.out.println("ss："+JsonUtil.toJsonString(ss));
            System.out.println("ss的大小为"+ss.size());
            ss.forEach(o -> System.out.println(o));
//            model.addAttribute("service", JsonUtil.toJsonString(ss));
//            model.addAttribute("serviceSize", ss.size());
            request.setAttribute("service",JsonUtil.toJsonString(ss));
            request.setAttribute("serviceSize",ss.size());
        } else {
            List<Map<String, Object>> ss = serviceStationMapper.queryServiceStationByPhone(lo, la, phone);

            model.addAttribute("service", JsonUtil.toJsonString(ss));
            model.addAttribute("serviceSize", ss.size());
        }
        return "mapList";
    }

    @RequestMapping("showServiceDataGridWeb.do")
//    @ResponseBody
    public void showServiceDataGridWeb(HttpServletRequest request,HttpServletResponse response,String lo,String la,String kmNumber) throws  Exception{
        System.out.println("url:showServiceDataGridWeb");
        List<Map<String, Object>> data = serviceStationMapper.queryServiceStation(lo, la, kmNumber);
        data.forEach(o -> System.out.println(o));

        Map<String, Object> result = new HashMap<>();

        JSONArray jsonArray = new JSONArray();
        if (data.size() == 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("STATIONNAME", "无");
            jsonObject.put("STATIONTEL", "无");
            jsonObject.put("LINKMAN", "无");
            jsonArray.add(jsonObject);
        } else for (int j = 0; j < data.size(); j++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("STATIONNAME", data.get(j).get("STATIONNAME"));
            jsonObject.put("STATIONTEL", data.get(j).get("STATIONTEL"));
            jsonObject.put("LINKMAN", data.get(j).get("LINKMAN"));
            jsonArray.add(jsonObject);
        }
        result.put("total", data.size());
        result.put("rows", jsonArray);
        JSONObject fromObject = JSONObject.fromObject(result);

//        return fromObject.toString();
        response.getWriter().print(fromObject.toString());
    }
}