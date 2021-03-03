package com.dongao.DaQsAiTest.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/3/2 2:16 下午
 */
public class SqlUtils {
    private static String sql;
    public static HashMap getSql(String sqlLabel, HashMap query) throws SQLException {
        if(sqlLabel.equals("userExtendId")) {
            sql = "SELECT qsue.id FROM ei_qs_study.qs_study_user_extend AS qsue\n" +
                    "WHERE qsue.is_valid = 1 \n" +
                    "AND qsue.is_valid = 1 \n" +
                    "AND qsue.user_id = " + query.get("userId") + "\n" +
                    "AND qsue.`year` = 2021\n";
            ResultSet resultSet = JdbcUtils.selectquery(sql);
            //获取case.yaml文件中的userExtendId
            String userExtendId = query.get(sqlLabel).toString();
            String queryuserExtendId = null;
            while (resultSet.next()) {
                //获取数据库中更新后的userExtendId
                queryuserExtendId = resultSet.getString("id");
            }
            //比较，如果不同，就覆盖发送请求参数值，如果相同，就直接发送请求，无需修改
            if (userExtendId != queryuserExtendId) {
                query.put("userExtendId", queryuserExtendId);
            }
        }
        //鼓励
        if(sqlLabel.equals("encourageUserExtendId")){
            sql="select user_extend_id from ei_qs_study.qs_study_user_class where  is_valid = 1 order by rand() LIMIT 1";
            ResultSet resultSet = JdbcUtils.selectquery(sql);
            //获取case.yaml文件中的userExtendId
            String encourageUserExtendId = query.get(sqlLabel).toString();
            String queryuserExtendId = null;
            while (resultSet.next()) {
                //获取数据库中更新后的userExtendId
                queryuserExtendId = resultSet.getString("user_extend_id");
            }
            //比较，如果不同，就覆盖发送请求参数值，如果相同，就直接发送请求，无需修改
            if (encourageUserExtendId != queryuserExtendId) {
                query.put("encourageUserExtendId", queryuserExtendId);
            }
        }

        if(sqlLabel.equals("content")){
            sql="delete  from ei_qs_solve.qs_solve_solve_detail_reply_comment where reply_id="+query.get("replyId");
            JdbcUtils.otherquery(sql);
            query.put("content", "自动化测试用例评价");
        }
        //释放数据库连接
        JdbcUtils.releaseConnection();
        return query;

    }



}
