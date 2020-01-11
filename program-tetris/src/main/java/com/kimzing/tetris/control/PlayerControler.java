package com.kimzing.tetris.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerControler extends KeyAdapter {
    // 游戏控制器
    private GameControler gameControler;

    // 传入一个控制器
    public PlayerControler(GameControler gameControl) {
        this.gameControler = gameControl;
    }

    /**
     * 键盘按下操作
     */
    @Override
    public void keyPressed(KeyEvent e) {
        this.gameControler.methodByKey(e.getKeyCode());
    }
}
