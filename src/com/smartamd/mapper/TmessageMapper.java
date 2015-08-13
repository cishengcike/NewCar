package com.smartamd.mapper;

import com.smartamd.model.Tmessage;

public interface TmessageMapper {
    int deleteByPrimaryKey(Integer messageid);

    int insert(Tmessage record);

    int insertSelective(Tmessage record);

    Tmessage selectByPrimaryKey(Integer messageid);

    int updateByPrimaryKeySelective(Tmessage record);

    int updateByPrimaryKey(Tmessage record);
}