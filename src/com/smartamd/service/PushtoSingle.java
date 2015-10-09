package com.smartamd.service;


import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;

/**
 * Created by aaron on 15-8-18.
 */
public class PushtoSingle {
    static String appId = "6wNywD0gXwAiz1HpybrJh8";
    static String appkey = "ajsQQ3T2On6o4CdInr84M";
    static String master = "PZfj2DVPHL9EkaNKZCfl44";
    static String CID = "64d247c0496fe1e9a60c6dbe1653b973";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";

    public static LinkTemplate linkTemplateDemo() {
        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appkey);
        // 设置通知栏标题与内容
        template.setTitle("this is a test");
        template.setText("Hello World!");
        // 配置通知栏图标
        template.setLogo("icon.png");
        // 配置通知栏网络图标，填写图标URL地址
        template.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        // 设置打开的网址地址
        template.setUrl("http://www.baidu.com");
        return template;
    }

    public static void main(String[] args) throws Exception {
        IGtPush push = new IGtPush(host, appkey, master);

        LinkTemplate template = linkTemplateDemo();
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        message.setPushNetWorkType(0); //可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
        Target target = new Target();

        target.setAppId(appId);
        target.setClientId(CID);
        //用户别名推送，cid和用户别名只能2者选其一
        //String alias = "个";
        //target.setAlias(alias);
        IPushResult ret = null;
        try{
            ret = push.pushMessageToSingle(message, target);
        }catch(RequestException e){
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if(ret != null){
            System.out.println(ret.getResponse().toString());
        }else{
            System.out.println("服务器响应异常");
        }
    }

}
