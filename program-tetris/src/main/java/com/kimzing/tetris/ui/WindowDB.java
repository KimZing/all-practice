package com.kimzing.tetris.ui;

import java.awt.*;

public class WindowDB extends WindowData {

    public WindowDB(int x, int y, int w, int h) {
        super(x, y, w, h);

    }

    @Override
    protected void paint(Graphics g) {
        this.paintWindow(g);
        g.drawImage(Images.DB, this.x + PADDING, this.y + PADDING, null);
        this.showData(this.gameDto.getDbRecoder(), g);
    }
}
