package com.kimzing.tetris.service;

import com.kimzing.tetris.config.GameConfig;
import com.kimzing.tetris.dto.GameDto;
import com.kimzing.tetris.entity.GameRect;
import com.kimzing.tetris.ui.setting.Cheat;

import java.awt.*;
import java.util.Random;

public class GameTetris implements GameService {
    /**
     * 随机生成方块
     */
    private Random random = new Random();
    // 方块种类个数，与初始化的方块类型一样
    private static final int MAX_TYPE = GameConfig.getSYSTEM_CONFIG()
            .getTypeNumber();
    private GameDto gameDto;

    public GameTetris(GameDto gameDto) {
        this.gameDto = gameDto;

    }

    public boolean turn() {
        if (this.gameDto.isPause()) {
            return true;
        }
        synchronized (this.gameDto) {
//		判断游戏是否开始，开始了再执行
            if (!this.gameDto.isSetStart()) {
                return false;
            }
            this.gameDto.getGameRect().round(this.gameDto.getGameMap());
        }
        return true;
    }

    // 自己的实验

    public boolean keyDown() {
        if (this.gameDto.isPause()) {
            return true;
        }
        synchronized (this.gameDto) {
            //TODO 为什么这里加了  程序就会卡死
            //		判断游戏是否开始，开始了再执行
            if (!this.gameDto.isSetStart()) {
                return false;
            }
            // 方块向下移动，并判断是否移动成功
            if (this.gameDto.getGameRect().move(0, 1, this.gameDto.getGameMap())) {
                return false;
            }
            // 获得游戏地图对象
            boolean[][] map = this.gameDto.getGameMap();
            // 获得方块对象
            Point[] rects = this.gameDto.getGameRect().getRectPoint();
            // 将方块堆积到地图数组
            for (int i = 0; i < rects.length; i++) {
                map[rects[i].x][rects[i].y] = true;
            }

            int lines = removeEvent(map);
            // 算分操作

            this.gameDto.setLine(this.gameDto.getLine() + lines);
            GameConfig.getSYSTEM_CONFIG();
            // 不同的消行加分数
            int scores[] = GameConfig.getSYSTEM_CONFIG().getScores();
            // 算出现在的分数

            int score = this.gameDto.getScore() + scores[lines];
            // 设置分数
            this.gameDto.setScore(score);
            // 判断是否升级
            this.gameDto.setLevel(this.gameDto.getLine()
                    / GameConfig.getSYSTEM_CONFIG().getLineLevel());
            if (this.gameDto.isSetStart()) {
                // 刷新一个新的方块
                this.gameDto.getGameRect().init(this.gameDto.getNext());
                //设置下一个方块
                this.gameDto.setNext(random.nextInt(MAX_TYPE));
            }
            if (this.isLose()) {
//			设置为关闭状态
                this.gameDto.setSetStart(false);

            }
            return true;
        }
    }

    //判断是否游戏失败
    private boolean isLose() {
        Point[] rectPoints = this.gameDto.getGameRect().getRectPoint();
        boolean[][] map = this.gameDto.getGameMap();
        for (int i = 0; i < rectPoints.length; i++) {
            if (map[rectPoints[i].x][rectPoints[i].y]) {

                return true;
            }
        }
        return false;
    }

    public boolean keyLeft() {
        if (this.gameDto.isPause()) {
            return true;
        }
        synchronized (this.gameDto) {
//		判断游戏是否开始，开始了再执行
            if (!this.gameDto.isSetStart()) {
                return false;
            }
            this.gameDto.getGameRect().move(-1, 0, this.gameDto.getGameMap());
        }
        return true;
    }

    public boolean keyRight() {
        if (this.gameDto.isPause()) {
            return true;
        }
        synchronized (this.gameDto) {
//		判断游戏是否开始，开始了再执行
            if (!this.gameDto.isSetStart()) {
                return false;
            }

            this.gameDto.getGameRect().move(1, 0, this.gameDto.getGameMap());
        }
        return true;
    }

    // T判断是否可以消行，如果可以就消行，并返回消行的总数 lines
    // 消行事件
    private int removeEvent(boolean[][] map) {
        int i = 0;
        //扫描游戏地图，查看是否有可消行
        for (int y = 0; y < map[0].length; y++) {
            boolean b = true;
            king:
            for (int x = 0; x < map.length; x++) {
                if (!map[x][y]) {
                    //如果有一个方格为false，则直接扫描下一行
                    b = false;
                    break king;
                }

            }
            if (b) {
                remove(y);
                i++;
            }
        }
        return i;
    }

    // 移除某一行的操作 row需要移除的行
    private void remove(int row) {
        boolean[][] bool = this.gameDto.getGameMap();
        for (int i = 0; i < bool.length; i++) {
            bool[i][row] = false;
        }
        for (int y = row - 1; y >= 0; y--) {
            for (int x = 0; x < bool.length; x++) {
                bool[x][y + 1] = bool[x][y];
                bool[x][y] = false;
            }
        }
    }

    //作弊器_______________________________________________-
    public void levelUp() {
        if (this.gameDto.isSetStart() && this.gameDto.isCheat()) {
            this.gameDto.setLine(this.gameDto.getLine() + 20);
            this.gameDto.setScore(this.gameDto.getScore() + 521);
            this.gameDto.setLevel(this.gameDto.getLevel() + 1);
            return;
        }
        if (!this.gameDto.isCheat() && !this.gameDto.isSetStart()) {
            new Cheat(this.gameDto).setVisible(true);
            return;
        }
    }

    @Override
    public boolean keyFunDown() {
//		直接下落   如果没有回出现bug   按下键游戏结束会让程序卡死
        if (this.gameDto.isPause() || !this.gameDto.isSetStart()) {
            return false;
        }
        while (!this.keyDown()) ;
        return true;
    }

    @Override
    public boolean keyFunUp() {
        // TODO 换一个背景音乐
        return true;
    }

    @Override
    public boolean keyFunLeft() {
//		如果游戏结束，则不提供显示功能
        if (this.gameDto.isSetStart()) {

            //设置阴影开关
            this.gameDto.changeShowShadow();
        }
        return true;
    }

    @Override
    public boolean keyFunRight() {
        //暂停
        if (this.gameDto.isSetStart()) {
            this.gameDto.changePause();
        }
        return true;
    }

    /**
     * 启动主线程
     */
    @Override
    public void startGame() {
//		初始化一个下落方块
        GameRect gameRect = new GameRect(random.nextInt(MAX_TYPE));
//		设置进去dto
        this.gameDto.setGameRect(gameRect);
//		初始化下一个方块
        this.gameDto.setNext(new Random().nextInt(GameConfig.getSYSTEM_CONFIG()
                .getTypeNumber()));
        //把游戏方法设置为开始
        this.gameDto.setSetStart(true);
        //dto初始化
        this.gameDto.dtoInit();

    }

    @Override
    public void mainAction() {
        this.keyDown();
    }
}
