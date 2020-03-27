package cn.daoyun.entity.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类  用于日期格式和 字符串之前的转换
 * @author cdfengyang
 *
 */
public class DateUtil {

	public static String formatDate(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}
	
	public static Date formatString(String str,String format) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
}
