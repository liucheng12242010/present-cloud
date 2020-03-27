package cn.tedu.entity.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * 响应 工具类  获得打印流的写方法 将对象写入到 页面上
 * @author cdfengyang
 *
 */
public class ResponseUtil {

	public static void write(HttpServletResponse response,Object o) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
