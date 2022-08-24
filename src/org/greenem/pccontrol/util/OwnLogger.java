package org.greenem.pccontrol.util;

public class OwnLogger {
	public static void log(String s) {
		System.out.println(s);
	}
	
	public static void log(int i) {
		log(i+"");
	}
	
	public static void log(long l) {
		log(l+"");
	}
	
	public static void log(float f) {
		log(f+"");
	}
	
	public static void log(double d) {
		log(d+"");
	}
	
	public static void log(char c) {
		log(c+"");
	}
	
	public static void log(Object o) {
		if(o==null) log("null");
		else log(o.toString());
	}
}
