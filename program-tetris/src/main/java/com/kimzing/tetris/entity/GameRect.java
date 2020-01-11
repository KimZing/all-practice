package com.kimzing.tetris.entity;

import com.kimzing.tetris.config.GameConfig;

import java.awt.*;
import java.util.List;

public class GameRect {
    private Point[] rectPoints = null;
    private static int MAX_X = GameConfig.getSYSTEM_CONFIG().getMaxX();
    private static int MIN_X = 0;
    private static int MAX_Y = GameConfig.getSYSTEM_CONFIG().getMaxY();
    private static int MIN_Y = 0;
    private static final List<Point[]> TYPE_CONFIG = GameConfig
            .getSYSTEM_CONFIG().getTYPE_CONFIG();

    private int typeCode;

    public GameRect(int typeCode) {
        this.init(typeCode);
    }

    public void init(int typeCode) {
        this.typeCode = typeCode;
        // TODO根据rectNumber的值刷新方块
        Point[] points = TYPE_CONFIG.get(typeCode);
        rectPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            rectPoints[i] = new Point(points[i].x, points[i].y);
            ;
        }
    }

    // 获取Point
    public Point[] getRectPoint() {
        return rectPoints;
    }

    public boolean move(int moveX, int moveY, boolean[][] gameMap) {
        // 图形移动 不要用引用
        for (int i = 0; i < rectPoints.length; i++) {
            int newX = rectPoints[i].x + moveX;
            int newY = rectPoints[i].y + moveY;
            if (this.isOverZone(newX, newY, gameMap)) {
                return false;
            }

        }
        for (int i = 0; i < rectPoints.length; i++) {
            rectPoints[i].x += moveX;
            rectPoints[i].y += moveY;
        }
        return true;

    }

    /**
     * 旋转方法
     */
    public void round(boolean[][] gameMap) {
        // 如果出现不可移动的图形，则不执行
        List<Boolean> canRound = GameConfig.getSYSTEM_CONFIG().getIsCanMove();
        if (!canRound.get(this.typeCode)) {
            return;
        }
        for (int i = 1; i < rectPoints.length; i++) {
            int newX = rectPoints[0].y + rectPoints[0].x - rectPoints[i].y;
            int newY = rectPoints[0].y - rectPoints[0].x + rectPoints[i].x;
            if (this.isOverZone(newX, newY, gameMap)) {
                return;
            }
        }
        for (int i = 1; i < rectPoints.length; i++) {
            int newX = rectPoints[0].y + rectPoints[0].x - rectPoints[i].y;
            int newY = rectPoints[0].y - rectPoints[0].x + rectPoints[i].x;
            rectPoints[i].x = newX;
            rectPoints[i].y = newY;
        }
    }

    // 判断是否超出边界
    public boolean isOverZone(int x, int y, boolean[][] gameMap) {
        return x < MIN_X || x > MAX_X || y < MIN_Y || y > MAX_Y
                || gameMap[x][y];
    }

    /**
     * 获取方块编号
     *
     * @return
     */
    public int getTypeCode() {
        return typeCode;
    }

}
