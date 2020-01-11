package com.kimzing.tetris.service;


public interface GameService {
    //	下
    public boolean keyDown();

    //	上
    public boolean turn();

    //	左
    public boolean keyLeft();

    //	右
    public boolean keyRight();

    //	下
    public boolean keyFunDown();

    //	上
    public boolean keyFunUp();

    //	左
    public boolean keyFunLeft();

    //	右
    public boolean keyFunRight();

    //启动主线程，开始游戏
    public void startGame();

    public void mainAction();
}
