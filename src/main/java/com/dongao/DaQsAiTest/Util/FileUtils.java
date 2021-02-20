package com.dongao.DaQsAiTest.Util;

/**
 * @Author: yule
 * @Description:
 * @Date: create in 2021/1/22 4:31 下午
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonToYamlUtils.class);

    /**
     * 读取json文件，返回json串
     *
     * @param fileName
     * @return
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString().substring(1, sb.length() - 1);
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 读json.chalres文件，
     * 1.去掉[]
     * 2.将obj="" 替换成obj=null
     */
    public static String actionPerformed(String filePath) {
        String line = null;
        String newFile = null;
        if (filePath.contains("new_") || filePath.contains("DS")) { //DS为系统生成的隐藏文件，这个文件不需要执行
            logger.info("这是new_文件或是Ds文件，不需要更新的charles导出文件");
        } else {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
                int i = filePath.lastIndexOf("/");
                newFile = filePath.substring(0, i + 1) + "new_" + filePath.substring(i + 1);
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "UTF-8"));

                while ((line = in.readLine()) != null) {
                    //去掉前后[]
                    line = line.substring(1, line.length() - 1);
                    //将obj 换成null，用于解析post请求体
                    if (line.contains("\\\"obj\\\":\\\"\\\"")) {
                        line = line.replace("\\\"obj\\\":\\\"\\\"", "\\\"obj\\\":null");
                    }
                    out.write(line);
                    out.newLine();
                }
                logger.info("创建新文件成功，去掉前后[],修改obj:null====》" + newFile);
                //清楚缓存
                out.flush();
                //关闭流
                in.close();
                out.close();

            }     //end try
            catch (Exception e) {

            }  //end catch

        }
        return newFile;
    }

    /**
     * 删除老文件，只保留新文件，并改名成老文件
     */
    public static void changeFile(String oldFile, String newFile) {
        File oldfile = new File(oldFile);
        File newfile = new File(newFile);

        if (oldfile.exists()) {
            newfile.renameTo(oldfile);
        }
        logger.info("新文件覆盖老文件成功");
    }

    /**
     * 删除文件
     */
    public static void deleteFile(String oldFile) {
        File file = new File(oldFile);
        if (file.exists()) {
            file.delete();
            logger.info(oldFile + "文件删除成功！！！！");
        } else {
            logger.info(oldFile + "文件不存在！！！！");
        }
    }

    /**
     * 递归查找文件夹里的文件
     */
    static List<String> finaldirList=new ArrayList<>();
    public static List findDir(String path) {
        List<List<String>> dirList = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        String filepath=file2.getAbsolutePath();
                        findDir(filepath);
                        if(!filepath.substring(filepath.length()-2).contains("V")){
                            finaldirList.add(filepath.substring(filepath.indexOf("V")));
                        }
                    }
                }

            }
        } else {
            System.out.println("文件不存在!");
        }

        return finaldirList;
    }

    /**
     * 文件回滚
     */
    public static void fileRollBack(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    //如果是文件夹，继续递归寻找底层文件
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        fileRollBack(file2.getAbsolutePath());
                    } else if(file2.getAbsolutePath().contains("new_")){
                        //这里开始真正的回滚
                        //文章前后增加[]
                        String originalfile=file2.getAbsolutePath().split("new_")[0]+file2.getAbsolutePath().split("new_")[1];//创建文件新名
                        BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(file2.getAbsolutePath())));
                        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(originalfile)));
                        String lineText=null;
                        while((lineText=buffer.readLine()) != null){
                            lineText.replace("\n","");
                            lineText="["+lineText+"]";
                            writer.write(lineText);
                        }
                        writer.close();
                        FileUtils.deleteFile(file2.getAbsolutePath());
                    }
                }
            }
        } else {
        System.out.println("文件不存在!");
        }
    }
    public static void main(String[] args) throws IOException {
//        System.out.println("最后结果"+findDir("src/main/resources/com.dongao.DaQsAiTest/case"));
        fileRollBack("src/main/resources/com.dongao.DaQsAiTest/data/V1/curriculum");
    }
}