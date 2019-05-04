package com.robatter.app.migrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ImportResource("classpath:applicationContext.xml")
public class SpringBootMigrateApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMigrateApplication.class, args);
    }
}
