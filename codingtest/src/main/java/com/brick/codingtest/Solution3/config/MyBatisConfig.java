package com.brick.codingtest.Solution3.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.brick.codingtest.Solution3.mapper")
public class MyBatisConfig {

}