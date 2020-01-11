package com.kimzing.tetris.ui.setting;

import com.kimzing.tetris.control.GameControler;
import com.kimzing.tetris.util.CenterSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class FrameConfig extends JFrame {
    private GameControler gameControler;
    private JLabel errorMsg = new JLabel();
    private final static String path = "/data/control.data";
    private JButton jbtOK = new JButton("确定");
    private JButton jbtCancel = new JButton("取消");
    private JButton jbtApply = new JButton("应用");
    private static final Image CONTROL = new ImageIcon(
            "graphics/other/control.png").getImage();
    private static final TextCtrl[] textCtrls = new TextCtrl[8];
    private final static String[] METHOD_NAME = {
            "keyRight", "turn", "keyLeft", "keyDown",
            "keyFunLeft", "keyFunUp", "keyFunRight", "keyFunDown"
    };

    public FrameConfig(GameControler gameControler) {
//		设置置顶
        this.setAlwaysOnTop(true);
//		 this.setUndecorated(true); // 去掉窗口的装饰 
        //初始化游戏控制器
        this.gameControler = gameControler;
        //标题,隐藏了装饰，所以不需要标题
        this.setTitle("设置");
        // 初始化文本
        this.initTextCtrl();
        // 设置窗体布局为边界布局
        this.setLayout(new BorderLayout());
        // 设置窗体大小
        this.setSize(592, 290);
        // 窗体居中
        CenterSet.setCenter(this);
        // 设置不可调整大小
        this.setResizable(false);
        // 添加按钮面板
        this.add(this.creatButtonPanel(), BorderLayout.SOUTH);
        // 添加主窗口
        this.add(this.CreatMainPanel(), BorderLayout.CENTER);

    }

    /**
     * 初始化文本框
     */
    private void initTextCtrl() {
        int x = 20;
        int y = 30;
        int w = 65;
        int h = 20;
        //循环左侧文本框
        for (int i = 0; i < 4; i++) {
            textCtrls[i] = new TextCtrl(x, y, w, h, METHOD_NAME[i]);
            y += 27;
        }
        x = 500;
        y = 33;
        //循环右侧文本框
        for (int i = 4; i < 8; i++) {
            textCtrls[i] = new TextCtrl(x, y, w, h, METHOD_NAME[i]);
            y += 27;
        }
        try {
            //输入流
            ObjectInputStream ois = new ObjectInputStream(FrameConfig.class.getResourceAsStream(path));
//			接受HashMap对象
            @SuppressWarnings("unchecked")
            HashMap<Integer, String> fcfRead = (HashMap<Integer, String>) ois.readObject();
            ois.close();
//			返回一个set
            Set<Entry<Integer, String>> entrys = fcfRead.entrySet();
            for (Entry<Integer, String> e : entrys) {
                for (TextCtrl tcl : textCtrls) {
                    if (tcl.getMethodName().equals(e.getValue())) {
                        tcl.setKeyCode(e.getKey());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建底部按钮布局
     *
     * @return
     */
    private JPanel creatButtonPanel() {
        // 流式布局
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.errorMsg.setForeground(Color.RED);
        jp.add(errorMsg);
        jp.add(jbtOK);
        this.jbtOK.addActionListener(new ActionListener() {
            //给确定按钮添加事件监听
            @Override
            public void actionPerformed(ActionEvent e) {
                if (writeConfig()) {
                    setVisible(false);
                    gameControler.setOver();
                }
            }
        });
        jp.add(jbtCancel);
        this.jbtCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                gameControler.setOver();
            }
        });
        jp.add(jbtApply);
//		给应用按钮增加事件监听
        this.jbtApply.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                writeConfig();
            }
        });
        return jp;
    }

    // 创建主控制面板
    private JTabbedPane CreatMainPanel() {
        // 选项卡面板
        JTabbedPane jt = new JTabbedPane();
//		增加两个选项卡
        jt.addTab("游戏控制", this.creatControlPanel());
        jt.addTab("皮肤设置", new JPanel());
        return jt;
    }

    private JPanel creatControlPanel() {
        // 匿名内部类，并设置布局为空

        JPanel jp = new JPanel(null) {
            protected void paintComponent(Graphics g) {
                g.drawImage(CONTROL, 0, 0, null);
            }

            ;
        };
        //循环添加到控制面板
        for (int i = 0; i < textCtrls.length; i++) {
            jp.add(textCtrls[i]);
        }
        return jp;
    }


    /**
     * 设置配置文件
     */
    private boolean writeConfig() {
        HashMap<Integer, String> keyEvents = new HashMap<Integer, String>();
        for (int i = 0; i < textCtrls.length; i++) {
            int keyCode = this.textCtrls[i].getKeyCode();
            if (keyCode == 0) {
                this.errorMsg.setText("您输入的按键有误，请重新输入");
                return false;
            }
            keyEvents.put(keyCode, textCtrls[i].getMethodName());
        }
        if (keyEvents.size() != 8) {
            this.errorMsg.setText("您输入了重复的按键，请重新输入");
            return false;
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(keyEvents);
            oos.close();
        } catch (Exception e) {
            this.errorMsg.setText(e.getMessage());
            return false;
        }
        this.errorMsg.setText("设置保存成功");
        return true;
    }
}
