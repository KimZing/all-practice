package com.kimzing.tetris.ui.setting;

import com.kimzing.tetris.control.GameControler;
import com.kimzing.tetris.util.CenterSet;

import javax.swing.*;
import java.awt.*;

public class User_Point extends JFrame {
    private JButton jbtExit = null;
    private JButton jbtOK = null;
    private JLabel user_score = null;
    public JTextField user_name = null;
    private JLabel errMsg = null;
    private GameControler gameControler;

    public User_Point(GameControler gameControler) {
        this.gameControler = gameControler;
        //标题
        this.setTitle("保存记录");
        //设置窗体布局
        this.setLayout(new BorderLayout());
//		设置窗体大小
        this.setSize(300, 128);
//		设置窗体居中
        CenterSet.setCenter(this);
//		实例化对象
        this.initCom();
        //监听器
        addAction();
//		不可拖动窗口
        this.setResizable(false);
        this.setAlwaysOnTop(true);
    }

    //显示窗口
    public void showPoint(int point) {
        this.user_score.setText("您的得分为" + point + "分!");
        this.setVisible(true);
    }

    //添加时间监听器
    private void addAction() {
        this.jbtExit.addActionListener(e -> System.exit(DISPOSE_ON_CLOSE));
        this.jbtOK.addActionListener(e -> {
            if (user_name.getText().length() > 16 || "".equals(user_name.getText())) {
                errMsg.setText("大侠名字真长！");
            } else {
                gameControler.savePlayer(user_name.getText());
                setVisible(false);
            }
        });
    }

    //初始化控件
    private void initCom() {
        //上方的panel
        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.errMsg = new JLabel();
        errMsg.setForeground(Color.RED);
        this.user_score = new JLabel();
        user_score.setFont(new Font("黑体", Font.BOLD, 15));
        north.add(user_score);
        north.add(errMsg);
        this.add(north, BorderLayout.NORTH);
        //下方的panel
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 5));
        this.jbtExit = new JButton("退出");
        this.jbtOK = new JButton("确定");
        south.add(jbtOK);
        south.add(jbtExit);
        this.add(south, BorderLayout.SOUTH);
        //中间的panel
        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.user_name = new JTextField(8);
        JLabel jl = new JLabel("大侠，留下姓名：");
        jl.setFont(new Font("黑体", Font.BOLD, 15));
        center.add(jl);
        center.add(user_name);
        this.add(center, BorderLayout.CENTER);
    }
}
