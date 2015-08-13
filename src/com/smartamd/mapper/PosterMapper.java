package com.smartamd.mapper;

import com.smartamd.model.Poster;

public interface PosterMapper {
    int deleteByPrimaryKey(Integer posterid);

    int insert(Poster record);

    int insertSelective(Poster record);

    Poster selectByPrimaryKey(Integer posterid);

    int updateByPrimaryKeySelective(Poster record);

    int updateByPrimaryKey(Poster record);
}