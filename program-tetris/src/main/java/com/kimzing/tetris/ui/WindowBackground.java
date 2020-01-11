package com.kimzing.tetris.ui;

import com.kimzing.tetris.config.GameConfig;

import java.awt.*;

public class WindowBackground extends WindowPaint {

    public WindowBackground(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    protected void paint(Graphics g) {
        int imgId = this.gameDto.getLevel() % Images.BACKGROUND.size();
        g.drawImage(Images.BACKGROUND.get(imgId), 0, 0, GameConfig
                .getFRAM_CONFIG().getWidth(), GameConfig.getFRAM_CONFIG()
                .getHeight(), null);
    }

}
