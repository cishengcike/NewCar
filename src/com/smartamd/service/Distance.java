package com.smartamd.service;

/**
 * Created by Aaron on 2015/7/20.
 */
public class Distance {
    public double getDistance(double lo1,double la1,double lo2,double la2){
        double pk,a1,a2,b1,b2,t1,t2,t3,tt,result;
        double r=6370996.81;
        double pr=Math.PI;
        pk=pr/180;
        //第一个坐标点的纬度，经度弧度表示：a1,a2
        //第二个坐标点的纬度，经度弧度表示：b1,b2
        a1=la1*pk;
        a2=lo1*pk;
        b1=la2*pk;
        b2=lo2*pk;
        //结果计算
        t1=Math.cos(a1)*Math.cos(a2)*Math.cos(b1)*Math.cos(b2);
        t2=Math.cos(a1)*Math.sin(a2)*Math.cos(b1)*Math.sin(b2);
        t3=Math.sin(a1)*Math.sin(b1);
        tt=Math.acos(t1+t2+t3);
        result=tt*r;
        return result;

    }
}
