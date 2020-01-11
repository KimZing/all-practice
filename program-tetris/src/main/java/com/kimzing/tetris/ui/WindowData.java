package com.kimzing.tetris.ui;

import com.kimzing.tetris.config.GameConfig;
import com.kimzing.tetris.dto.Player;

import java.awt.*;
import java.util.List;

public abstract class WindowData extends WindowPaint {
    // 最大的显示数据量
    private static int maxData = GameConfig.getFRAM_CONFIG().getMaxData();
    // 第一个的xy坐标
    private int startY;
    private int startX;
    // 每个进度条的间距
    private int space;

    protected WindowData(int x, int y, int w, int h) {
        super(x, y, w, h);
        // 初始化间距
        space = (this.h - (PADDING << 1) - Images.LOCAL.getHeight(null) - Images.COLOR
                .getHeight(null) * maxData)
                / maxData;
        // 初始化x.y
        startX = this.x + (PADDING << 1);
        startY = this.y + PADDING + Images.LOCAL.getHeight(null) + space;
    }

    // 显示数据的方法
    protected void showData(List<Player> players, Graphics g) {
        // 获取循环次数
        for (int i = 0; i < maxData; i++) {
            // 获取当前对象
            Player pla = players.get(i);
            // 获得当前对象的分数
            int score = this.gameDto.getScore();
            // 如果分数大于记录分数，则其等于记录分数，使得百分比等于1
            score = score > pla.getScore() ? pla.getScore() : score;
            String strScore = pla.getScore() == 0 ? "Null" : Integer
                    .toString(pla.getScore());
            // 绘制进度条
            this.drawColorRect(startX,
                    startY + (space + Images.COLOR.getHeight(null)) * i,
                    COLOR_W, pla.getName(), strScore, score,
                    (double) pla.getScore(), g);
        }
    }
}
