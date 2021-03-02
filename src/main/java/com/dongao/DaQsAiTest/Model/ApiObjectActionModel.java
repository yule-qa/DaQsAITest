package com.dongao.DaQsAiTest.Model;

import com.dongao.DaQsAiTest.Util.JdbcUtils;
import com.dongao.DaQsAiTest.Util.SqlUtils;
import io.restassured.response.Response;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
    public String sqllable;


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
            sqllable="userExtendId";
            query= SqlUtils.getSql(sqllable,query);

        }
        //如果查询条件中，有查询数据库选项，则调用查询数据库sql，执行sql
        if(query.containsValue("dbsql")){
            Set set=query.entrySet();
            Iterator it=set.iterator();
            while(it.hasNext()) {
                Map.Entry entry=(Map.Entry)it.next();
                if(entry.getValue().equals("dbsql")) {
                    query=SqlUtils.getSql(entry.getKey().toString(),query);
                }
                }
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

