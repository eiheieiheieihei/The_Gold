package com.sxt;

import java.awt.*;

public class Object {
    //坐标 宽高 图片 标记是否可以移动
    int x,y;
    int width,height;
    Image img;
    boolean flag=false;

    void paintSelf(Graphics g){
        g.drawImage(img,x,y,null);
    }
}
