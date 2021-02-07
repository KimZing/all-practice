package com.kimzing.generator.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.kimzing.utils.string.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kimzing.generator.param.Parameters.*;

/**
 * 自定义配置.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/7/5 15:41
 */
public class InjectionConfiguration {

    public static InjectionConfig getInjectionConfig() {

        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("moduleName", moduleName);
                map.put("packageName", packageName);
                map.put("classPrefixName", classPrefixName);
                map.put("packageParent", packageParent);
                this.setMap(map);
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        // xml配置
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return generaPath + "/src/main/resources/mapper/"
                        + StringUtil.upperFirstChar(classPrefixName) + "Mapper" + StringPool.DOT_XML;
            }
        });

        focList.add(new FileOutConfig("/templates/exception.properties.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return generaPath + "/src/main/resources/exception.properties";
            }
        });

        focList.add(new FileOutConfig("/templates/domain/save-dto.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return generaPath + "/src/main/java/" + packageParent.replace(".", "/") + "/" + moduleName
                        + "/domain/" + packageName + "/" + StringUtil.upperFirstChar(classPrefixName) + "SaveDTO.java";
            }
        });

        focList.add(new FileOutConfig("/templates/domain/update-dto.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return generaPath + "/src/main/java/" + packageParent.replace(".", "/") + "/" + moduleName
                        + "/domain/" + packageName + "/" + StringUtil.upperFirstChar(classPrefixName) + "UpdateDTO.java";
            }
        });

        focList.add(new FileOutConfig("/templates/domain/query-dto.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return generaPath + "/src/main/java/" + packageParent.replace(".", "/") + "/" + moduleName
                        + "/domain/" + packageName + "/" + StringUtil.upperFirstChar(classPrefixName) + "QueryDTO.java";
            }
        });

        focList.add(new FileOutConfig("/templates/domain/bo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return generaPath + "/src/main/java/" + packageParent.replace(".", "/") + "/" + moduleName
                        + "/domain/" + packageName + "/" + StringUtil.upperFirstChar(classPrefixName) + "BO.java";
            }
        });

        // 是否生成缓存模板
        if (cache) {
            focList.add(new FileOutConfig("/templates/domain/cache-po.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return generaPath + "/src/main/java/" + packageParent.replace(".", "/") + "/" + moduleName
                            + "/repository/" + packageName + "/" + StringUtil.upperFirstChar(classPrefixName) + "CachePO.java";
                }
            });

            focList.add(new FileOutConfig("/templates/cache-repository.java.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return generaPath + "/src/main/java/" + packageParent.replace(".", "/") + "/" + moduleName
                            + "/repository/" + packageName + "/" + StringUtil.upperFirstChar(classPrefixName) + "CacheRepository.java";
                }
            });
        }

        injectionConfig.setFileOutConfigList(focList);
        return injectionConfig;
    }

}
