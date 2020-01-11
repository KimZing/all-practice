package com.kimzing.tetris.ui;

import java.awt.*;

public class WindowStart extends WindowPaint {

    public WindowStart(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    protected void paint(Graphics g) {
        this.paintWindow(g);
    }
}
