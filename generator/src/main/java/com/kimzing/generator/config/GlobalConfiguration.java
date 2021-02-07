package com.kimzing.generator.config;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;

import static com.kimzing.generator.param.Parameters.*;
import static com.kimzing.generator.param.Parameters.controllerName;

/**
 * 全局配置.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/7/5 15:44
 */
public class GlobalConfiguration {

    /**
     * 全局配置
     * @return
     */
    public static GlobalConfig getGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(outputDir);
        globalConfig.setSwagger2(true);
        globalConfig.setAuthor(author);
        globalConfig.setOpen(needOpenDir);
        globalConfig.setBaseResultMap(baseResultMap);
        globalConfig.setFileOverride(fileOverride);
        globalConfig.setBaseColumnList(baseColumnList);
        globalConfig.setEntityName(entityName);
        globalConfig.setMapperName(mapperName);
        globalConfig.setXmlName(xmlName);
        globalConfig.setServiceName(serviceName);
        globalConfig.setServiceImplName(serviceImplName);
        globalConfig.setControllerName(controllerName);
        return globalConfig;
    }

}
