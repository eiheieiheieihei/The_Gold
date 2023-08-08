package com.sxt;

import java.awt.*;

public class Gold extends Object{

    Gold(){
        //0-700 300-850 防止生成在窗体上,天上
        this.x=(int)(Math.random()*700);
        this.y=(int)(Math.random()*500+300);
        this.width=52;
        this.height=52;
        this.flag=false;
        this.m=150;
        this.img= Toolkit.getDefaultToolkit().getImage("imgs/gold1.gif");
    }
}
