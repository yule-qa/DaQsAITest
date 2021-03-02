package com.dongao.DaQsAiTest.Util;

import com.dongao.DaQsAiTest.Model.ApiObjectActionModel;

import java.sql.*;

public class JdbcUtils {
    private static ResultSet rs;
    private static Connection conn;

    //外部调用方法[
    public static ResultSet startDbQuery(String sql){
        conn = getConnection("root", "root");
        //调用数据库连接
        rs=query(conn,sql);

        return rs;
    }

    //查询数据，定义query方法
    public static ResultSet query(Connection conn,String sql) {
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        return rs;
    }

    //数据库连接
    public static Connection getConnection(String user, String pass) {
        //声明连接对象
        Connection conn = null;
        //驱动程序类名
        String driver = "com.mysql.cj.jdbc.Driver";
        //数据库URL
        String url = "jdbc:mysql://192.168.22.120:3306?"
                + "useUnicode=true&characterEncoding=UTF8";//防止乱码
        try {
            //加载驱动程序
            Class.forName(driver);
            //获取数据库连接
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //释放数据库连接
    public static void releaseConnection() {

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
