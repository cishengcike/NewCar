package test;

import com.gexin.rp.sdk.base.impl.Target;
import com.smartamd.mapper.*;
import com.smartamd.model.QueryLoLa;
import com.smartamd.service.CIDResolver;
import com.smartamd.service.LoginServletDao;
import com.smartamd.service.MD5;
import com.smartamd.service.PushToList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by aaron on 15-8-13.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:D:/IdeaProjects/Web/NewV/web/WEB-INF/dispatcher-servlet.xml")
public class MapperTest {

    @Autowired
    private LoginServletDao loginServletDao;
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private TuserMapper tuserMapper;

    private PushToList pushToList;

    private CIDResolver cidResolver;
    @Autowired
    private ServiceStationMapper serviceStationMapper;

    @Autowired
    private TcarMapper tcarMapper;

    @Autowired
    private QueryLoLaMapper queryLoLaMapper;


    @Test
    public void insertTuserTest() {
        loginMapper.insertTuser("段亦逍");
    }


    @Test
    public void interParseTest() {
        Object i = Integer.parseInt("1");
        System.out.println(i.getClass());
    }

    @Test
    public void printfTest() {
        String a = "shang";
        String b = "hai";
        String c = "hello";
        String d = "world";
        //System.out.printf("%c %c  %c  %c,a,b,c,d");
        System.out.printf("a=%s,b=%s,c=%s,d=%s", a, b, c, d);
    }

    @Test
    public void adminTest() {
        System.out.println(MD5.MD5Trans("Guangdian2014"));

    }

    @Test
    public void queryMapTest() {
        List<Map<String, Object>> list = null;
        list = tuserMapper.queryMapDriver("121.56095123291016", "31.30206871032715", "11500000");
        System.out.println(list.size());


//        System.out.println("??????????"+list);
//
//        JSONArray jsonArray = JSONArray.fromObject(list);
//        System.out.println("json??????"+ jsonArray);
//        JSONObject jsonObject = jsonArray.getJSONObject(0);
//        System.out.println("jsonobject??" + jsonObject);
//        jsonArray.clear();
//        jsonArray.add(0, "this is a test");
//        jsonObject.element("test", jsonArray);
//        System.out.println("??jsonovject??" + jsonObject);
//        JSONArray test = jsonObject.getJSONArray("test");
//        System.out.println("test??"+test);

    }

    @Test
    public void queryMapByPhoneTest() {
        List<Map<String, Object>> list1 = null;
        List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
         List<Map<String,Object>> mm = tuserMapper.queryMapDriverByPhone("121.560924939438", "31.3019659047683","10000000012");
            System.out.println(mm);
    }


//    @Test
//    public void queryMapDriverByTypeTest() {
//        List<Map<String, Object>> list = new ArrayList<>();
//        List<String> cid_list = new ArrayList<>();
//        list = tuserMapper.queryMapDriverByType("121.560924939438", "31.3019659047683", "15", 1);
//        //cidResolver
//        cid_list=CIDResolver.getCIDList(list);
//
//        int i=0;
//        for(String cid : cid_list)
//            System.out.println(i++ +":" + cid);
//
//    }


    @Test
    public void getTargetsTest() {
        List<String> list = new ArrayList<String>();
        List<Target> list2 = new ArrayList<Target>();
        list.add("9495b9705e7dc951d77d9ed98bfe5f5e");
        list.add("333d19cd3a2d6cff61cef87a8236d8a5");
        list2 = pushToList.getTarget(list);
        System.out.println(list2.get(0).getClientId());
    }

    @Test
    public void listTest() {
        List<String> list = new ArrayList<String>();
        List<Target> targets = new ArrayList<Target>();
        list.add("9495b9705e7dc951d77d9ed98bfe5f5e");
        list.add("333d19cd3a2d6cff61cef87a8236d8a5");
        for (String clientID : list) {
            Target target = new Target();
            target.setClientId(clientID);
            targets.add(target);

        }
        System.out.println(targets.get(1).getClientId());
    }






    @Test
    public void selectUserByPhoneTest() {
        Map<String, Object> map = tuserMapper.selectUserByPhone("15852386459", "121.56123", "31.30184");
        System.out.println(map);
    }

    @Test
    public void selectUserTelByUserIDTest() {
        System.out.println(tuserMapper.selectUserTelByUserID("12"));
    }

