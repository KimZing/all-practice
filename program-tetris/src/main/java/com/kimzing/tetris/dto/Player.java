package com.kimzing.tetris.dto;

import java.io.Serializable;

public class Player implements Comparable<Player>, Serializable {
    // 用户名称
    private String userName;
    // 用户分数
    private int userScore;

    public Player(String name, int score) {
        super();
        this.userName = name;
        this.userScore = score;
    }

    // getter h和setter方法
    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public int getScore() {
        return userScore;
    }

    public void setScore(int score) {
        this.userScore = score;
    }

    @Override
    public int compareTo(Player o) {
        return o.getScore() - this.getScore();
    }

}
