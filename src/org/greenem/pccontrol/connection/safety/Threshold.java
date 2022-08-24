package org.greenem.pccontrol.connection.safety;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import org.greenem.pccontrol.data.References;

public class Threshold {
	public static Map<InetAddress, Long> lastConnectionForAdress = new HashMap<>();
	
	public static void setNewLastTime(InetAddress address) {
		lastConnectionForAdress.put(address, getTime());
	}
	
	public static boolean isNotLessThanThreshold(InetAddress address) {
		Long lastTime = lastConnectionForAdress.get(address);
		if(lastTime==null) return true;
		return getTime() - lastTime >= References.connectionThreshold;
	}
	
	private static long getTime() {
		return System.currentTimeMillis();
	}
	
//	public static void main(String[] args) {
//		Map<String, Long> a = new HashMap<>();
//		Long l = a.get("sdfgd");
//		System.out.println(l);
//	}
}
