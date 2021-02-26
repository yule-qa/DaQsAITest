package com.dongao.DaQsAiTest.Model;

import com.dongao.DaQsAiTest.Util.JdbcUtils;
import io.restassured.response.Response;

import java.sql.*;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * @Author: yule
 * @Description: 方法模型 ：
 * yaml文件action 节点下内容，解析成ApiObjectActionModel对象
 * 对象属性为yaml-actions下节点
 * 代表了一个单一的http接口
 * @Date: create in 2021/1/11 1:45 下午
 */
public class ApiObjectActionModel {


    public String post;
    public String get;


    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public Response run(HashMap query) throws SQLException {
        String queryuserExtendId=null;
        String url = "";
        String method = "get";
        if (post != null) {
            url = post;
            method = "post";
        }
        if (get != null) {
            url = get;
            method = "get";
        }
        //读取配置文件，获得域名与ip对应关系，在此替换 ,这里解决多环境问题
//        url=url.replaceAll("domain","ip");
        if(query.containsKey("userExtendId") && query.containsKey("userId")) {
            //调用数据库,更新userExtendId
            String sql = "SELECT qsue.id FROM ei_qs_study.qs_study_user_extend AS qsue\n" +
                    "WHERE qsue.is_valid = 1 \n" +
                    "AND qsue.is_valid = 1 \n" +
                    "AND qsue.user_id = " + query.get("userId") + "\n" +
                    "AND qsue.`year` = 2021\n";

            ResultSet resultSet = JdbcUtils.startDbQuery(sql);
            //获取case.yaml文件中的userExtendId
            String userExtendId = query.get("userExtendId").toString();
            while (resultSet.next()) {
                //获取数据库中更新后的userExtendId
                queryuserExtendId = resultSet.getString("id");
            }
            //比较，如果不同，就覆盖发送请求参数值，如果相同，就直接发送请求，无需修改
            if (userExtendId != queryuserExtendId) {
                query.put("userExtendId", queryuserExtendId);
            }
            //释放数据库连接
            JdbcUtils.releaseConnection();
        }
        //数据准备好，正式发送请求
        Response response = given().log().all().formParams(query)
                .contentType("application/x-www-form-urlencoded;charset=utf-8")
                .request(method, url)//发送请求
                .then().log().all() // 打印日志
                .statusCode(200)
                .extract().response(); //截取响应;
        return response;
    }
}

