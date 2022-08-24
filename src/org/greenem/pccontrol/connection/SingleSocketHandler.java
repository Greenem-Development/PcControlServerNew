package org.greenem.pccontrol.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicReference;

import org.greenem.pccontrol.data.References;
import org.greenem.pccontrol.packets.AuthPacket;
import org.greenem.pccontrol.packets.DisconnectPacket;
import org.greenem.pccontrol.packets.Packet;
import org.greenem.pccontrol.util.ManagingConnectionDetails;

public class SingleSocketHandler {
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public SingleSocketHandler(Socket socket) {
		this.socket = socket;
	}
	
	private void init() {
		try {
			is = socket.getInputStream();
			os = socket.getOutputStream();
			ois = new ObjectInputStream(is);
			oos = new ObjectOutputStream(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		if(socket==null) return; // default?
		init();
		
		AtomicReference<Thread> upperThread = new AtomicReference<>();
		upperThread.set(Thread.currentThread());
		
		AtomicReference<Object> recievedObject = new AtomicReference<>();
		
		Thread waitingThread = new Thread(new Runnable() {
			@Override
			public void run() {
				Object o = null;
				try {
					o = ois.readObject();
				} catch (ClassNotFoundException e) {
					disconnect("ClassNotFoundException"); return;
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
				if(o==null) {
					disconnect("Null sent"); return;
				}
				recievedObject.set(o);
				upperThread.get().notify();
			}
		});
		waitingThread.start();
		
		try {
			upperThread.wait(References.authTimeout);
			disconnect("Auth timeout");
		} catch (InterruptedException e) {
			processRecievedAuthObject(recievedObject.get());
		}
	}
	
	private void processRecievedAuthObject(Object o) {
		if(!(o instanceof Packet)) {
			disconnect("Wrong connection usage"); return;
		}
		if(!(o instanceof AuthPacket)) {
			disconnect("Wrong packet"); return;
		}
		AuthPacket ap = (AuthPacket) o;
//		if(ap.getAuthType()!=References.authType) {
//			disconnect("Wrong auth type sent"); return;
//		}
		if(!(References.pwd==null || References.pwd.trim().equals("")) && !ap.getPassword().equals(References.pwd.trim())) {
			disconnect("Wrong password"); return;
		}
//		if(group)
		afterAuth();
	}
	
	private void afterAuth() {
		
	}
	
	@SuppressWarnings("unused")
	private void disconnect() {
		disconnect(null);
	}
	
	private void disconnect(String reason) {
		if(reason!=null) {
			DisconnectPacket dp = new DisconnectPacket(reason);
			try {
				oos.writeObject(dp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ManagingConnectionDetails.close(oos);
		ManagingConnectionDetails.close(ois);
		ManagingConnectionDetails.close(os);
		ManagingConnectionDetails.close(is);
		ManagingConnectionDetails.close(socket);
	}
}
