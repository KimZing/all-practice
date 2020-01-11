package com.kimzing.tetris.ui.setting;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class TextCtrl extends JTextField {
    private int keyCode;
    private final String methodName;

    public TextCtrl(int x, int y, int w, int h, String MethodName) {
//		设置文本框位置
        this.setBounds(x, y, w, h);
//		初始化方法名称
        this.methodName = MethodName;
//		初始化KeyCode
        this.setText(KeyEvent.getKeyText(keyCode));
//		设置事件监听
        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                setKeyCode(e.getKeyCode());

            }
        });
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
        setText(KeyEvent.getKeyText(keyCode));
    }

    public String getMethodName() {
        return methodName;
    }

}
