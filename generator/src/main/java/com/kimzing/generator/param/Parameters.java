package com.kimzing.generator.param;

import com.kimzing.generator.generator.CodeGenerator;
import com.kimzing.utils.property.PropertiesUtil;
import com.kimzing.utils.string.StringUtil;

import java.io.File;
import java.util.Map;

/**
 * 参数列表.
 *
 * @author KimZing - kimzing@163.com
 * @since 2020/7/3 13:24
 */
public class Parameters {
    public static String generaPath = System.getProperty("user.home") + File.separator + "Desktop";
    public static String outputDir = generaPath + "/src/main/java";
    public static Map<String, String> properties = PropertiesUtil.getAllProperties(CodeGenerator.class.getResource("/application.properties").getFile());
    public static Boolean baseResultMap = Boolean.valueOf(properties.get("baseResultMap"));
    public static Boolean fileOverride = Boolean.valueOf(properties.get("fileOverride"));
    public static Boolean baseColumnList = Boolean.valueOf(properties.get("baseColumnList"));
    public static Boolean needOpenDir = Boolean.valueOf(properties.get("needOpenDir"));
    public static String moduleName = properties.get("moduleName");
    public static String packageParent = properties.get("packageParent");
    public static String packageName = properties.get("packageName");
    public static Boolean controller =  Boolean.valueOf(properties.get("controller"));
    public static Boolean service =  Boolean.valueOf(properties.get("service"));
    public static Boolean serviceImpl =  Boolean.valueOf(properties.get("serviceImpl"));
    public static Boolean cache =  Boolean.valueOf(properties.get("cache"));
    public static Boolean mapper =  Boolean.valueOf(properties.get("mapper"));
    public static Boolean entity =  Boolean.valueOf(properties.get("entity"));
    public static Boolean xml =  Boolean.valueOf(properties.get("xml"));
    public static String serviceImplType = properties.get("serviceImplType");
    public static String entityName = properties.get("entityName");
    public static String mapperName = properties.get("mapperName");
    public static String xmlName = properties.get("xmlName");
    public static String serviceName = properties.get("serviceName");
    public static String serviceImplName = properties.get("serviceImplName");
    public static String controllerName = properties.get("controllerName");
    public static String author = properties.get("author");
    public static String url = properties.get("url");
    public static String username = properties.get("username");
    public static String password = properties.get("password");
    public static String table = properties.get("table");
    public static String classPrefixName = StringUtil.toCamelCase(table);
    public static String[] superEntityColumns = properties.get("superEntityColumns").split(",");

}
