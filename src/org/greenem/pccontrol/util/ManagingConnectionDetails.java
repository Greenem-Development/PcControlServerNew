package org.greenem.pccontrol.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ManagingConnectionDetails {
	public static void close(OutputStream os) {
		if(os==null) return;
		try {
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ObjectOutputStream oos) {
		if(oos==null) return;
		try {
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(InputStream is) {
		if(is==null) return;
		try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ObjectInputStream ois) {
		if(ois==null) return;
		try {
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Socket s) {
		if(s==null) return;
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ServerSocket ss) {
		if(ss==null) return;
		try {
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
