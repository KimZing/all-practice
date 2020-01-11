package com.kimzing.tetris.ui;

import com.kimzing.tetris.config.FrameConfig;
import com.kimzing.tetris.config.GameConfig;
import com.kimzing.tetris.util.CenterSet;

import javax.swing.*;

public class GameFrame extends JFrame {
    FrameConfig fcf = GameConfig.getFRAM_CONFIG();

    public GameFrame(GamePanel gamePanel) {
        //设置软件的图标
        this.setIconImage(Images.TITLE);
        // 设置标题
        this.setTitle(fcf.getTitle());
        // 设置默认关闭操作
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口大小
        this.setSize(fcf.getWidth(), fcf.getHeight());
        // 不允许用户调整大小
        this.setResizable(false);
        // 居中
        CenterSet.setCenter(this);
        // 设置内置容器
        this.setContentPane(gamePanel);
        this.setVisible(true);
    }
}
