package com.kimzing.tetris.config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GameConfig {
    private static FrameConfig FRAM_CONFIG = null;
    private static DataConfig DATA_CONFIG = null;
    private static SystemConfig SYSTEM_CONFIG = null;

    // 构造器私有化
    private GameConfig() {
    }

    static {
        try {
            // 创建一个读取器
            SAXReader reader = new SAXReader();
            // 将读取的内容存入Document
            Document doc = reader.read(GameConfig.class.getResourceAsStream("/data/cfg.xml"));
            // 获取根元素
            Element game = doc.getRootElement();
            // 配置窗口参数
            FRAM_CONFIG = new FrameConfig(game.element("frame"));
            // 配置数据库参数
            DATA_CONFIG = new DataConfig(game.element("data"));
            // 配置系统参数
            SYSTEM_CONFIG = new SystemConfig(game.element("system"));
        } catch (DocumentException e) {
            System.out.println("无法获取配置文件信息！");
            e.printStackTrace();
        }
    }

    /**
     * 获得窗口配置
     *
     * @return
     */
    public static FrameConfig getFRAM_CONFIG() {
        return FRAM_CONFIG;
    }

    /**
     * 获得数据访问配置
     *
     * @return
     */
    public static DataConfig getDATA_CONFIG() {
        return DATA_CONFIG;
    }

    /**
     * 获得系统配置
     *
     * @return
     */
    public static SystemConfig getSYSTEM_CONFIG() {
        return SYSTEM_CONFIG;
    }
}
