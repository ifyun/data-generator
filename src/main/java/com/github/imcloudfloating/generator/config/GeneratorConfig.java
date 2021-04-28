package com.github.imcloudfloating.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Locale;

@Data
@Configuration
@ConfigurationProperties(prefix = "generator")
public class GeneratorConfig {
    private Locale locale;
    private List<TableConfig> tables;
}
