package com.shsh.ic.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.shsh.ic.model.dao")
public class DBConfig {

}
