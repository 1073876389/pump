package com.xuwx.pump.config;/**
 * Created by xuwx on 2020/7/14.
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 *@ClassName MyBatisConfig
 *@Description MyBatis 配置类   用于配置需要动态生成mapper接口的路径
 *@Author wx.Xu
 *Date 2020/7/14 10:18
 *Version 1.0
 */
@Configuration
@MapperScan("com.xuwx.pump.mbg.mapper")
public class MyBatisConfig {
}
