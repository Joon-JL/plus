package com.sds.secframework.common.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class ResponseWriter {

    public static void wirteJSON(HttpServletResponse response, JSONObject json)
    {
        try{
            response.setContentType("text/json; charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().print(json);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void wirteJSON(HttpServletResponse response, String json)
    {
        try{
            response.setContentType("text/json; charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().print(json);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void wirteHTML(HttpServletResponse response, String html)
    {
        try{
            response.setContentType("text/html; charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().print(html);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
