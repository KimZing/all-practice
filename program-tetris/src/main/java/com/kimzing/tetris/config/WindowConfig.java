package com.kimzing.tetris.config;

public class WindowConfig {
    // 窗口的类
    private String className;
    // 窗口的顶点x,y
    private int x;
    private int y;
    // 窗口的宽度高度
    private int w;
    private int h;

    // 获取类名称
    public String getClassName() {
        return className;
    }

    /**
     * 获取窗口的顶点坐标
     *
     * @return
     */
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * 获取窗口的宽度和高度
     *
     * @return
     */
    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    // 构造函数
    public WindowConfig(String className, int x, int y, int w, int h) {
        super();
        this.className = className;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

}
