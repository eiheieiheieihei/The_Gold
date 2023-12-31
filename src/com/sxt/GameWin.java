package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

//继承JFrame类：创建窗口，监听鼠标键盘事件的功能
public class GameWin extends JFrame {
    //游戏状态
    static GameState gameState=GameState.GAME_START;
    public enum GameState{
        GAME_START,
        GAME_ING,
        GAME_SHOPPING,
        GAME_WIN,
        GAME_FAIL
    }
    //存储金块、石块
    List<Object> objectList=new ArrayList<>();
    Bg bg=new Bg();
    Line line=new Line(this);

    //类中不能直接写for循环，所以用代码块或者无参构造
    //代码块
    {
        //是否可以放置
        boolean isPlace=true;
        for(int i=0;i<8;i++){
            double randon=Math.random();
            Gold gold;
            if(randon<0.3){gold=((new GoldMini()));}
            else if(randon<0.7){gold=(new Gold());}
            else {gold=(new GoldPlus());}

            //判断是否重叠
            for(Object obj:objectList){
                if(gold.getRec().intersects(obj.getRec())){
                    //不能放置
                    isPlace=false;
                }
            }

            if(isPlace){
                objectList.add(gold);
            }else {
                //释放状态，重新生成
                isPlace=true;i--;
            }
        }
        for(int i=0;i<6;i++){
            double randon=Math.random();
            Rock rock;
            rock=new Rock();
            //判断是否重叠
            for(Object obj:objectList){
                if(rock.getRec().intersects(obj.getRec())){
                    //不能放置
                    isPlace=false;
                }
            }

            if(isPlace){
                objectList.add(rock);
            }else {
                isPlace=true;i--;
            }
        }
    }

    //画布
    Image offScreenImage;

    //窗口设置
    void launch(){
        this.setVisible(true);
        this.setSize(768,1000);
        this.setLocationRelativeTo(null);
        this.setTitle("来捞金金！！！");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //鼠标事件
        addMouseListener(new MouseAdapter() {
            //点击事件 左键1 滚轮2 右键3z
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (gameState){
                    case GAME_START:
                        if(e.getButton()==3) {
                            gameState = GameState.GAME_ING;
                            bg.satrtTime=System.currentTimeMillis();
                        }
                        break;
                    case GAME_ING:
                        //左右摇摆，点击左键
                        if(e.getButton()==1&&line.state==0)line.state=1;
                        //抓取返回，点击右键
                        if(e.getButton()==3&&line.state==3&&Bg.waterNum>0){
                            Bg.waterState=true;
                            Bg.waterNum--;
                        }
                        break;
                    case GAME_SHOPPING:
                        if(e.getButton()==1){
                            bg.shopWater=true;
                        }
                        if(e.getButton()==3){
                            gameState=GameState.GAME_START;
                        }
                        break;
                    case GAME_WIN:
                    case GAME_FAIL:
                        if(e.getButton()==3) {
                            gameState = GameState.GAME_START;
                            bg.reGame();
                            line.reGame();
                        }
                        break;
                    default:
                }
            }
        });

        //不停的绘制
        while (true){
            repaint();
            nextLevel();
            //延时
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
//                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    //绘制图片bg
    public void paint(Graphics img){
        //画布大小和画笔设定
        offScreenImage=this.createImage(768,1000);
        Graphics gImage=offScreenImage.getGraphics();
        //画于画布上
        bg.paintSelf(gImage);
        if(gameState==GameState.GAME_ING){
            line.paintSelf(gImage);
            for(Object obj:objectList){
                obj.paintSelf(gImage);
            }
        }
        //画布画于窗口
        img.drawImage(offScreenImage,0,0,null);
    }

    public static void main(String[] args){
        GameWin gameWin=new GameWin();
        gameWin.launch();
    }

    //判断是否在规定时间内完成通关
    boolean gameTimeOn(){
        long time=(bg.endTime-bg.satrtTime)/1000;
        if(time> bg.gameTime)return true;
        return false;
    }
    //下一关
    public void nextLevel(){
        if(gameState==GameState.GAME_ING&&gameTimeOn()){
            if(Bg.count>=bg.goal){
                if(Bg.level==bg.levelSum){
                    gameState=GameState.GAME_WIN;
                }else{
                    gameState=GameState.GAME_SHOPPING;
                    Bg.level++;
                }
            }else{
                gameState=GameState.GAME_FAIL;
            }
            dispose();
            GameWin gameWinNext=new GameWin();
            gameWinNext.launch();
        }
    }
}
