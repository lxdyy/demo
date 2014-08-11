package org.util.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 通过arp命令获取MAC地址：
 * 
 *通过IP获取MAC地址也可以用nbtstat命令，但是nbtstat获取不到ipad的mac地址，所以现在改用arp命令获取mac地址
 *但通过arp命令获取地址只能是与服务器有过通信的才能查看，所以在代码中有一次ping一次对方IP的方法，
 *	1、确保IP通信正常；
 *	2、建立与对面IPAD的通信
 *详细见代码，如有更好的建议忘各位指正；
 * 
 * @author Administrator
 *
 */
public class Testjar {

//	public static final String IP = "172.17.36.45";

	public static void main(String[] args) throws IOException {
		BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("输入IP:");
		String IP = strin.readLine();
		System.out.println(GetMacAddress(IP));
	}


	/**
	 * 判断IP是否非法
	 * 判断IP是否为本地IP 例如：127.0.0.1
	 * 判断IP是否可正常通信
	 * 获取MAC地址
	 * @param ip
	 * @return
	 */
	private static String GetMacAddress(String ip) {
		if(!booleanIP(ip)){
			return "IP非法";
		}
		if(ip.equalsIgnoreCase("127.0.0.1")){
			return "不能输入本地地址";
		}
		System.out.println("输入的IP地址为"+ip);
		String str = "";
		String macAddress = "";
		try {
			if (!Ping(ip)) {
				return "无法访问";
			}
			System.out.println("IP可以正常访问");
			Process p = Runtime.getRuntime().exec("arp -a ");
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf(ip) > 1) {
						macAddress = str.substring(str.indexOf(ip) + 22,str.length() - 11);
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return macAddress;
	}

	/**
	 * 验证IP是否非法
	 * @param ip
	 * @return
	 */
	private static boolean booleanIP(String ip) {
		String p = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
		Pattern pattern = Pattern.compile(p);
		Matcher matcher = pattern.matcher(ip);
		return matcher.matches();
	}

	/**
	 * 判断ip通信是否通畅
	 * @param ip
	 * @return
	 * @throws IOException
	 */
	private static boolean Ping(String ip) throws IOException {
		System.out.println("进行IP在线监测");
		boolean f = false;
		Process process = Runtime.getRuntime().exec("ping " + ip);
		InputStreamReader r = new InputStreamReader(process.getInputStream());
		LineNumberReader returnData = new LineNumberReader(r);
		String line = "";
		while ((line = returnData.readLine()) != null) {
			if(line.indexOf(String.valueOf("TTL")) == -1){
			}else {
				f = true;
				break;
			}
		}
		return f;
	}
	
}
