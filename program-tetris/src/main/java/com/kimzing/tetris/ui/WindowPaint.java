package com.kimzing.tetris.ui;

import com.kimzing.tetris.config.FrameConfig;
import com.kimzing.tetris.config.GameConfig;
import com.kimzing.tetris.dto.GameDto;

import java.awt.*;

public abstract class WindowPaint {
    static FrameConfig fcf = GameConfig.getFRAM_CONFIG();
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected GameDto gameDto = null;

    // 获取值槽的宽度
    protected static final int COLOR_W = Images.COLOR.getWidth(null);
    // 获取值槽的宽度
    protected static final int COLOR_H = Images.COLOR.getHeight(null);
    // String文字和窗口的边距
    protected static final int PADDING = GameConfig.getFRAM_CONFIG()
            .getPadding();
    // 窗口的边框宽度
    protected static final int BORDER = fcf.getBorder();
    // 窗口绘制的模板

    // 窗口模板的宽度
    private static final int WINDOW_W = Images.WINDOW.getWidth(null);
    // 窗口模板的高度
    private static final int WINDOW_H = Images.WINDOW.getHeight(null);
    // 获得数字图片

    // 数字宽度
    protected static final int NUMBER_W = Images.NUMBER.getWidth(null) / 10;
    // 数字高度
    private static final int NUMBER_H = Images.NUMBER.getHeight(null);

    protected void setGameDto(GameDto gameDto) {
        this.gameDto = gameDto;
    }

    protected WindowPaint(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    protected void paintWindow(Graphics g) {
        // 左上角
        g.drawImage(Images.WINDOW, x, y, x + BORDER, y + BORDER, 0, 0, BORDER,
                BORDER, null);
        // 上横线
        g.drawImage(Images.WINDOW, x + BORDER, y, x + w - BORDER, y + BORDER,
                BORDER, 0, WINDOW_W - BORDER, BORDER, null);
        // 左竖线
        g.drawImage(Images.WINDOW, x, y + BORDER, x + BORDER, y + h - BORDER,
                0, BORDER, BORDER, WINDOW_H - BORDER, null);
        // 右上角
        g.drawImage(Images.WINDOW, x + w - BORDER, y, x + w, y + BORDER,
                WINDOW_W - BORDER, 0, WINDOW_W, BORDER, null);
        // 右竖线
        g.drawImage(Images.WINDOW, x + w - BORDER, y + BORDER, x + w, y + h
                - BORDER, WINDOW_W - BORDER, BORDER, WINDOW_W, WINDOW_H
                - BORDER, null);
        // 左下角
        g.drawImage(Images.WINDOW, x, y + h - BORDER, x + BORDER, y + h, 0,
                WINDOW_H - BORDER, BORDER, WINDOW_H, null);
        // 下横线
        g.drawImage(Images.WINDOW, x + BORDER, y + h - BORDER, x + w - BORDER,
                y + h, BORDER, WINDOW_H - BORDER, WINDOW_W - BORDER, WINDOW_H,
                null);
        // 右下角
        g.drawImage(Images.WINDOW, x + w - BORDER, y + h - BORDER, x + w,
                y + h, WINDOW_W - BORDER, WINDOW_H - BORDER, WINDOW_W,
                WINDOW_H, null);
        // 中间部分
        g.drawImage(Images.WINDOW, x + BORDER, y + BORDER, x + w - BORDER, y
                + h - BORDER, BORDER, BORDER, WINDOW_W - BORDER, WINDOW_H
                - BORDER, null);
    }

    /**
     * @param x   相对于窗体左上角x
     * @param y   相对于窗体左上角y
     * @param num 要显示的数字
     * @param g   画笔对象
     */
    protected void drawNumberLeftPad(int x, int y, int num, int maxBit,
                                     Graphics g) {
        // 把要打印是的数字转换成字符串
        String strNum = Integer.toString(num);
        // 循环绘制数字右对齐
        for (int i = 0; i < maxBit; i++) {
            // 判断是否满足绘制条件
            if (maxBit - i <= strNum.length()) {
                // 获得数字在字符串中的下标
                int idx = i - maxBit + strNum.length();
                // 吧数字number中的每一位取出
                int bit = strNum.charAt(idx) - '0';
                // 绘制数字
                g.drawImage(Images.NUMBER, x + NUMBER_W * i, y, x + NUMBER_W
                        * (i + 1), y + NUMBER_H, bit * NUMBER_W, 0, (bit + 1)
                        * NUMBER_W, NUMBER_H, null);
            }
        }
    }

    // 绘制功能
    protected abstract void paint(Graphics g);

    /**
     * 将图片居中窗口
     *
     * @param img
     * @param g
     */
    protected void paintAtCenter(Image img, Graphics g) {
        int ImgW = img.getWidth(null);
        int ImgH = img.getHeight(null);
        g.drawImage(img, this.x + (this.w - ImgW >> 1), this.y
                + (this.h - ImgH >> 1), null);
    }

    protected void drawColorRect(int x, int y, int w, String title,
                                 String content, double value, double maxValue, Graphics g) {
        // 各种值得初始化
        // 绘制背景
        g.setColor(Color.BLACK);
        g.fillRect(x, y, w, COLOR_H);
        g.setColor(Color.WHITE);
        g.fillRect(x + 1, y + 1, w - 2, COLOR_H - 2);
        g.setColor(Color.BLACK);
        g.fillRect(x + 2, y + 2, w - 4, COLOR_H - 4);
        // 百分比
        double percent = value / maxValue;
        // 求出宽度
        int width = (int) ((w - 4) * percent);
        // 求出颜色
        int subIdx = (int) (percent * COLOR_W) - 1;// 为什么减一 因为下面加了一
        // 切到257肯定无法显示
        // 绘制值槽
        g.drawImage(Images.COLOR, x + 2, y + 2, x + 2 + width, y + 2 + COLOR_H
                - 4, subIdx, 0, subIdx + 1, 30, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("黑体", Font.BOLD, 23));
        g.drawString(title, x + 4, y + 22);
        if (content != null) {
            // 绘制数值
            g.drawString(content, x + 180, y + 22);
        }
    }

}
