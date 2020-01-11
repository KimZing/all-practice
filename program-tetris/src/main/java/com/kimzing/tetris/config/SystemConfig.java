package com.kimzing.tetris.config;

import org.dom4j.Element;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SystemConfig {
    private static int maxX;

    private static int maxY;

    private static int length;
    private static int[] scores;
    // 升级的判断行数
    private static int lineLevel;
    // 方块最大的种类数
    private static int typeNumber;
    // 方块是否能移动
    private static List<Boolean> isCanMove;
    private List<Point[]> TYPE_CONFIG;

    public SystemConfig(Element system) {
        // 升级的行数
        this.lineLevel = Integer.parseInt(system.attributeValue("lineLevel"));
        this.length = Integer.parseInt(system.attributeValue("length"));
        // 获取加分数组
        scores = new int[this.length];
        for (int i = 0; i < this.length; i++) {
            scores[i] = Integer.parseInt(system.attributeValue("l" + i));
        }
        // 游戏窗口的x.y的最大和最小值
        this.maxX = Integer.parseInt(system.attributeValue("maxX"));

        this.maxY = Integer.parseInt(system.attributeValue("maxY"));

        // 方块的种类个数
        this.typeNumber = Integer.parseInt(system.attributeValue("typeNumber"));
        // 获取system下的所有元素
        @SuppressWarnings("unchecked")
        List<Element> rectElements = system.elements();
        // 初始化TYPE_CONFIG并使长度为rectElements的长度
        this.isCanMove = new ArrayList<Boolean>(rectElements.size());
        TYPE_CONFIG = new ArrayList<Point[]>(rectElements.size());
        // 循环获得每个rect
        for (Element rectE : rectElements) {
            this.isCanMove.add(Boolean.parseBoolean(rectE
                    .attributeValue("round")));
            // 获取每个rect下的point
            @SuppressWarnings("unchecked")
            List<Element> pointList = rectE.elements();
            // 声明一个point数组
            Point[] points = new Point[pointList.size()];
            // 循环给point[] 赋值
            for (int i = 0; i < points.length; i++) {
                points[i] = new Point(Integer.parseInt(pointList.get(i)
                        .attributeValue("x")), Integer.parseInt(pointList
                        .get(i).attributeValue("y")));
            }
            // 将point[] 添加进Type_config
            TYPE_CONFIG.add(points);
        }
    }

    public List<Point[]> getTYPE_CONFIG() {
        return TYPE_CONFIG;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int[] getScores() {
        return scores;
    }

    public int getTypeNumber() {
        return typeNumber;
    }

    public int getLineLevel() {
        return lineLevel;
    }

    public List<Boolean> getIsCanMove() {
        return isCanMove;
    }

}
