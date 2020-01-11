package com.kimzing.tetris.dao;

import com.kimzing.tetris.dto.Player;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DataLocal implements Data {
    private final String path;

    public DataLocal(HashMap<String, String> map) {
        path = map.get("path");
    }

    @Override
    public List<Player> LoadData() {
        ObjectInputStream ois = null;
        List<Player> players = null;
        try {
            ois = new ObjectInputStream(DataLocal.class.getResourceAsStream(path));
            players = (List<Player>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return players;
    }

    @Override
    public void saveData(Player player) {
        // 先取出数据
        List<Player> players = this.LoadData();
        // 判断是否大于5条，去掉分数低的
        int i = players.size();
        if (players.size() > 5) {
            Collections.sort(players);
            while (i > 5) {
                players.remove(i - 1);
                i--;
            }
        }
        // 追加新纪录
        players.add(player);

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(players);
        } catch (Exception e) {

        } finally {
            try {
                oos.flush();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
