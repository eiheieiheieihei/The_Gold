package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

//继承JFrame类：创建窗口，监听鼠标键盘事件的功能
public class GameWin extends JFrame {

    //存储金块、石块
    List<Object> objectList=new ArrayList<>();
    Bg bg=new Bg();
    Line line=new Line(this);

    //类中不能直接写for循环，所以用代码块或者无参构造
    //代码块
    {
        for(int i=0;i<3;i++){
            objectList.add(new Gold());
        }
    }

    //画布
    Image offScreenImage;

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
        //画布大小和画笔设定
        offScreenImage=this.createImage(768,1000);
        Graphics gImage=offScreenImage.getGraphics();
        //画于画布上
        bg.paintSelf(gImage);
        line.paintSelf(gImage);
        for(Object obj:objectList){
            obj.paintSelf(gImage);
        }
        //画布画于窗口
        img.drawImage(offScreenImage,0,0,null);
    }
    public static void main(String[] args){
        GameWin gameWin=new GameWin();
        gameWin.launch();
    }
}
