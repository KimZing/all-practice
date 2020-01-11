package com.kimzing.tetris.ui;

import com.kimzing.tetris.config.GameConfig;

import java.awt.*;

public class WindowGame extends WindowPaint {
    // 点坐标位移的参数
    private static int BORDER_ROW = GameConfig.getFRAM_CONFIG().getBorderRow();
    private static int max_shadow = GameConfig.getFRAM_CONFIG().getMaxShadow();

    public WindowGame(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    protected void paint(Graphics g) {
        this.paintWindow(g);
        if (this.gameDto.getGameRect() == null) {
            return;
        }
        /**
         * 获取方块的坐标
         */
        Point[] ps = this.gameDto.getGameRect().getRectPoint();
        this.drawShadow(ps, g);
        //绘制活动方块
        this.drawMainRect(ps, g);
        //绘制游戏地图
        this.drawMap(g);
        //绘制暂停图片
        if (this.gameDto.isPause()) {
            this.paintAtCenter(Images.PAUSE, g);
        }

    }

    //绘制游戏地图
    private void drawMap(Graphics g) {
        // 打印地图
        boolean[][] map = this.gameDto.getGameMap();
        //TODO 如果失败显示的图形
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y]) {
                    paintByPoint(x, y, this.gameDto.isSetStart() ? this.gameDto.getLevel() % 8 : 8, g);
                }
            }
        }
    }

    //绘制活动方块
    private void drawMainRect(Point[] ps, Graphics g) {

        int typeCode = this.gameDto.getGameRect().getTypeCode();
        //循环绘制每个方块的位置
        for (int i = 0; i < ps.length; i++) {
            paintByPoint(ps[i].x, ps[i].y, typeCode + 1, g);
        }
    }

    // 绘制阴影部分
    private void drawShadow(Point[] ps, Graphics g) {
        // 判断用户的设置
        if (!this.gameDto.isShowShadow()) {
            return;
        }
        // 左侧的x坐标
        int leftX = ps[1].x;
        // 右侧的x坐标
        int rightX = ps[1].x;
        for (Point point : ps) {
            leftX = leftX < point.x ? leftX : point.x;
            rightX = rightX > point.x ? rightX : point.x;
        }
        // 根据当前的等级显示不同的阴影
        int nowLevel = this.gameDto.getLevel() % max_shadow;
        g.drawImage(Images.SHADOW, this.x + (leftX << BORDER_ROW) + BORDER,
                this.y + BORDER, this.x + BORDER + (rightX + 1 << BORDER_ROW),
                this.y + this.h - BORDER, nowLevel << BORDER_ROW, 0,
                (nowLevel << BORDER_ROW) + (PADDING << 1), PADDING << 1, null);
    }

    // 根据点来绘制该点上的图形，typecodeyo你过来判断该图形的颜色
    public void paintByPoint(int x, int y, int typeCode, Graphics g) {
        //解决游戏失败后的最上方不显示的bug
        typeCode = this.gameDto.isSetStart() ? typeCode : 8;
        g.drawImage(Images.Rect, this.x + BORDER + (x << BORDER_ROW), this.y
                        + BORDER + (y << BORDER_ROW), this.x + BORDER
                        + ((x + 1) << BORDER_ROW), this.y + BORDER
                        + ((y + 1) << BORDER_ROW), (typeCode) << BORDER_ROW, 0,
                (typeCode + 1) << BORDER_ROW, 1 << BORDER_ROW, null);
    }
}
