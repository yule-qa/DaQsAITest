package com.dongao.DaQsAiTest.Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
//import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class GetSign {
	
	

	 public static String vmsSign(String url,String method,Map params, String salt) {
		  
		    String address = method + url + "?";
		    String signStr = address + paramsMap2String(params);
	        try {
	            return new String(EncryptUtils.base64Encode(EncryptUtils.HMACSHA256(signStr, salt)));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return "";
	    }
	 
	 public static String paramsMap2String(Map params) {
	        Set<String> keySet = params.keySet();
	        String[] arrayParams = new String[keySet.size()];
	        int j = 0;
	        for (String key : keySet) {
	            arrayParams[j] = key;
	            j++;
	        }
	        Arrays.sort(arrayParams);
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < arrayParams.length; i++) {
	            if (arrayParams[i] != null && !"".equals(arrayParams[i])) {
	                sb.append(arrayParams[i])
	                        .append("=")
	                        .append(params.get(arrayParams[i]))
	                        .append("&");
	            }
	        }
	        return sb.substring(0, sb.length() - 1);
	    }
	 public static String sign(Map params, final String salt) {
		    String signStr = paramsMap2String(params) + salt;
		    System.out.println("拼接字符串值是====》"+signStr);
		    String str =  encrypt(signStr);
		    System.out.println("发送sign值是====》"+str);
		    return str;
		    //return EncryptUtils.encryptMD5ToString(signStr);
		}
	 public static String encrypt(final String key) {
		    // md.digest() 该函数返回值为存放哈希值结果的byte数组
		    MessageDigest md5;
		    StringBuffer hexValue = new StringBuffer();
		    try {
		      md5 = MessageDigest.getInstance("MD5");
		      byte[] md5Bytes = md5.digest(key.getBytes("UTF-8"));
		      for (int i = 0; i < md5Bytes.length; i++) {
		        int val = ((int) md5Bytes[i]) & 0xff;
		        if (val < 16) {
		          hexValue.append("0");
		        }
		        hexValue.append(Integer.toHexString(val));
		      }
		    } catch (NoSuchAlgorithmException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    } catch (UnsupportedEncodingException e) {
		      e.printStackTrace();
		    }
		    ;
		    return hexValue.toString();
		  }
 

		public static void main(String[] args) {
			String apiurl="https://mm.dongaocloud.com/interface/v1/uims/user";
			String apimethod="POST";
			HashMap mp=new HashMap();
			mp.put("Action","MergeProfile");
//			mp.put("Timestamp",Calendar.getInstance().getTimeInMillis());
//			mp.put("Nonce",);
			mp.put("FromUserId","31906175");
			mp.put("ToUserId","1485");
			mp.put("SecretId","9c3a4a6daa084b1d472641c888fc75f0");
			mp.put("SignatureMethod","HMAC-SHA256");
			mp.put("SignatureVersion","v1.0");
			mp.put("Token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTMyNDY4ODcsImlhdCI6MTU5MDUzNDg4NywiaWNvblVybCI6ImFIUjBjSE1sTTBFbE1rWWxNa1ppYVhwbWFXeGxjeTVrYjI1bllXOHVZMjl0SlRKR1ltbDZKVEpHYUdWaFpIQnBZeVV5Um1obFlXUndhV05yZFNVeVJqSXdNVGtsTWtabFpUWTRNMkUwT0Rka04yVXpNekV5T0RNNE5EQTVZMk5sWkRkbE9XVTRNU1V5UmtoRlFVUmZVRWxEWDFOTlFVeE1Yek5rTnpBMlpHWXlZemMzTmpSbVlUbGlOR1E1TlRFM1pESXdZMk0zT0dZeExtcHdadyIsImlzcyI6ImxpdmUuZG9uZ2FvY2xvdWQuY29tIiwibmlja05hbWUiOiJhVTlUSlVVM0pUbEJKVGcwSlVVMkpUazRKVUkxSlVVM0pVRTNKVUl3SlVVMUpUa3hKVGd3Iiwib3JpZ19pYXQiOjE1OTA2NTQ4ODcsInN1YiI6ImRvbmdhb19saXZlIiwidXNlcklkIjoiMzE5MDYxNzUiLCJ1c2VyTmFtZSI6ImFVOVQ1NXFFNXBpMTU2ZXc1WkdBIn0.4YqOzsBf58fUNulIu-thvRjPAVjswnabXKik9tC60QE");
			
			String vs=vmsSign(apiurl,apimethod,mp,"0b64499ac4e4e17bbe68d168c51f8922");
			System.out.println(vs);
		 }
		
}	