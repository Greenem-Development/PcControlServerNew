package org.greenem.pccontrol.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.greenem.pccontrol.connection.safety.Threshold;
import org.greenem.pccontrol.connection.safety.WhitelistBlacklist;
import org.greenem.pccontrol.packets.DisconnectPacket;
import org.greenem.pccontrol.util.Exit;
import org.greenem.pccontrol.util.ManagingConnectionDetails;
import org.greenem.pccontrol.util.OwnLogger;

public class ConnectorListener {
	private int ssPort;
	private boolean stop = false;
	
	public ConnectorListener(int incomingPort) {
		ssPort = incomingPort;
	}
	
	public void startListening() {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(ssPort); // backlog arg?
		} catch (BindException e) {
			OwnLogger.log("Can't start the connection on the specified port, please fix that");
			Exit.doExit();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		while (!stop) { // for now it's forever
			Socket socket = null;
			try {
				socket = ss.accept();
			} catch (IOException e) {
//				e.printStackTrace();
				return;
			}
			
			OutputStream os = null;
			ObjectOutputStream oos = null;
			try {
				os = socket.getOutputStream();
				oos = new ObjectOutputStream(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			InetAddress ia = ss.getInetAddress();
			if(!WhitelistBlacklist.isAdressAllowed(ia)) {
				try {
					oos.writeObject(new DisconnectPacket("The adress your programm is connecting from is not allowed on this device by the user's settings"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				ManagingConnectionDetails.close(oos); ManagingConnectionDetails.close(os); ManagingConnectionDetails.close(socket);
			}
			if(Threshold.isNotLessThanThreshold(ia)) {
				Threshold.setNewLastTime(ia);
			} else {
				try {
					oos.writeObject(new DisconnectPacket("You are trying to connect too often"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				ManagingConnectionDetails.close(oos); ManagingConnectionDetails.close(os); ManagingConnectionDetails.close(socket);
			}
			
			SingleSocketHandler handler = new SingleSocketHandler(socket);
			new Thread(new Runnable() {
				@Override
				public void run() {
					handler.start();
				}
			}).start();
		}
	}
}
