package com.kimzing.tetris.config;

import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

public class FrameConfig {
    private final String title;
    private final int up;
    private final int width;
    private final int height;
    // 窗口的边框宽度
    private final int border;
    // 内边距
    private final int padding;
    // 方块的偏移量 向左位移5个
    private final int borderRow;
    // 阴影的最大种类数
    private final int maxShadow;
    // 显示的数据条数
    private final int maxData;
    // 将每个层的数据装入一个list
    private List<WindowConfig> configWindows;
    private ButtonConfig btnConfig;

    public FrameConfig(Element frame) {

        /**
         * 设置窗体的各个属性
         *
         */
        this.up = Integer.parseInt(frame.attributeValue("up"));
        this.title = frame.attributeValue("title");
        this.width = Integer.parseInt(frame.attributeValue("width"));
        this.height = Integer.parseInt(frame.attributeValue("height"));
        this.border = Integer.parseInt(frame.attributeValue("border"));
        this.padding = Integer.parseInt(frame.attributeValue("padding"));
        this.maxShadow = Integer.parseInt(frame.attributeValue("maxShadow"));
        this.borderRow = Integer.parseInt(frame.attributeValue("borderRow"));
        this.maxData = Integer.parseInt(frame.attributeValue("maxData"));
        /**
         * 将窗口的参数存入configWindows
         */
        @SuppressWarnings("unchecked")
        // 无视编译警告
                List<Element> windowPaints = frame.elements("windowPaint");
        configWindows = new ArrayList<WindowConfig>();
        for (Element windowPaint : windowPaints) {
            WindowConfig cfw = new WindowConfig(
                    windowPaint.attributeValue("className"),
                    Integer.parseInt(windowPaint.attributeValue("x")),
                    Integer.parseInt(windowPaint.attributeValue("y")),
                    Integer.parseInt(windowPaint.attributeValue("w")),
                    Integer.parseInt(windowPaint.attributeValue("h")));
            configWindows.add(cfw);
        }
        //初始化按钮配置
        btnConfig = new ButtonConfig(frame.element("button"));

    }

    public String getTitle() {
        return title;
    }

    public int getUp() {
        return up;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBorder() {
        return border;
    }

    public int getPadding() {
        return padding;
    }

    public List<WindowConfig> getConfigWindows() {
        return configWindows;
    }

    public int getBorderRow() {
        return borderRow;
    }

    public int getMaxShadow() {
        return maxShadow;
    }

    public int getMaxData() {
        return maxData;
    }

    public ButtonConfig getBtnConfig() {
        return btnConfig;
    }

}
