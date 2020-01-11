package com.kimzing.tetris.dao;

import com.kimzing.tetris.dto.Player;

import java.util.List;

public interface Data {
    /**
     * 获得数据
     *
     * @return
     */
    List<Player> LoadData();

    /**
     * 存储数据
     */
    void saveData(Player player);
}
