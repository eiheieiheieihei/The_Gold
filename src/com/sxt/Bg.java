package com.sxt;

import java.awt.*;

public class Bg {
    //关卡数 目标得分
    int levelSum = 5;
    static int level = 1;
    int goal = level * 5;
    //积分总分
    static int count = 0;
    //药水数量 药水状态，true为正在使用
    static int waterNum = 3;
    static boolean waterState = false;
    //计时，开始 结束 每局时间
    long satrtTime;
    long endTime;
    int gameTime = 20;
    //药水价格 是否进入商店
    int waterPrice = (int) (Math.random() * 10);
    boolean shopWater = false;
    //图片载入
    Image bg = Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
    Image bg1 = Toolkit.getDefaultToolkit().getImage("imgs/bg1.jpg");
    Image peo = Toolkit.getDefaultToolkit().getImage("imgs/peo.png");
    Image water = Toolkit.getDefaultToolkit().getImage("imgs/water.png");

    //绘制图片bg
    public void paintSelf(Graphics img) {
        img.drawImage(bg, 0, 0, null);
        img.drawImage(bg1, 0, 0, null);
        switch (GameWin.gameState) {
            case GAME_START:
                drawWord(img, 50, Color.BLACK, "按下右键开始！", 100, 400);
                drawWord(img, 50, Color.BLACK, "一次没进就再试试再试试！！！", 35, 600);
                break;
            case GAME_ING:
                img.drawImage(peo, 300, 50, null);
                img.drawImage(water, 500, 40, null);
                drawWord(img, 30, Color.BLACK, "*" + waterNum, 560, 70);
                drawWord(img, 30, Color.BLACK, "积分：" + count, 30, 150);
                drawWord(img, 20, Color.BLACK, "关卡：" + Bg.level, 30, 60);
                drawWord(img, 30, Color.BLACK, "目标积分：" + goal, 30, 110);
                endTime = System.currentTimeMillis();
                long time = gameTime - (endTime - satrtTime) / 1000;
                drawWord(img, 30, Color.BLACK, "时间: " + (time > 0 ? time : 0), 520, 150);
                break;
            case GAME_SHOPPING:
                img.drawImage(water, 300, 350, null);
                drawWord(img, 30, Color.red, "药水价格：" + waterPrice, 300, 500);
                drawWord(img, 30, Color.red, "是否购买？", 300, 550);
                drawWord(img, 30, Color.red, "是（左键） 否（右键）", 300, 650);
                if (shopWater) {
                    count = count - waterPrice;
                    waterNum++;
                    shopWater = false;
                    GameWin.gameState = GameWin.GameState.GAME_START;
                }
                break;
            case GAME_WIN:
                drawWord(img, 60, Color.red, "wk！好厉害！", 100, 400);
                drawWord(img, 60, Color.red, "你赢啦！", 100, 550);
                drawWord(img, 60, Color.red, "总分：" + count, 100, 630);
                break;
            case GAME_FAIL:
                drawWord(img, 60, Color.BLACK, "很不错哟！", 100, 400);
                drawWord(img, 60, Color.BLACK, "这就失败了！", 100, 550);
                drawWord(img, 60, Color.BLACK, "总分：" + count, 100, 630);
                break;
            default:
        }
    }

    //字符串绘制
    public static void drawWord(Graphics img, int size, Color color, String str, int x, int y) {
        img.setColor(color);
        img.setFont(new Font("仿宋", Font.BOLD, size));
        img.drawString(str, x, y);
    }

    //元素重置
    void reGame() {
        //关卡数 目标得分
        levelSum = 5;
        level = 1;
        goal = level * 5;
        //积分总分
        count = 0;
        //药水数量 药水状态，true为正在使用
        waterNum = 3;
        waterState = false;
    }
}
