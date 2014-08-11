package org.util.demo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class mac {

	public static String getMACAddress(String ip){
		String str = "";
		String macAddress = "";
		try {
		Process p = Runtime.getRuntime().exec("arp -a " );
		InputStreamReader ir = new InputStreamReader(p.getInputStream());
		LineNumberReader input = new LineNumberReader(ir);
		for (int i = 1; i <100; i++) {
		str = input.readLine();
		if (str != null) {
		if (str.indexOf(ip) >1) {
		macAddress = str.substring(str.indexOf(ip)+20,str.length()-10); 
		break;
		} }}}catch (IOException e) {
		e.printStackTrace(System.out);
		}
		return macAddress;
	}

	public static void main(String[] args) {
		getMACAddress("172.17.36.45");
	}
}
