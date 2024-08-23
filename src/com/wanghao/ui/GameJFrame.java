package com.wanghao.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    public static Container container = null;

    int[][] data = new int[4][4];
    int[][] win = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0},
    };


    String path = "image\\animal\\animal3\\";
    //记录空白方块
    int x = 0;
    int y = 0;
    int step = 0;

    //创建每个选项下面的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");


    JMenuItem accountItem = new JMenuItem("公众号");

    //游戏主界面
    public GameJFrame() {
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据
        initData();

        //初始化图片
        initImage();

        //展示界面放在最后
        this.setVisible(true);
    }

    private void initData() {

        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);

            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }


        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tempArr[i];
        }
    }

    //初始化图片
    private void initImage() {
        this.getContentPane().removeAll();

        if (victory()) {
            JLabel winLabel = new JLabel(new ImageIcon("image\\win.png"));
            winLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winLabel);
        }

        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);


        //先加载的图片会再最上面，后加载的放下面
        //加载15个图片
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int number = data[i][j];
                JLabel jLabel = new JLabel(new ImageIcon(path + number + ".jpg"));
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                jLabel.setBorder(new BevelBorder(1));
                this.getContentPane().add(jLabel);
            }
        }
        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);
        //刷新一下界面
        this.getContentPane().repaint();


    }


    private void initJMenuBar() {
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上面的两个选项
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        JMenu changePic = new JMenu("更换图片");


        functionJMenu.add(changePic);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        changePic.add(girl);
        changePic.add(animal);
        changePic.add(sport);

        aboutJMenu.add(accountItem);

        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);

        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603, 680);  //单位是像素

        this.setTitle("拼图单机版 v1.0");

        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);

        //取消默认的居中放置
        this.setLayout(null);

        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        int code = e.getKeyCode();
        if (code == 65) {
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()) {
            return;
        }

        initImage();
        int code = e.getKeyCode();
        if (code == 37 && y != 3) {
            System.out.println("Left");
            if (y == 3) {
                return;
            }
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            step++;
            initImage();
        } else if (code == 38) {
            System.out.println("Up");
            if (x == 3) {
                return;
            }
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            step++;
            initImage();
        } else if (code == 39) {
            System.out.println("Right");
            if (y == 0) {
                return;
            }
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            step++;
            initImage();
        } else if (code == 40) {
            System.out.println("Down");
            if (x == 0) {
                return;
            }
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            step++;
            initImage();
        } else if (code == 87) {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();

        }
    }

    public boolean victory() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == replayItem) {
            System.out.println("重新游戏");
            initData();
            step = 0;
            initImage();
        } else if (obj == reLoginItem) {
            System.out.println("重新登录");
            this.setVisible(false);
            new LoginJFrame();
        } else if (obj == closeItem) {
            System.out.println("关闭游戏");
            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("公众号");
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("image/about.png"));
            jLabel.setBounds(0, 0, 258, 258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344, 344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);

        } else if (obj == girl) {
            Random r = new Random();
            int girlnumber = r.nextInt(13) + 1;
            path = "image\\girl\\girl"+ girlnumber + "\\";
            System.out.println(path);
            initImage();
        }else if (obj == animal) {
            Random r = new Random();
            int animalnumber = r.nextInt(8) + 1;
            path = "image\\animal\\animal"+ animalnumber + "\\";
            System.out.println(path);
            initImage();
        }else if (obj == sport) {
            Random r = new Random();
            int sportnumber = r.nextInt(10) + 1;
            path = "image\\sport\\sport"+ sportnumber + "\\";
            System.out.println(path);
            initImage();
        }
    }
}