    @Test
    public void updateCarTypeTest() {
        int car_type = loginMapper.queryCarType("??????");
        System.out.println(car_type);
        String phone = tuserMapper.selectPhoneByUserID(34);
        System.out.println(phone);
        int res = tuserMapper.updateCarType(car_type, phone);
        System.out.println(res);
    }

    @Test
    public void selectUserFromTcarTest() {
        System.out.println(loginMapper.queryCarType("轮麦收割 "));

    }

    @Test
    public void queryCarTypeTest() {
        loginMapper.updateCarType("12345678907", loginMapper.queryCarType("轮麦收割"));
    }

    @Test
    public void alterUserInformationPhoneTest() {
        int a = loginServletDao.alterUserInformationPhone(492, "0", "", "");
    }

    @Test
    public void queryUserByPhoneTest() {
        System.out.println(tuserMapper.queryUserByPhone("10000000012"));
    }

    @Test
    public void updateUserTest() {
        Date dt = new Date();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        int a = tuserMapper.updateUser("12345678900", "116", "116", "70", df.format(dt));
        System.out.println(a);
    }

    @Test
    public void queryServiceStationTest() {
        List<Map<String, Object>> map = serviceStationMapper.queryServiceStation("116", "34", "1500");
        for (Map<String, Object> ss : map)
            System.out.println(ss);
    }


    @Test
    public void queryCarTest() {
        System.out.println(tcarMapper.queryCar("121.560934912226", "31.3019976198599", "150"));
    }

    @Test
    public void queryLoLaTest() {
        List<QueryLoLa> list = queryLoLaMapper.queryLoLa("171","2015-11-1","2015-11-2");
        System.out.println(list.size());
    }

    @Test
    public void addUserTest() {
        System.out.println(loginServletDao.addUser("段", "123", "1233211233", "1", "轮麦收割"));
    }

    @Test
    public void insertTcarWhileRegisterTest() {
        System.out.println(tuserMapper.insertTcarWhileRegister("132"));
    }

    @Test
    public void queryCarByTelTest() {
        List<Map<String, Object>> list = tcarMapper.queryCarByTel("10000000019");
        System.out.println(list.size());
        System.out.println(list.get(0).toString());
        System.out.println(list);
    }

    @Test
    public void queryServiceStationByPhoneTest() {
        System.out.println(serviceStationMapper.queryServiceStationByPhone("121.561411", "31.301739", "89290786"));
    }

    @Test
    public void queryMapDriverTest() {
        List<Map<String, Object>> ll = tuserMapper.queryMapDriverByType("121.561411", "31.301739", "15000", 1);
        for (Map<String, Object> map : ll)
            System.out.println(map);
        System.out.println(ll.size());

    }

    @Test
    public void queryMapDriverPhone() {
        List<Map<String, Object>> ll = tuserMapper.queryMapDriverPhone("121.561411", "31.301739", "13852109092");
        for (Map<String, Object> map : ll)
            System.out.println(map);
        List<Map<String, Object>> mm = ll.stream().distinct().collect(Collectors.toList());
        mm.forEach(o-> System.out.println(o));

    }

    @Test
    public void chuandiTest(){
        int i=3;
        int j=4;
        System.out.println(i);
    }

    @Test
    public void queryMapAllTest(){
        List<Map<String,Object>> ll=tuserMapper.queryMapAll("121.561411", "31.301739", "1500000");
        System.out.println(ll.size());
    }

    //htt 填写车辆lo,la
    @Test
    public void carNullTest(){
        double lo;
        double la;
        Integer carId=null;
        Random random = new Random();
        List<Map<String, Object>> data = null;
        //取出所有车户数据
        data=tcarMapper.selectAll();
        System.out.println("data size "+data.size());
        if(data.size()>0)
        {

            for(int i=0;i<data.size();i++)
            {
                lo=117.333424620056;
                la=34.2866402303996;
                if(data.get(i).get("LO")==null)
                {
                    System.out.println("i="+i+" lo:"+data.get(i).get("LO"));
                    double temp=Math.abs(random.nextDouble())*0.1;
                    lo=lo+temp;
                    la=la+temp;
                    carId=(Integer)data.get(i).get("CARID");
                    tcarMapper.updateNullCar(lo,la,carId);
                }
            }
        }
    }


}
