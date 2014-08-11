package org.util.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ��ȡMAC��ַ
 * 
 * @author 
 */
public class GetMacAddress {

	public static String callCmd(String[] cmd) {
		String result = "";
		String line = "";
		try {
			Process proc = Runtime.getRuntime().exec(cmd);
			InputStreamReader is = new InputStreamReader(proc.getInputStream());
			BufferedReader br = new BufferedReader(is);
			while ((line = br.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param cmd
	 *            ��һ������
	 * @param another
	 *            �ڶ�������
	 * @return �ڶ��������ִ�н��
	 */
	public static String callCmd(String[] cmd, String[] another) {
		String result = "";
		String line = "";
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmd);
			proc.waitFor(); // �Ѿ�ִ�����һ�����׼��ִ�еڶ�������
			proc = rt.exec(another);
			InputStreamReader is = new InputStreamReader(proc.getInputStream());
			BufferedReader br = new BufferedReader(is);
			while ((line = br.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param ip
	 *            Ŀ��ip,һ���ھ�������
	 * @param sourceString
	 *            ������Ľ���ַ���
	 * @param macSeparator
	 *            mac�ָ�����
	 * @return mac��ַ��������ķָ����ű�ʾ
	 */
	public static String filterMacAddress(final String ip,
			final String sourceString, final String macSeparator) {
		String result = "";
		String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator
				+ "){1,5})[0-9,A-F,a-f]{1,2})";
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(sourceString);
		System.out.println(pattern);
		while (matcher.find()) {
			result = matcher.group(1);
			if (sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher
					.group(1))) {
				break; // ����ж��IP,ֻƥ�䱾IP��Ӧ��Mac.
			}
		}
		return result;
	}

	/**
	 * 
	 * @param ip
	 *            Ŀ��ip
	 * @return Mac Address
	 * 
	 */
	public static String getMacInWindows(final String ip) {
		String result = "";
		String[] cmd = { "cmd", "/c", "ping " + ip };
		String[] another = { "cmd", "/c", "arp -a" };

		String cmdResult = callCmd(cmd, another);
		result = filterMacAddress(ip, cmdResult, "-");
		return result;
	}

	/**
	 * 
	 * @param ip
	 *            Ŀ��ip
	 * @return Mac Address
	 * 
	 */
	public static String getMacInLinux(final String ip) {
		String result = "";
		String[] cmd = { "/bin/sh", "-c", "ping " + ip + " -c 2 && arp -a" };
		String cmdResult = callCmd(cmd);
		result = filterMacAddress(ip, cmdResult, ":");

		return result;
	}

	/**
	 * ��ȡMAC��ַ
	 * 
	 * @return ����MAC��ַ
	 */
	public static String getMacAddress(String ip) {
		String macAddress = "";
		macAddress = getMacInWindows(ip).trim();
		if (macAddress == null || "".equals(macAddress)) {
			macAddress = getMacInLinux(ip).trim();
		}
		return macAddress;
	}

	/**
	 * ����
	 */
	public static void main(String[] args) {
		System.out.println(getMacAddress("172.17.36.39"));
	}
}