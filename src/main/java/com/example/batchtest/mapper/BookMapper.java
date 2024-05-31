package com.example.batchtest.mapper;

import com.example.batchtest.dto.AladinItemDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {
    void insertBook(AladinItemDto item);
}
