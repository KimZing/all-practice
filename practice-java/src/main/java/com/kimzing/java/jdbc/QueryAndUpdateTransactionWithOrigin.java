package com.kimzing.java.jdbc;

import lombok.extern.java.Log;

import java.sql.*;

/**
 * User KimZing - kimzing@163.com
 * Date 2018/7/27 13:46
 * Desc 在原有数据上做数据修改
 */
@Log
public class QueryAndUpdateTransactionWithOrigin {

    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();
        conn.setAutoCommit(false);

        String querySQL = "select * from user";
        PreparedStatement queryPS = conn.prepareStatement(querySQL);
        ResultSet rs = queryPS.executeQuery();
        while (rs.next()) {
            String username = rs.getString("username");
            int amount = rs.getInt("amount");
            log.info(username + " has amount " + amount);
        }
        //将TranslateTransaction进行调试,会锁住这一行，此处进入等待，结果仍然正确。
        String minusSQL = "update user set amount = amount - 100 where username = ?;";
        PreparedStatement minusPS = conn.prepareStatement(minusSQL);
        minusPS.setString(1, "boy");
        minusPS.executeUpdate();

        conn.commit();
        queryPS.close();
        minusPS.close();
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
