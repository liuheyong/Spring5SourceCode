package com.study.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author: liuheyong
 * @create: 2019-12-30
 */
@ComponentScan("com.study.demo")
@EnableAspectJAutoProxy(proxyTargetClass = false)
@Configuration
public class Config {
}
