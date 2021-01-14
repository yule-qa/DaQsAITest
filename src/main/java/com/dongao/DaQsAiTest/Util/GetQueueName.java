package com.dongao.DaQsAiTest.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;


public class GetQueueName {
    public static String uncompress(String str) {

        Inflater decompressor = new Inflater();
        ByteArrayOutputStream bos = null;
        byte[] value = null;
        try {
        	value = str.getBytes("ISO-8859-1");
            bos = new ByteArrayOutputStream(value.length);
            decompressor.setInput(value);
            final byte[] buf = new byte[1024];
            while (!decompressor.finished()) {
                int count = 0;
                count = decompressor.inflate(buf);
                bos.write(buf, 0, count);
                if (count <= 0) {
                    break;
                }
            }
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } 
        catch (DataFormatException e) {
            e.printStackTrace();
        } finally {
            decompressor.end();
        }
        if (bos != null) {


            return bos.toString();

        }
        return "解压不成功";
    }
    

    public static String getQueueName(String finaldata) {
    	String msgText=uncompress(finaldata);
        JSONObject object = JSON.parseObject(msgText);
        JSONObject json = object.getJSONObject("data");
        JSONObject common = json.getJSONObject("common");
        String queueName = common.getString("queueName");
        return queueName;
    }
    

}