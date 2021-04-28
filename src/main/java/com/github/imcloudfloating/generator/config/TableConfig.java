package com.github.imcloudfloating.generator.config;

import lombok.Data;

import java.util.Map;

@Data
public class TableConfig {
    private String table;
    private Integer count;
    private Map<String, String> columns;
}
