package com.sxt;

import java.awt.*;

public class Bg {
    //关卡数 目标得分
    static int level=1;
    int goal=level*5;
    //积分总分
    static int count=0;
    //药水数量 药水状态，true为正在使用
    static int waterNum=3;
    static boolean waterState=false;
    //计时，开始 结束 每局时间
    long satrtTime;
    long endTime;
    int gameTime=20;
    //图片载入
    Image bg=Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
    Image bg1=Toolkit.getDefaultToolkit().getImage("imgs/bg1.jpg");
    Image peo=Toolkit.getDefaultToolkit().getImage("imgs/peo.png");
    Image water=Toolkit.getDefaultToolkit().getImage("imgs/water.png");
    //绘制图片bg
    public void paintSelf(Graphics img){
        img.drawImage(bg,0,0,null);
        img.drawImage(bg1,0,0,null);
        switch (GameWin.gameState){
            case GAME_START:
                drawWord(img,50,Color.BLACK,"按下左键开始！",100,400);
                drawWord(img,50,Color.BLACK,"一次没进就再试试再试试！！！",35,600);
                break;
            case GAME_ING:
                img.drawImage(peo,300,50,null);
                img.drawImage(water,500,40,null);
                drawWord(img,30,Color.BLACK,"*"+waterNum,560,70);
                drawWord(img,30,Color.BLACK,"积分："+count,30,150);
                drawWord(img,20,Color.BLACK,"关卡："+Bg.level,30,60);
                drawWord(img,30,Color.BLACK,"目标积分："+goal,30,110);
                endTime=System.currentTimeMillis();
                long time=gameTime-(endTime-satrtTime)/1000;
                drawWord(img,30,Color.BLACK,"时间: "+(time>0?time:0),520,150);
                break;
            case GAME_SHOPPING:
                break;
            case GAME_WIN:
                break;
            case GAME_FAIL:
                break;
            default:
        }

    }

    //字符串绘制
    public static void drawWord(Graphics img,int size,Color color,String str,int x,int y){
        img.setColor(color);
        img.setFont(new Font("仿宋",Font.BOLD,size));
        img.drawString(str,x,y);
    }
}
