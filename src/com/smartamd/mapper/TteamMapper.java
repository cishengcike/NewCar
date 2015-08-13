package com.smartamd.mapper;

import com.smartamd.model.Tteam;

public interface TteamMapper {
    int deleteByPrimaryKey(Integer teamid);

    int insert(Tteam record);

    int insertSelective(Tteam record);

    Tteam selectByPrimaryKey(Integer teamid);

    int updateByPrimaryKeySelective(Tteam record);

    int updateByPrimaryKey(Tteam record);
}