package com.kimzing.tetris.config;

import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataInterfaceConfig {
    private final String className;
    private final Map<String, String> param;

    public DataInterfaceConfig(Element dataInterfaceConfig) {
        // 获取className
        this.className = dataInterfaceConfig.attributeValue("className");
        @SuppressWarnings("unchecked")
        // 获取dataA或B元素的的所有子元素
                List<Element> elements = dataInterfaceConfig.elements();
        // 实例化Map
        param = new HashMap<String, String>();
        // 利用循环来赋值
        for (Element e : elements) {
            param.put(e.attributeValue("key"), e.attributeValue("value"));
        }
    }

    public String getClassName() {
        return className;
    }

    public Map<String, String> getParam() {
        return param;
    }

}
