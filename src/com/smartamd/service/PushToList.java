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
        // ����APPID��APPKEY
        template.setAppId(appId);
        template.setAppkey(appkey);
        if("0".equals(userType))
            userType="ũ��";
        else userType="ũ����";
        // ����֪ͨ������������
        template.setTitle(username + "(" + userType + ")" + "��������һ����Ϣ");
        template.setText(content);
        // ����֪ͨ��ͼ��
        template.setLogo("icon.png");
        // ����֪ͨ������ͼ��
        template.setLogoUrl("");
        // ����֪ͨ�Ƿ����壬�𶯣����߿����
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        // ͸����Ϣ���ã�1Ϊǿ������Ӧ�ã��ͻ��˽��յ���Ϣ��ͻ���������Ӧ�ã�2Ϊ�ȴ�Ӧ������
        template.setTransmissionType(2);
        template.setTransmissionContent(phone);
        System.out.println("͸��"+phone);

        return template;
    }

    //͸����Ϣ
    public static TransmissionTemplate transmissionTemplateDemo() {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appkey);
        // ͸����Ϣ���ã�1Ϊǿ������Ӧ�ã��ͻ��˽��յ���Ϣ��ͻ���������Ӧ�ã�2Ϊ�ȴ�Ӧ������
        template.setTransmissionType(2);
        template.setTransmissionContent("������");
        // ���ö�ʱչʾʱ��
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }

    //��ɶ��ֻ�������
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

    //��ɶ��ֻ�������
    public static void PushToPhone(String username, String userType, String content,String phone, List<String> list) {
        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
        IGtPush push = new IGtPush(host, appkey, master);

        //֪ͨ͸��ģ��
        NotificationTemplate template = notificationTemplateDemo(content, username, userType,phone);
//        TransmissionTemplate template = transmissionTemplateDemo();
        ListMessage message = new ListMessage();
        message.setData(template);

        //������Ϣ���ߣ�����������ʱ��
        message.setOffline(true);
        //������Чʱ�䣬��λΪ���룬��ѡ
        message.setOfflineExpireTime(24 * 1000 * 3600);

        List<Target> targets = new ArrayList<>();
        targets = getTarget(list);
        //��ȡtaskID
        String taskId = push.getContentId(message);
        //ʹ��taskID��Ŀ���������
        IPushResult ret = push.pushMessageToList(taskId, targets);
        //��ӡ������������Ϣ
        System.out.println(ret.getResponse().toString());
        System.out.println(message.getData().getPushInfo());

    }


}