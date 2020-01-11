package com.kimzing.tetris.control;

import com.kimzing.tetris.config.DataInterfaceConfig;
import com.kimzing.tetris.config.GameConfig;
import com.kimzing.tetris.dao.Data;
import com.kimzing.tetris.dto.GameDto;
import com.kimzing.tetris.dto.Player;
import com.kimzing.tetris.service.GameService;
import com.kimzing.tetris.service.GameTetris;
import com.kimzing.tetris.ui.GameFrame;
import com.kimzing.tetris.ui.GamePanel;
import com.kimzing.tetris.ui.setting.FrameConfig;
import com.kimzing.tetris.ui.setting.User_Point;

import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 接收玩家键盘事件 控制画面 控制游戏逻辑
 *
 * @author KING
 */
public class GameControler {
    //
    private User_Point user_Point;
    private FrameConfig framConfig;
    // 数据访问接口A
    private Data dataDB;
    // 数据访问接口B
    private Data dataLocal;
    // 游戏界面层
    private GamePanel panelGame;
    // 游戏逻辑层
    private GameService gameService;
    // 键盘实践对应的数组
    private Map<Integer, Method> actionList;
    private GameDto gameDto = null;
    //游戏线程
    private Thread gameThread = null;

    // 初始化panelgameh和gameService
    public GameControler() {
        //初始化保存分式窗口
        user_Point = new User_Point(this);
        // 创建游戏数据源
        this.gameDto = new GameDto();
        // 创建游戏逻辑块，（连接游戏数据源）
        this.gameService = new GameTetris(gameDto);
        // 创建游戏面板
        this.panelGame = new GamePanel(this, gameDto);
        // 从数据库接口A获得数据库记录
        this.dataDB = createDataObject(GameConfig.getDATA_CONFIG().getDataA());
        // 设置数据库记录到游戏
        this.gameDto.setDbRecoder(dataDB.LoadData());
        // 从数据接口B获得本地磁盘记录
        this.dataLocal = createDataObject(GameConfig.getDATA_CONFIG()
                .getDataB());
        // 设置本地磁盘记录到游戏
        this.gameDto.setLocalRecoder(dataLocal.LoadData());
//		读取用户配置
        this.setControlConfig();
//		初始化用户配置窗口
        this.framConfig = new FrameConfig(this);
        // 创建游戏窗口，安装游戏面板
        new GameFrame(this.panelGame);
    }

    private void setControlConfig() {
        //创建键盘吗与方法名的映射数组
        // 初始化事件方法对应的数组
        this.actionList = new HashMap<Integer, Method>();
        try {
            ObjectInputStream ois = new ObjectInputStream(GameControler.class.getResourceAsStream("/data/control.data"));
            HashMap<Integer, String> cfgSet = (HashMap<Integer, String>) ois.readObject();
            ois.close();
            Set<Entry<Integer, String>> entrySet = cfgSet.entrySet();
            for (Entry<Integer, String> e : entrySet) {

                actionList.put(e.getKey(), this.gameService.getClass().getMethod(e.getValue()));
            }
            actionList.put(27, GameTetris.class.getMethod("levelUp"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 按键的判断与执行相应的方法
    public void methodByKey(int keyCode) {

        try {
            if (this.actionList.containsKey(keyCode)) {
                actionList.get(keyCode).invoke(this.gameService);
                panelGame.repaint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获得两个数据窗口的配置的类，利用反射
    private Data createDataObject(DataInterfaceConfig d) {
        try {
            Class<?> css = Class.forName(d.getClassName());
            Constructor<?> cst = css.getConstructor(HashMap.class);
            return (Data) cst.newInstance(d.getParam());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //显示玩家控制窗口
    public void showConfig() {
        this.framConfig.setVisible(true);
    }

    /**
     * 子窗口关闭事件
     */
    public void setOver() {
        this.panelGame.repaint();
        this.setControlConfig();
    }

    public void start() {
//		按钮设置为不可点击
        this.panelGame.ButtonSwitch(false);
//		开始时将不相关窗口关闭掉
        this.framConfig.setVisible(false);
        this.user_Point.setVisible(false);
//		游戏数据初始化
        this.gameService.startGame();
//		创建线程对象
        this.gameThread = new MainThread();
        //启动线程
        this.gameThread.start();
        //刷新画面
        this.panelGame.repaint();
    }

    //保存分数
    public void savePlayer(String name) {
        Player player = new Player(name, this.gameDto.getScore());
        this.dataLocal.saveData(player);
        this.dataDB.saveData(player);
        // 设置数据库记录到游戏
        this.gameDto.setDbRecoder(dataDB.LoadData());
        // 设置本地磁盘记录到游戏
        this.gameDto.setLocalRecoder(dataLocal.LoadData());
        this.panelGame.repaint();
    }

    public void afterLose() {

        //显示保存得分窗口
        this.user_Point.user_name.setText("");
        this.user_Point.showPoint(this.gameDto.getScore());
        //使按钮可以点击
        this.panelGame.ButtonSwitch(true);

    }

    private class MainThread extends Thread {
        @Override
        public void run() {
            panelGame.repaint();
            //一直循环执行
            while (gameDto.isSetStart()) {
                try {
                    //睡眠0.8秒
                    Thread.sleep(800);
                    //如果暂停 就继续重新循环
                    if (gameDto.isPause()) {
                        continue;
                    }
//					方块下落
                    gameService.mainAction();
                    panelGame.repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            afterLose();
        }
    }

}
