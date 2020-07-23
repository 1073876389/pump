package com.xuwx.pump.mbg;


import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *@ClassName Generator
 *@Description 用于生产MBG的代码
 *@Author wx.Xu
 *Date 2020/7/14 9:58
 *Version 1.0
 */
public class Generator {
    public static void main(String[] args) throws Exception {
        //MGB 执行过程中的警告信息
        List<String> warnings = new ArrayList<>();
        //当生成的代码重复时,覆盖原代码
        boolean overwrite = true;
        //读取我们的MGB配置文件
        InputStream is = Generator.class.getResourceAsStream("/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        is.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);

        //创建MBG
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        //执行生成代码
        myBatisGenerator.generate(null);
        //控制台输出警告信息
        for (String warning:warnings){
            System.out.println(warning);
        }

    }
}
