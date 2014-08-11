package org.util.demo;

import java.sql.Time;
import org.util.demo.*;
/**
 * 将一个字符串逆序，不要使用反转函数
 * @author Administrator
 *
 */
public class message {
	
	public static void main (String [] args){

		String message = "hello world";
		StringBuilder sb = new StringBuilder();
		for (int i = message.length()-1;i>=0;i--){
			sb.append(message.charAt(i));
		}
		System.out.println(sb.toString());
	}

}
