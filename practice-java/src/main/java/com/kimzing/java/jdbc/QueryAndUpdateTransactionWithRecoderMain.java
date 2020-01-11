package com.kimzing.java.jdbc;

import lombok.extern.java.Log;

import java.sql.*;

/**
 * User KimZing - kimzing@163.com
 * Date 2018/7/27 13:46
 * Desc 根据先前记录的数据进行查询
 */
@Log
public class QueryAndUpdateTransactionWithRecoderMain {

    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();
        conn.setAutoCommit(false);

        //不加条件会将整个表锁住, 和serieriable类似
        String querySQL = "select * from user where username = 'boy' for update";
        PreparedStatement queryPS = conn.prepareStatement(querySQL);
        ResultSet rs = queryPS.executeQuery();
        Integer amount = 0;
        while (rs.next()) {
            String username = rs.getString("username");
            amount = rs.getInt("amount");
            log.info(username + " has amount " + amount);
        }
        //将TranslateTransaction进行调试,会锁住这一行。
        String minusSQL = "update user set amount = ? - 100 where username = ?;";
        PreparedStatement minusPS = conn.prepareStatement(minusSQL);
        minusPS.setInt(1, amount);
        minusPS.setString(2, "boy");
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
