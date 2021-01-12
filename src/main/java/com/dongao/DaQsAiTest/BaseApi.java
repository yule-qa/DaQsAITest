package com.dongao.DaQsAiTest;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: yule
 * @Description:管理ApiObjectModel
 * @Date: create in 2021/1/11 2:22 下午
 */
public class BaseApi {
    //保存了所有api对象
    List<ApiObjectModel> apis=new ArrayList<>();

    /**
     * 通过给定目录，将所有的api对象， 加载到apis里
     */
    public void load(String dir){
        //通过流式方法，将目录下所有的yaml 文件，通过调用load方法,返回ApiObjectModel对象，并加入apis
        Arrays.stream(new File(dir).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return true;
            }
        })).forEach(path -> {
            try {
                apis.add(ApiObjectModel.load(dir+"/"+path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 根据测试用例中提供的api object和对应的action，从自己的数据中检索对应的api，并调用对应的方法
     * @param name
     * @param action
     */

    public void run(String name,String action){
        //apis为一个List，所以用流式对象来遍历里面的值api。并且执行api里的方法。来执行测试用例
        apis.stream().filter(api->api.name.equals(name)).forEach(api->{
            api.actions.get(action).run(); //这里实际调用过的是ApiObjectActionModel里的run方法
        });
    }
}
