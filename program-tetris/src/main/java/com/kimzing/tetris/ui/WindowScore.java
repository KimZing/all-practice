package com.kimzing.tetris.ui;

import com.kimzing.tetris.config.GameConfig;

import java.awt.*;

public class WindowScore extends WindowPaint {
    // 最大位数
    private static final int MAXBIT = 5;
    // 分数的默认x坐标
    private static int COM_NUM_X = 0;
    // 分数的Y坐标
    private static int SCORE_Y = PADDING;

    // 消行数字的y坐标
    private static int LINE_Y = Images.DISAPPEAR.getHeight(null) + BORDER * 3;
    // 获得"分数"的高度，并被用作值槽的高度
    private static int STRING_H = Images.SCORE.getHeight(null);
    // 字符的初始xy坐标
    private static int COM_STRING_X;
    private static int COM_STRING_Y;
    private static int COM_LINE_X;
    private static int COM_LINE_Y;

    public WindowScore(int x, int y, int w, int h) {
        super(x, y, w, h);
        // 初始化分数的x坐标
        COM_NUM_X = this.w - PADDING - NUMBER_W * MAXBIT;
        // 图片的初始x坐标
        COM_STRING_X = this.x + (PADDING << 1);
        // 图片的初始y坐标
        COM_STRING_Y = this.y + PADDING;
        // 值槽的x
        COM_LINE_X = this.x + (PADDING << 1);
        // 值槽的y
        COM_LINE_Y = this.y + PADDING;
    }

    @Override
    protected void paint(Graphics g) {
        this.paintWindow(g);
        // 分数
        g.drawImage(Images.SCORE, COM_STRING_X, COM_STRING_Y, null);
        this.drawNumberLeftPad(this.x + COM_NUM_X, this.y + SCORE_Y,
                this.gameDto.getScore(), MAXBIT, g);
        // 消行
        g.drawImage(Images.DISAPPEAR, COM_STRING_X, COM_STRING_Y + PADDING * 3,
                null);
        this.drawNumberLeftPad(this.x + COM_NUM_X, this.y + LINE_Y,
                this.gameDto.getLine(), MAXBIT, g);
        // 值槽
        int rmLine = this.gameDto.getLine();
        int expW = this.w - (PADDING << 2);
        int expY = COM_LINE_Y + (STRING_H << 1) + (PADDING << 1);
        // 升级值槽
        this.drawColorRect(COM_LINE_X, expY, expW, "下一级", null,
                (rmLine % GameConfig.getSYSTEM_CONFIG().getLineLevel()), GameConfig.getSYSTEM_CONFIG().getLineLevel(), g);
    }

}
