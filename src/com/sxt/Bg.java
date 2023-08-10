package com.sxt;

import java.awt.*;

public class Bg {
    //积分总分
    static int count=0;
    //图片载入
    Image bg=Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
    Image bg1=Toolkit.getDefaultToolkit().getImage("imgs/bg1.jpg");
    Image peo=Toolkit.getDefaultToolkit().getImage("imgs/peo.png");

    //绘制图片bg
    public void paintSelf(Graphics img){
        img.drawImage(bg,0,0,null);
        img.drawImage(bg1,0,0,null);
        img.drawImage(peo,300,50,null);
        drawWord(img,30,Color.BLACK,"积分："+count,30,150);
    }

    //字符串绘制
    public static void drawWord(Graphics img,int size,Color color,String str,int x,int y){
        img.setColor(color);
        img.setFont(new Font("仿宋",Font.BOLD,size));
        img.drawString(str,x,y);
    }
}
