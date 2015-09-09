package test;

import com.smartamd.mapper.LoginMapper;
import com.smartamd.mapper.TuserMapper;
import com.smartamd.service.LoginServletDao;
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
    public void insertTuserTest(){
        loginMapper.insertTuser("段亦逍");
    }

    @Test
    public void alterUserInformationPhoneTest() {
        loginServletDao.alterUserInformationPhone(470, "1", "上海市");
    }

    @Test
    public void interParseTest(){
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
        List<Map<String,Object>> list = null;
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
    public void queryMapByPhoneTest(){
        List<Map<String,Object>> list1=null;
        List<Map<String,Object>> list2=new ArrayList<>();
        Map<String, Object> x = tuserMapper.queryMapFarmerByPhone("121.56425476074219", "31.29883575439453", "123456789");
        System.out.println(x);
    }

    @Test
    public void sqrtTest(){
//        double a=1.23456789d;
//        double b = (double)(Math.round(a * 1000))/1000;
//        float c=1234f;
//        float d=c/1000;
//        float e=1;
//        System.out.printf("%.3f;",a);
//
        int a=9;
        System.out.println(Math.sqrt((int)a));

    }

    @Test
    public void queryMapDriverByTypeTest(){
        List<Map<String,Object>> list=new ArrayList<>();
        list=tuserMapper.queryMapDriverByType("121.560924939438","31.3019659047683","15",2);
        System.out.println("test");
        System.out.println(list);
    }

    @Test
    public void stringTest(){
        String a="-1";
        System.out.println("-1".equals(a));
    }

}
