package com.kimzing.tetris.dao;

import com.kimzing.tetris.dto.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBase implements Data {
    // 初始化连接
    private static Connection conn = null;
    // 反射数据库的类
    private final String driver;
    // 连接数据库的地址
    private final String dbUrl;
    // 用户名
    private final String dbUser;
    // 用户密码
    private final String dbPassword;
    // 读取数据库的命令
    private final String loadSql;
    // 插入数据库的命令
    private final String saveSql;

    public DataBase(HashMap<String, String> map) {
        driver = map.get("driver");
        dbUrl = map.get("db_url");
        dbUser = map.get("db_userName");
        dbPassword = map.get("db_userPassword");
        loadSql = map.get("select");
        saveSql = map.get("insert");
        // 初始化数据库类，只需要加载一次
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Player> LoadData() {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Player> players = new ArrayList<Player>();
        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            stmt = conn.prepareStatement(loadSql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                players.add(new Player(rs.getString(1), rs.getInt(2)));
            }
        } catch (SQLException e) {
            System.out.println("数据库连接失败！请检查数据库配置");
        } finally {
            if (stmt != null)
                try {
                    if (rs != null)
                        rs.close();
                    stmt.close();
                    if (conn != null)
                        conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return players;
    }

    @Override
    public void saveData(Player player) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            stmt = conn.prepareStatement(saveSql);
            stmt.setObject(1, player.getName());
            stmt.setObject(2, player.getScore());
            stmt.setObject(3, 1);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (stmt != null)
                try {
                    stmt.close();
                    if (conn != null)
                        conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}
