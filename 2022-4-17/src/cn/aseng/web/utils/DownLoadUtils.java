package cn.aseng.web.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class DownLoadUtils {

    public static String getFileName(String agent,String filename) throws UnsupportedEncodingException {
        if(agent.contains("MSIE")){
            filename= URLEncoder.encode(filename,"utf-8");
            filename=filename.replace("+"," ");
        }else if(agent.contains("FireFox")){
            BASE64Encoder base64Encoder = new BASE64Encoder();
            filename = "=?utf-8?B?" + base64Encoder.encode
                    (filename.getBytes("utf-8")) + "?=";

        }else{
            filename=URLEncoder.encode(filename,"utf-8");
        }
        return filename;
    }
}
