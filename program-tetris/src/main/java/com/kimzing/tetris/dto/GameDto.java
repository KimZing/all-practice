package com.kimzing.tetris.dto;

import com.kimzing.tetris.config.GameConfig;
import com.kimzing.tetris.entity.GameRect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameDto {
    // 最大的用户数 与显示的数据数相同
    private final int maxData = GameConfig.getFRAM_CONFIG().getMaxData();
    // 数据库用户记录
    private List<Player> dbRecoder;
    // 本地用户记录
    private List<Player> localRecoder;
    // 游戏主窗口的方块判断
    private boolean[][] gameMap;
    // 游戏方块
    private GameRect gameRect;
    // 下一个初始化
    private int next;
    // 当前等级
    private int level;
    // 当前分数
    private int score;
    // 当前消掉的行数
    private int line;
    //游戏开始与结束
    private boolean setStart = false;
    //阴影开关
    private boolean showShadow = true;
    private boolean pause = false;
    //作弊开关
    private boolean cheat = false;

    // Dto初始化
    public GameDto() {
        dtoInit();
    }

    public void dtoInit() {
        // 地图的数组长度
        this.gameMap = new boolean[GameConfig.getSYSTEM_CONFIG().getMaxX() + 1][GameConfig
                .getSYSTEM_CONFIG().getMaxY() + 1];
        this.level = 0;
        this.score = 0;
        this.line = 0;
        this.pause = false;
    }

    // 各个属性的get和set方法
    public List<Player> getDbRecoder() {
        return dbRecoder;
    }

    public void setDbRecoder(List<Player> dbRecoder) {
        // 存储前进行排序
        this.dbRecoder = fillRecoder(dbRecoder);
    }

    public List<Player> getLocalRecoder() {
        return localRecoder;
    }

    public void setLocalRecoder(List<Player> localRecoder) {
        // 存储前进行排序

        this.localRecoder = fillRecoder(localRecoder);
    }

    // 排序操作
    private List<Player> fillRecoder(List<Player> players) {
        if (players == null) {
            players = new ArrayList<Player>();
        }
        while (players.size() < maxData) {
            players.add(new Player("No Data", 0));
        }
        Collections.sort(players);
        return players;
    }

    public boolean[][] getGameMap() {
        return gameMap;
    }

    public void setGameMap(boolean[][] gameMap) {
        this.gameMap = gameMap;
    }

    public GameRect getGameRect() {
        return gameRect;
    }

    public void setGameRect(GameRect gameRect) {
        this.gameRect = gameRect;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public boolean isSetStart() {
        return setStart;
    }

    public void setSetStart(boolean setStart) {
        this.setStart = setStart;
    }

    public boolean isShowShadow() {
        return showShadow;
    }

    public void changeShowShadow() {
        this.showShadow = !this.showShadow;
    }

    public boolean isPause() {
        return pause;
    }

    public void changePause() {
        this.pause = !this.pause;
    }

    public boolean isCheat() {
        return cheat;
    }

    public void setCheat(boolean cheat) {
        this.cheat = cheat;
    }

}
