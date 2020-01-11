package com.kimzing.tetris.util;

import com.kimzing.tetris.config.FrameConfig;
import com.kimzing.tetris.config.GameConfig;

import javax.swing.*;
import java.awt.*;

public class CenterSet {
    static FrameConfig fcf = GameConfig.getFRAM_CONFIG();

    /**
     * 窗口居中
     *
     * @param jf
     */
    public static void setCenter(JFrame jf) {
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension screen = tool.getScreenSize();
        int x = (screen.width - jf.getWidth()) >> 1;
        int y = ((screen.height - jf.getHeight()) >> 1) - fcf.getUp();
        jf.setLocation(x, y);
    }
}
