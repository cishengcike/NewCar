package com.smartamd.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by aaron on 15-9-15.
 */
public class CIDResolver {

    public static List<String> getCIDList(List<Map<String,Object>> list,String userID){
        List<String> cid_list = new ArrayList<>();
        Iterator<Map<String,Object>> iterator=list.iterator();
        while(iterator.hasNext()){
            Map<String,Object> map=iterator.next();
            if(Integer.parseInt(userID)==(int)map.get("USERID"))
                iterator.remove();
        }
        for(Map<String,Object> map:list) {
            if(map.get("CID")!=null)
                cid_list.add(map.get("CID").toString());
        }
        return cid_list;

    }

    public static List<String> getCIDList(Map<String,Object> map){
        List<String> cid_list=new ArrayList<>();
        if(map.get("CID")!=null)
            cid_list.add(map.get("CID").toString());
        return cid_list;
    }
}
