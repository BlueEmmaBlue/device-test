package com.skinairvalve.sz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.skinairvalve.sz.mapper"})
public class DeviceTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceTestApplication.class, args);
    }
}
