package cn.daoyun.entity.util;

/**
 * 字符串工具类  用户 判断 字符串是否为空的工具类
 * @author cdfengyang
 *
 */
public class StringUtil {

	public static boolean isEmpty(String str){
		if("".equals(str)||str==null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(String str){
		if(!"".equals(str)&&str!=null){
			return true;
		}else{
			return false;
		}
	}
}
