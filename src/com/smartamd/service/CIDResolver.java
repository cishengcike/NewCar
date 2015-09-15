package com.smartamd.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by aaron on 15-9-15.
 */
public class CIDResolver {

    public static List<String> getCIDList(List<Map<String,Object>> list){
        List<String> cid_list = new ArrayList<>();
        for(Map<String,Object> map:list)
            cid_list.add(map.get("CID").toString());
        return cid_list;

    }

    public static List<String> getCIDList(Map<String,Object> map){
        List<String> cid_list=new ArrayList<>();
        cid_list.add(map.get("CID").toString());
        return cid_list;
    }
}
