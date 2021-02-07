package com.kimzing.generator.config;

import com.baomidou.mybatisplus.generator.config.PackageConfig;

import static com.kimzing.generator.param.Parameters.*;
import static com.kimzing.generator.param.Parameters.packageName;

/**
 * 包配置.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/7/5 15:45
 */
public class PackageConfiguration {

    public static PackageConfig getPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(packageParent);
        packageConfig.setModuleName(moduleName);
        packageConfig.setController("controller." + packageName);
        packageConfig.setService("service." + packageName);
        packageConfig.setServiceImpl("service." + packageName + ".impl");
        packageConfig.setMapper("repository." + packageName);
        packageConfig.setEntity("repository." + packageName);
        packageConfig.setXml("mapper");

        return packageConfig;
    }

}
