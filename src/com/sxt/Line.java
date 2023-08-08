package com.sxt;

import java.awt.*;

public class Line {
    GameWin frame;

    Line(GameWin frame) {
        this.frame = frame;
    }

    //线起点坐标
    int x = 375, y = 160;
    //线终点坐标
    int endx = 500, endy = 500;
    //线长 角度 方向 状态（摇摆0 抓取1 收回2 抓取返回3）as
    double length = 100;
    double n = 0;
    int toward = 1;
    int state;

    void paintSelf(Graphics g) {
        logic();
        switch (state) {
            case 0:
                state_0(g);
                break;
            case 1:
                if (length < 650) {
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
                break;
            case 3:
                int m=1;
                if (length > 100) {
                    state_2(g);
                    for (Object obj : this.frame.objectList) {
                        if (obj.flag) {
                            m=obj.m;
                            obj.x = endx - obj.getWidth()/2;
                            obj.y = endy;
                            if (length <= 100) {
                                obj.x = -150;
                                obj.y = -150;
                                obj.flag=false;
                                state = 0;
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(m);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
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
        length = length + 15;
        drawing(g);
    }

    void state_2(Graphics g) {
        length = length - 15;
        drawing(g);
    }

    //红线终点与相关绘制
    void drawing(Graphics g) {
        endx = (int) (x + length * Math.cos(n * Math.PI));
        endy = (int) (y + length * Math.sin(n * Math.PI));
        g.setColor(Color.red);
        g.drawLine(x, y, endx, endy);
    }

    //判断endx,endy是否在矩形区域中
    void logic() {
        for (Object obj : this.frame.objectList) {
            if (endx > obj.x && endx < obj.x + obj.width &&
                    endy > obj.y && endy < obj.y + obj.height) {
                state = 3;
                obj.flag = true;
            }
        }
    }
}
