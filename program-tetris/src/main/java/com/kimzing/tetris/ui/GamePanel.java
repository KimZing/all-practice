package com.kimzing.tetris.ui;

import com.kimzing.tetris.config.FrameConfig;
import com.kimzing.tetris.config.GameConfig;
import com.kimzing.tetris.config.WindowConfig;
import com.kimzing.tetris.control.GameControler;
import com.kimzing.tetris.control.PlayerControler;
import com.kimzing.tetris.dto.GameDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.List;

public class GamePanel extends JPanel {
    private static final int BTN_W = GameConfig.getFRAM_CONFIG().getBtnConfig().getWidth();
    private static final int BTN_H = GameConfig.getFRAM_CONFIG().getBtnConfig().getHeight();
    //	开始标签
    private JButton start;
    //	设置标签
    private JButton config;
    private GameControler gameControler = null;
    FrameConfig fcf = GameConfig.getFRAM_CONFIG();
    // 装入窗口对象的数组
    WindowPaint[] windowPaints = null;

    public GamePanel(GameControler gameControler, GameDto gameDto) {
        //给面板添加控制器
        this.gameControler = gameControler;
        //初始化开始设置标签按钮
        this.init();
        // 初始化窗口布局
        this.setLayout(null);
        initWindow(gameDto);
        //设置按键监听器
        this.addKeyListener(new PlayerControler(gameControler));
    }

    private void init() {
        //初始化开始按钮
        start = new JButton(Images.START);
        //设置开始按钮位置
        start.setBounds(GameConfig.getFRAM_CONFIG().getBtnConfig().getStartX(),
                GameConfig.getFRAM_CONFIG().getBtnConfig().getStartY(),
                BTN_W,
                BTN_H);
//		给开始按钮添加监听
        this.start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameControler.start();
                repaint();
            }
        });
        //添加进面板
        this.add(start);
        //初始化设置按钮
        config = new JButton(Images.TEMP);
        //设置设置按钮位置
        config.setBounds(GameConfig.getFRAM_CONFIG().getBtnConfig().getConfigX(),
                GameConfig.getFRAM_CONFIG().getBtnConfig().getConfigY(),
                BTN_W,
                BTN_H);
//		给设置按钮添加监听
        this.config.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gameControler.showConfig();
            }
        });
        //添加进面板
        this.add(config);
    }


    public void initWindow(GameDto gameDto) {
        // 获得窗口配置
        List<WindowConfig> wcfgs = fcf.getConfigWindows();
        // 创建窗口数组
        windowPaints = new WindowPaint[wcfgs.size()];
        // 创建所有窗口对象
        int i = 0;
        // 循环创建每个窗口的实例并添加进WindowPaints数组
        for (WindowConfig c : wcfgs) {
            try {
                // 获取类
                Class<?> cls = Class.forName(c.getClassName());
                // 获得一个四个参数的构造器
                Constructor<?> ctr = cls.getConstructor(int.class, int.class,
                        int.class, int.class);
                // 利用构造函数创建一个窗口实例
                WindowPaint w = (WindowPaint) ctr.newInstance(c.getX(),
                        c.getY(), c.getW(), c.getH());
                w.setGameDto(gameDto);
                // 添加进数组
                windowPaints[i] = w;
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        // 调用基类方法进行前处理
        super.paintComponent(g);
        // 循环刷新窗口
        for (int i = 0; i < windowPaints.length; windowPaints[i++].paint(g))
            ;
        // 获得焦点
        this.requestFocus();
    }

    public void ButtonSwitch(boolean onoff) {
        this.start.setEnabled(onoff);
        this.config.setEnabled(onoff);
    }
}
