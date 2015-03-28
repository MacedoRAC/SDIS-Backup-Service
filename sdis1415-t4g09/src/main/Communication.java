package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Communication {
	
	private int port;
	private MulticastSocket socket;
	private InetAddress address;
	private int chunkSize;

	public Communication(String ip, int port) throws IOException {
		this.port = port;
		this.address = InetAddress.getByName(ip);
		this.socket = new MulticastSocket(this.port);
		this.chunkSize = 64000;
		
		//join address to multicast group
		socket.joinGroup(address);
	}
	
	public void send(byte[] args){
		DatagramPacket packet = new DatagramPacket(args, args.length, address, port);
		
		try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public byte[] receive(){
		byte[] msg = new byte[chunkSize];
		DatagramPacket packet = new DatagramPacket(msg, msg.length);
		
		try {
			socket.receive(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;		
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
