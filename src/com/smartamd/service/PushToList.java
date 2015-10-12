package com.smartamd.service;

/**
 * Created by aaron on 15-9-14.
 */

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.smartamd.mapper.TuserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PushToList {
    static String appId = "6wNywD0gXwAiz1HpybrJh8";
    static String appkey = "ajsQQ3T2On6o4CdInr84M";
    static String master = "PZfj2DVPHL9EkaNKZCfl44";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";

    @Autowired
    TuserMapper tuserMapper;

    public static NotificationTemplate notificationTemplateDemo (String content, String username, String userType,String phone)  {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appkey);
        if("0".equals(userType))
            userType="农户";
        else userType="农机手";
        // 设置通知栏标题与内容
        template.setTitle(username + "(" + userType + ")" + "向您发送一条消息");
        template.setText(content);
        // 配置通知栏图标
        template.setLogo("icon.png");
        // 配置通知栏网络图标
        template.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent(phone);
        System.out.println("透传"+phone);

        return template;
    }

    //透传消息
    public static TransmissionTemplate transmissionTemplateDemo() {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appkey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent("放弃吧");
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }

    //完成对手机的推送
    public static List<Target> getTarget(List<String> list) {
        List<Target> targets = new ArrayList<>();
        for (String clientID : list) {
            Target target = new Target();
            target.setAppId(appId);
            target.setClientId(clientID);
            targets.add(target);

        }

        return targets;
    }

    //完成对手机的推送
    public static void PushToPhone(String username, String userType, String content,String phone, List<String> list) {
        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
        IGtPush push = new IGtPush(host, appkey, master);

        //通知透传模板
        NotificationTemplate template = notificationTemplateDemo(content, username, userType,phone);
//        TransmissionTemplate template = transmissionTemplateDemo();
        ListMessage message = new ListMessage();
        message.setData(template);

        //设置消息离线，并设置离线时间
        message.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);

        List<Target> targets = new ArrayList<>();
        targets = getTarget(list);
        //获取taskID
        String taskId = push.getContentId(message);
        //使用taskID对目标进行推送
        IPushResult ret = push.pushMessageToList(taskId, targets);
        //打印服务器返回信息
        System.out.println(ret.getResponse().toString());
        System.out.println(message.getData().getPushInfo());

    }


}