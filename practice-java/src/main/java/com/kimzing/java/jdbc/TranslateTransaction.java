package com.kimzing.java.jdbc;

import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * User KimZing - kimzing@163.com
 * Date 2018/7/27 13:46
 * Desc 事物
 */
@Log
public class TranslateTransaction {

    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();
        conn.setAutoCommit(false);

        String plusSQL = "update user set amount = amount + 100 where username = ?;";
        PreparedStatement plusPS = conn.prepareStatement(plusSQL);
        plusPS.setString(1, "king");
        plusPS.executeUpdate();

        String minusSQL = "update user set amount = amount - 100 where username = ?;";
        PreparedStatement minusPS = conn.prepareStatement(minusSQL);
        minusPS.setString(1, "boy");
        minusPS.executeUpdate();

        conn.commit();

        minusPS.close();
        plusPS.close();
        conn.close();

    }

    private static Connection getConnection() throws SQLException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/user_tx";
        String username = "root";
        String password = "123456";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.info(e.getLocalizedMessage());
        }
        return DriverManager.getConnection(url, username, password);

    }

}
