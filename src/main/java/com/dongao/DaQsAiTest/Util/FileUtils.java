package com.dongao.DaQsAiTest.Util;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/22 4:31 下午
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class FileUtils {

    //json转对象
    public static void main(String[] args){
        actionPerformed();
//        String path = FileUtils.class.getClassLoader().getResource("test.json").getPath();
//        String s = FileUtils.readJsonFile("src/main/resources/com.dongao.DaQsAiTest/data/course_syncCourseRecord.chlsj");
//        JSONObject jobj = JSON.parseObject(s);
//        JSONObject text = jobj.getJSONObject("response").getJSONObject("body").getJSONObject("text");
//        String obj=text.get("obj").toString();
//
//        System.out.println("obj:"+obj);

//
//        JSONArray links = jobj.getJSONArray("links");
//
//        for (int i = 0 ; i < links.size();i++){
//            JSONObject key1 = (JSONObject)links.get(i);
//            String name = (String)key1.get("name");
//            String url = (String)key1.get("url");
//            System.out.println(name);
//            System.out.println(url);
//        }
    }

    /**
     * 读取json文件，返回json串
     * @param fileName
     * @return
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString().substring(1,sb.length()-1);
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void actionPerformed() {

        ArrayList<String> lines = new ArrayList<String>();
        String line = null;

        try {
            FileReader fr = new FileReader("src/main/resources/com.dongao.DaQsAiTest/data/course_syncCourseRecord.chlsj");
            BufferedReader br = new BufferedReader(fr);

            line = br.readLine();
            while (line != null) {
                line.replaceFirst("[","");
                if (line.contains("obj:\"\"")){
                    line.replace("\"obj\":\"\"","\"obj\":null");
                    }
                lines.add(line);

                FileWriter fw = new FileWriter("src/main/resources/com.dongao.DaQsAiTest/data/course_syncCourseRecord1.chlsj");
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(line);
                fw.close();
                bw.close();

            }  //end if

        }     //end try
        catch (Exception e) {

        }  //end catch
    }

}