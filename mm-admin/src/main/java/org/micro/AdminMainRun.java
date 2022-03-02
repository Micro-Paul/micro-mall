package org.micro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author micro-paul
 * @date 2021年12月22日 10:11
 */
@EnableOpenApi
@SpringBootApplication
@MapperScan("org.micro.mappers")
public class AdminMainRun {

    public static void main(String[] args) {
        SpringApplication.run(AdminMainRun.class, args);
    }
}
