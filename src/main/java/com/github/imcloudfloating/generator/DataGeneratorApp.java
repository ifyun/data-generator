package com.github.imcloudfloating.generator;

import com.github.imcloudfloating.generator.component.Generator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@Slf4j
@SpringBootApplication
public class DataGeneratorApp implements CommandLineRunner {

    @Resource
    private Generator generator;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DataGeneratorApp.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }

    @Override
    public void run(String... args) {
        try {
            generator.generate();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
