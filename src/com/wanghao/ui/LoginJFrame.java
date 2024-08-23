package com.wanghao.ui;

import javax.swing.*;

public class LoginJFrame extends JFrame {

    public LoginJFrame(){
        this.setSize(488,430);

        this.setTitle("拼图 登录");

        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);

        this.setVisible(true);
    }
}
