package com.kimzing.generator.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import static com.kimzing.generator.config.DataSourceConfiguration.getDataSourceConfig;
import static com.kimzing.generator.config.GlobalConfiguration.getGlobalConfig;
import static com.kimzing.generator.config.InjectionConfiguration.getInjectionConfig;
import static com.kimzing.generator.config.PackageConfiguration.getPackageConfig;
import static com.kimzing.generator.config.StrategyConfiguration.getStrategyConfig;
import static com.kimzing.generator.config.TemplateConfiguration.getTemplateConfig;

public class CodeGenerator {

    public static void generate() {
        // 代码生成器
        AutoGenerator generator = new AutoGenerator();
        // 全局配置
        GlobalConfig globalConfig = getGlobalConfig();
        generator.setGlobalConfig(globalConfig);
        // 数据源配置
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        generator.setDataSource(dataSourceConfig);
        // 包配置
        PackageConfig packageConfig = getPackageConfig();
        generator.setPackageInfo(packageConfig);
        // 自定义配置
        InjectionConfig injectionConfig = getInjectionConfig();
        generator.setCfg(injectionConfig);
        // 配置模板
        TemplateConfig templateConfig = getTemplateConfig();
        generator.setTemplate(templateConfig);
        // 策略配置
        StrategyConfig strategy = getStrategyConfig();
        generator.setStrategy(strategy);
        // 设置模板并生成
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();
    }

}