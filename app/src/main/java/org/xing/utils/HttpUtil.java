package org.xing.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Map;
import java.util.Vector;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public class HttpUtil {
	public static String bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	public static String md5Encode(String str) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest md5=MessageDigest.getInstance("MD5");
        String newstr=bytes2HexString(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
	
    public static String formPostParams(Map<String, Object> params, String charset, String secret)
            throws UnsupportedEncodingException {
        StringBuilder str = new StringBuilder();
        
        Vector<String> fields = new Vector<>();
        for(Map.Entry<String, Object> en : params.entrySet()) {
        	fields.add(URLEncoder.encode(en.getKey(), charset)+
        			"="+URLEncoder.encode(en.getValue().toString(), charset));
        }
        
        Collections.sort(fields);
        for(String field : fields) {
        	if(str.length() > 0) {
        		str.append("&");
        	}
        	str.append(field);
        }
        
        try {
			String sign = md5Encode(str.toString()+secret);
			str.append("&sign="+sign);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

        //System.out.println(str.toString());
        
        return str.toString();
    }

    public static String doPost(String urlString, Map<String, Object> params, String charset) {
        StringBuilder result = new StringBuilder();
        String secret = "862369397363725329";
        try{
            String bodyData = formPostParams(params, charset, secret);

            URL url = new URL(urlString);
            HttpURLConnection urlConn=(HttpURLConnection)url.openConnection();
            urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);
            urlConn.setRequestMethod("POST");
            urlConn.setUseCaches(false);
            urlConn.connect();

            DataOutputStream out = new DataOutputStream(urlConn.getOutputStream());
            out.writeBytes(bodyData);
            
            String line = null;
            BufferedReader reader = new BufferedReader(
            		new InputStreamReader(urlConn.getInputStream(), "utf-8"));
            while((line = reader.readLine())  != null) {
            	result.append(line);
            }
            //System.out.println(result.toString());

            out.flush();
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return result.toString();
    }
}
