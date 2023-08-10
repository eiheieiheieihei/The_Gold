package com.sxt;

import java.awt.*;

public class Object {
    //坐标 宽高 图片 标记是否可以移动
    int x,y;
    int width,height;
    Image img;
    boolean flag=false;
    //质量属性 积分 类型
    int m;
    int count;
    Type type;
    public enum Type{
        ROCK,
        GOLD
    }

    void paintSelf(Graphics g){
        g.drawImage(img,x,y,null);
    }

    public int getWidth() {
        return width;
    }

    //获取矩形
    public Rectangle getRec(){
        return new Rectangle(x,y,width,height);
    }
}
