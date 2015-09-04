package com.suntt.pingguoid;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2015/8/30.
 */
public class HttpUtil {
    public static String sendHttpRequest(String url) {
        BufferedReader reader = null;
        String result = null;
        String charset = "utf-8";
        StringBuffer sbf = new StringBuffer();
        String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
        try {
            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)url1.openConnection();
            connection.setRequestMethod("GET");
//            connection.setReadTimeout(30000);
//            connection.setConnectTimeout(30000);
            connection.setRequestProperty("User-agent",userAgent);
//            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(
                    is, charset));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            connection.disconnect();
            result = sbf.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;


    }
}
