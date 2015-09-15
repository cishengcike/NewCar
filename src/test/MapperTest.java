package test;

import com.gexin.rp.sdk.base.impl.Target;
import com.smartamd.mapper.LoginMapper;
import com.smartamd.mapper.TuserMapper;
import com.smartamd.service.CIDResolver;
import com.smartamd.service.LoginServletDao;
import com.smartamd.service.PushToList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aaron on 15-8-13.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:/home/aaron/IdeaProjects/NewCar/web/WEB-INF/dispatcher-servlet.xml")
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
        loginServletDao.addUser("黄师姐", "123", "10000000001", "1", "水稻插秧");


    }

    @Test
    public void updateCarTypeTest() {
        Map<String, String> map = new HashMap<String, String>();

        int i = loginMapper.updateCarType("10000000001", "9");
        System.out.println(i);
    }

    @Test
    public void queryCarTypeTest() {
        System.out.println(loginMapper.queryCarType("轮麦收割"));
    }

    @Test
    public void insertTuserTest() {
        loginMapper.insertTuser("段亦逍");
    }

    @Test
    public void alterUserInformationPhoneTest() {
        loginServletDao.alterUserInformationPhone(470, "1", "上海市");
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

//        System.out.println("全部数据为："+list);
//
//        JSONArray jsonArray = JSONArray.fromObject(list);
//        System.out.println("json数组为"+ jsonArray);
//        JSONObject jsonObject = jsonArray.getJSONObject(0);
//        System.out.println("jsonobject为" + jsonObject);
//        jsonArray.clear();
//        jsonArray.add(0, "this is a test");
//        jsonObject.element("test", jsonArray);
//        System.out.println("新jsonovject为" + jsonObject);
//        JSONArray test = jsonObject.getJSONArray("test");
//        System.out.println("test为"+test);

    }

    @Test
    public void queryMapByPhoneTest() {
        List<Map<String, Object>> list1 = null;
        List<Map<String, Object>> list2 = new ArrayList<>();
        Map<String, Object> x = tuserMapper.queryMapFarmerByPhone("121.56425476074219", "31.29883575439453", "123456789");
        System.out.println(x);
        System.out.printf("");
    }



    @Test
    public void queryMapDriverByTypeTest() {
        List<Map<String, Object>> list = new ArrayList<>();
        List<String> cid_list = new ArrayList<>();
        list = tuserMapper.queryMapDriverByType("121.560924939438", "31.3019659047683", "15", 1);
        //cidResolver
        cid_list=CIDResolver.getCIDList(list);

        int i=0;
        for(String cid : cid_list)
            System.out.println(i++ +":" + cid);

    }

    @Test
    public void updateTuserCIDTest() {
        tuserMapper.updateUserCID("12000000002", "12345");
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
    public void pushToPhoneTest() {
        List<String> list = new ArrayList<>();
        list.add("b6eab2e9c0ea0466fe1505725bfceee7");

        pushToList.PushToPhone("黄师姐", "农机手", "你个逗，你要的payload", list);

    }


}
