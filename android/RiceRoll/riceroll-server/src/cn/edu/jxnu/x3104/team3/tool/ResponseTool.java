package cn.edu.jxnu.x3104.team3.tool;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ResponseTool {

	public static void initResponse(HttpServletResponse response,JSONObject json_result){
		try {
			response.setContentType("text/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");		
			byte[] bytes=json_result.toString().getBytes("utf-8");
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void initResponse(HttpServletResponse response,JSONArray JSONAarray){
		try {
			response.setContentType("text/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");		
			byte[] bytes=JSONAarray.toString().getBytes("utf-8");
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
