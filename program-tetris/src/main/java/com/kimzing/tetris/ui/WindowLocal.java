package com.kimzing.tetris.ui;

import java.awt.*;

public class WindowLocal extends WindowData {

    public WindowLocal(int x, int y, int w, int h) {
        super(x, y, w, h);

    }

    @Override
    protected void paint(Graphics g) {
        this.paintWindow(g);
        g.drawImage(Images.LOCAL, this.x + PADDING, this.y + PADDING, null);
        this.showData(this.gameDto.getLocalRecoder(), g);
    }
}
