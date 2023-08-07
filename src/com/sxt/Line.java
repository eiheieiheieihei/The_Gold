package com.sxt;

import java.awt.*;


public class Line {
    //线起点坐标
    int x=375,y=160;
    //线终点坐标
    int endx=500,endy=500;
    //线长
    double length=100;
    double n=0;
    int toward=1;
    void paintSelf(Graphics g){

        //角度与方向
        if(n<0.2)toward=1;
        else if(n>0.9)toward=-1;
        n=n+0.05*toward;
        //红线终点
        endx=(int)(x+length*Math.cos(n*Math.PI));
        endy=(int)(y+length*Math.sin(n*Math.PI));
        g.setColor(Color.red);
        g.drawLine(x,y,endx,endy);
    }
}
