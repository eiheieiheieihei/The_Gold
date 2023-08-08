package com.sxt;

import java.awt.*;

public class Object {
    //坐标 宽高 图片
    int x,y;
    int width,height;
    Image img;

    void paintSelf(Graphics g){
        g.drawImage(img,x,y,null);
    }
}
