package com.kimzing.tetris.ui;

import java.awt.*;

public class WindowLevel extends WindowPaint {

    private static final int LEVEL_W = Images.LEVEL.getWidth(null);
    private static final int LEVEL_H = Images.LEVEL.getHeight(null);
    private static int CENT_X;
    private static int MAXBIT = 2;
    private static int NUMBER_Y = LEVEL_H + BORDER;
    private static int NUMBER_X = CENT_X + LEVEL_W / 2;

    public WindowLevel(int x, int y, int w, int h) {
        super(x, y, w, h);
        CENT_X = this.w - LEVEL_W >> 1;
    }

    @Override
    protected void paint(Graphics g) {
        this.paintWindow(g);
        // 绘制等级标题
        g.drawImage(Images.LEVEL, this.x + CENT_X, this.y + BORDER, null);
        // 绘制等级,显示数字
        this.drawNumberLeftPad(this.x + NUMBER_X, this.y + NUMBER_Y,
                this.gameDto.getLevel(), MAXBIT, g);

    }

}
