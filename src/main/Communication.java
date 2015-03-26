package main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Communication {
	
	private int port;
	private MulticastSocket socket;
	private InetAddress address;

	public Communication(String ip, int port) throws IOException {
		this.port = port;
		this.address = InetAddress.getByName(ip);
		this.socket = new MulticastSocket(this.port);
		
		//join address to multicast group
		socket.joinGroup(address);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public MulticastSocket getSocket() {
		return socket;
	}

	public void setSocket(MulticastSocket socket) {
		this.socket = socket;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}
	
	

}
