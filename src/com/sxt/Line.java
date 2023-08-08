package com.sxt;

import java.awt.*;

public class Line {
    //线起点坐标
    int x = 375, y = 160;
    //线终点坐标
    int endx = 500, endy = 500;
    //线长 角度 方向 状态（摇摆0 抓取1 收回2）
    double length = 100;
    double n = 0;
    int toward = 1;
    int state;

    void paintSelf(Graphics g) {
        switch (state) {
            case 0:
                state_0(g);
                break;
            case 1:
                if (length < 500) {
                    state_1(g);
                } else {
                    state = 2;
                }
                break;
            case 2:
                if (length > 100) {
                    state_2(g);
                } else {
                    state = 0;
                }
            default:
                break;
        }
    }

    void state_0(Graphics g) {
        //角度与方向
        if (n < 0.2) toward = 1;
        else if (n > 0.9) toward = -1;
        n = n + 0.05 * toward;
        drawing(g);
    }

    void state_1(Graphics g) {
        length = length + 10;
        drawing(g);
    }

    void state_2(Graphics g) {
        length = length - 10;
        drawing(g);
    }

    //红线终点与相关绘制
    void drawing(Graphics g){
        endx = (int) (x + length * Math.cos(n * Math.PI));
        endy = (int) (y + length * Math.sin(n * Math.PI));
        g.setColor(Color.red);
        g.drawLine(x, y, endx, endy);
    }
}
