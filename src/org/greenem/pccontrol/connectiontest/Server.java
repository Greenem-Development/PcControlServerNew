package org.greenem.pccontrol.connectiontest;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.greenem.pccontrol.packets.Packet;
import org.greenem.pccontrol.packets.TextMessagePacket;

public class Server {
	public static InetAddress ia = null;
	public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ServerSocket awaiting connections...");
        while (true) {
	        Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
	        System.out.println("Connection from " + socket + "!");
	
	        InputStream inputStream = socket.getInputStream();
	        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
	        
	        Packet packet = (Packet) objectInputStream.readObject();
	        if(packet instanceof TextMessagePacket) {
	        	System.out.println(((TextMessagePacket) packet).getText());
	        }
	        
	        InetAddress newIa = socket.getInetAddress();
	        if(ia!=null) {
	        	System.out.println(ia.equals(newIa));
	        }
	        
	        ia = newIa;
	        
	        System.out.println("Closing socket");
        socket.close();
        }
//        ss.close();
    }
}
