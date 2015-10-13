package test;

import com.gexin.rp.sdk.base.impl.Target;
import com.smartamd.mapper.LoginMapper;
import com.smartamd.mapper.TuserMapper;
import com.smartamd.model.Tuser;
import com.smartamd.service.CIDResolver;
import com.smartamd.service.LoginServletDao;
import com.smartamd.service.PushToList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by aaron on 15-8-13.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:/home/aaron/IdeaProjects/WEB/NewCar_IDEA/web/WEB-INF/dispatcher-servlet.xml")
public class MapperTest {

    @Autowired
    private LoginServletDao loginServletDao;
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private TuserMapper tuserMapper;

    private PushToList pushToList;

    private CIDResolver cidResolver;

    @Test
    public void addUserTest() {
        loginServletDao.addUser("?????", "123", "10000000001", "1", "???????");


    }





    @Test
    public void insertTuserTest() {
        loginMapper.insertTuser("∂Œ“‡Â–");
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
    public void queryMapTest() {
        List<Map<String, Object>> list = null;
        list = tuserMapper.queryMapDriver("121.56095123291016", "31.30206871032715", "500000");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        System.out.println(list.get(4));

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
        List<Map<String, Object>> list2 = new ArrayList<>();
        Map<String, Object> x = tuserMapper.queryMapFarmerByPhone("121.56425476074219", "31.29883575439453", "123456789");
        System.out.println(x);
        System.out.printf("");
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
    public void updateTuserTest() {
        tuserMapper.updateUser("13655204924", "12345", "123", "12345");
    }

    @Test
    public void getTargetsTest(){
        List<String> list = new ArrayList<>();
        List<Target> list2 = new ArrayList<>();
        list.add("9495b9705e7dc951d77d9ed98bfe5f5e");
        list.add("333d19cd3a2d6cff61cef87a8236d8a5");
        list2= pushToList.getTarget(list);
        System.out.println(list2.get(0).getClientId());
    }

    @Test
    public void listTest(){
        List<String> list = new ArrayList<>();
        List<Target> targets = new ArrayList<>();
        list.add("9495b9705e7dc951d77d9ed98bfe5f5e");
        list.add("333d19cd3a2d6cff61cef87a8236d8a5");
        for(String clientID : list){
            Target target=new Target();
            target.setClientId(clientID);
            targets.add(target);

        }
        System.out.println(targets.get(1).getClientId());
    }





    @Test
    public void queryMapAllTest() {
        List<Map<String,Object>> list=null;
        list=tuserMapper.queryMapAll("121.56128", "31.301811", "15");
        System.out.println(list.size());
        for(Map<String,Object> map:list){
            System.out.println(map);
        }
        System.out.println("???????");
        Iterator<Map<String,Object>> iterator=list.iterator();
        while (iterator.hasNext()){
            Map<String,Object> map=iterator.next();
            if((int)map.get("USERID")==12)
                iterator.remove();
        }

        for(Map<String,Object> map:list){
            System.out.println(map);
        }
        System.out.println(list.size());

    }

    @Test
    public void getCIDListTest(){
        List<Map<String,Object>> list=tuserMapper.queryMapAll("121.56128", "31.301811", "15");
        System.out.println(list.size());

        List<String> cid_list = new ArrayList<>();
        for(Map<String,Object> map:list) {

            if(map.get("CID")!=null)

                cid_list.add(map.get("CID").toString());

        }
        System.out.println(cid_list.size());

    }

    @Test
    public void selectUserByPhoneTest(){
        Map<String,Object> map=tuserMapper.selectUserByPhone("13655204924", "121.56123", "31.30184");
        System.out.println(map);
    }

    @Test
    public void selectUserTelByUserIDTest(){
        System.out.println(tuserMapper.selectUserTelByUserID("12"));
    }

    @Test
    public void updateCarTypeTest(){
        int car_type=loginMapper.queryCarType("??????");
        System.out.println(car_type);
        String phone = tuserMapper.selectPhoneByUserID(34);
        System.out.println(phone);
        int res= tuserMapper.updateCarType(car_type,phone);
        System.out.println(res);
    }

    @Test
    public void selectUserFromTcarTest(){
        System.out.println(loginMapper.queryCarType("¬÷¬Û ’∏Ó "));

    }

    @Test
    public void queryCarTypeTest() {
        loginMapper.updateCarType("12345678907", loginMapper.queryCarType("¬÷¬Û ’∏Ó"));
    }

}
