package com.kimzing.tetris.ui.setting;

import com.kimzing.tetris.dto.GameDto;
import com.kimzing.tetris.util.CenterSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cheat extends JFrame {
    private JButton jbtOK = null;
    private JLabel question = null;
    private JTextField answer = null;
    private JLabel errMsg = null;
    private GameDto gameDto;

    public Cheat(GameDto gameDto) {
        this.gameDto = gameDto;
        //标题
        this.setTitle("作弊器");
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

    //添加时间监听器
    private void addAction() {
        this.jbtOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(answer.getText().equals("小金我爱你"))) {
                    errMsg.setText("你不记得了吗？宝贝");
                } else {
                    gameDto.setCheat(true);
                    setVisible(false);
                }
            }
        });
    }

    //初始化控件
    private void initCom() {
        //上方的panel
        JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.errMsg = new JLabel();
        errMsg.setForeground(Color.RED);
        this.question = new JLabel("尊敬的老婆大人：");
        question.setFont(new Font("黑体", Font.BOLD, 15));
        north.add(question);
        north.add(errMsg);
        this.add(north, BorderLayout.NORTH);
        //下方的panel
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.jbtOK = new JButton("爱你");
        south.add(jbtOK);
        this.add(south, BorderLayout.SOUTH);
        //中间的panel
        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.answer = new JTextField(8);
        JLabel jl = new JLabel("请老婆输入密语暗号：");
        jl.setFont(new Font("黑体", Font.BOLD, 15));
        center.add(jl);
        center.add(answer);
        this.add(center, BorderLayout.CENTER);

    }
}
