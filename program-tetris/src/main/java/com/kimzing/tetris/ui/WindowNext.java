package com.kimzing.tetris.ui;

import java.awt.*;

public class WindowNext extends WindowPaint {

    public WindowNext(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    protected void paint(Graphics g) {
        this.paintWindow(g);
        if (this.gameDto.isSetStart()) {
            paintAtCenter(Images.NEXT_RECT[this.gameDto.getNext()], g);
        }

    }

}
