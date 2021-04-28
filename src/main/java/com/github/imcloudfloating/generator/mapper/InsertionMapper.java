package com.github.imcloudfloating.generator.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

@Mapper
public interface InsertionMapper {
    void insert(@Param("table") String table, @Param("data") HashMap<String, Object> data);
}
