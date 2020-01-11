package com.kimzing.tetris.config;

import org.dom4j.Element;

public class ButtonConfig {
    private final int width;
    private final int height;
    private final int startX;
    private final int startY;
    private final int configX;
    private final int configY;

    public ButtonConfig(Element e) {
        Element startE = e.element("start");
        height = Integer.parseInt(e.attributeValue("h"));
        width = Integer.parseInt(e.attributeValue("w"));
        startX = Integer.parseInt(startE.attributeValue("x"));
        startY = Integer.parseInt(startE.attributeValue("y"));
        Element configE = e.element("config");
        configX = Integer.parseInt(configE.attributeValue("x"));
        configY = Integer.parseInt(configE.attributeValue("y"));

    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getConfigX() {
        return configX;
    }

    public int getConfigY() {
        return configY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
