package com.sxt;

import java.awt.*;

public class Rock extends Object{
    Rock(){
        this.x=(int)(Math.random()*700);
        this.y=(int)(Math.random()*500+300);
        this.width=71;
        this.height=71;
        this.flag=false;
        this.m=250;
        this.count=1;
        this.type=Type.ROCK;
        this.img= Toolkit.getDefaultToolkit().getImage("imgs/rock1.png");
    }
}
