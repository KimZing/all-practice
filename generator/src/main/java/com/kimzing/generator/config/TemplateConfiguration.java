package com.kimzing.generator.config;

import com.baomidou.mybatisplus.generator.config.TemplateConfig;

import static com.kimzing.generator.param.Parameters.*;

/**
 * 模板配置.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/7/5 15:43
 */
public class TemplateConfiguration {

    public static TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setController(controller ? "templates/controller.java": "");
        templateConfig.setService(service ? "templates/service.java": "");
        templateConfig.setServiceImpl(serviceImpl ? "templates/serviceImpl-" + serviceImplType + ".java": "");
        templateConfig.setMapper(mapper ? "templates/mapper.java": "");
        templateConfig.setEntity(entity ? "templates/domain/po.java": "");
        templateConfig.setXml(xml ? "templates/mapper.xml": "");
        return templateConfig;
    }

}
