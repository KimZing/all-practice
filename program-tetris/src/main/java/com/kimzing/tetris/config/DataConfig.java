package com.kimzing.tetris.config;

import org.dom4j.Element;

public class DataConfig {
    private DataInterfaceConfig dataA;
    private DataInterfaceConfig dataB;

    public DataConfig(Element data) {
        // 初始化
        dataA = new DataInterfaceConfig(data.element("dataA"));
        dataB = new DataInterfaceConfig(data.element("dataB"));
    }

    public DataInterfaceConfig getDataA() {
        return dataA;
    }

    public DataInterfaceConfig getDataB() {
        return dataB;
    }

}
