package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//继承JFrame类：创建窗口，监听鼠标键盘事件的功能
public class GameWin extends JFrame {
    Bg bg=new Bg();
    Line line=new Line();
    //窗口设置
    void launch(){
        this.setVisible(true);
        this.setSize(768,1000);
        this.setLocationRelativeTo(null);
        this.setTitle("来捞金金！！！");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //鼠标事件
        addMouseListener(new MouseAdapter() {
            //点击事件 左键1 滚轮2 右键3
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getButton()==1)line.state=1;
            }
        });
        //不停的绘制
        while (true){
            repaint();

            //延时
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //绘制图片bg
    public void paint(Graphics img){
        bg.paintSelf(img);
        line.paintSelf(img);
    }
    public static void main(String[] args){
        GameWin gameWin=new GameWin();
        gameWin.launch();
    }
}
