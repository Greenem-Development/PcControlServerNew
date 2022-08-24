package org.greenem.pccontrol.connectiontest;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.greenem.pccontrol.packets.TextMessagePacket;

public class Client {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Socket socket = new Socket("localhost", 7777);
		OutputStream outputStream = socket.getOutputStream();
	    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
	    
	    TextMessagePacket packet = new TextMessagePacket("Hello");
	    System.out.println("Sending messages to the ServerSocket");
	    objectOutputStream.writeObject(packet);
	
	    System.out.println("Closing socket and terminating program.");
	    socket.close();
	}
}
