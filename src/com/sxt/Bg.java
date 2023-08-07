package com.sxt;

import java.awt.*;

public class Bg {
    //图片载入
    Image bg=Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
    Image bg1=Toolkit.getDefaultToolkit().getImage("imgs/bg1.jpg");
    Image peo=Toolkit.getDefaultToolkit().getImage("imgs/peo.png");

    //绘制图片bg
    public void paintSelf(Graphics img){
        img.drawImage(bg,0,0,null);
        img.drawImage(bg1,0,0,null);
        img.drawImage(peo,300,50,null);
    }
}
