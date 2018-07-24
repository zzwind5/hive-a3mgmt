package com.aerohive.nms.a3.communicator.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class A3CommunicatorUtil {

	public static String getLocalMac() {
		try {
			InetAddress ia = InetAddress.getLocalHost();
			byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
			StringBuffer sb = new StringBuffer("");
			for(int i=0; i<mac.length; i++) {
				int temp = mac[i]&0xff;
				String str = Integer.toHexString(temp);
				if(str.length()==1) {
					sb.append("0"+str);
				}else {
					sb.append(str);
				}
			}
			return sb.toString().toUpperCase();
		}catch(Exception e) {
			throw new RuntimeException("Get local MAC failed.");
		}
	}
	
	public static void main(String... strings) throws UnknownHostException, SocketException {
		System.out.println(getLocalMac());
	}
}
