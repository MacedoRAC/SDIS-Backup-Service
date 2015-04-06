package protocols;

import java.io.IOException;

import main.Communication;
import main.Main;

public class Control extends Thread{
	private static Communication com;
	boolean working;
	byte[] body;
	String[] header;

	public Control(String ip, int port) throws IOException{
		com = new Communication(ip, port);
		working = true;
		body = new byte[64000];
		header = new String[2500];
	}
	
	@Override
	public void run(){
		byte[] msg;
		
		while(working){
			msg = com.receive();
			
			if(msg != null)
				receiving(msg);
		}
	}
	
	private void receiving(byte[] msg) {
		decrypte(msg);
		
		if(header[0] == "GETCHUNK"){
			if(header[1] == Main.getVersion()){
				getChunkProcess();
			}
		}else if(header[0] == "STORED"){
			if(header[1] == Main.getVersion()){
				storedProcess();
			}
		}else if(header[0] == "REMOVED"){
			if(header[1] == Main.getVersion()){
				removedProcess();
			}
		}else if(header[0] == "DELETE"){
			if(header[1] == Main.getVersion()){
				deleteProcess();
			}
		}else{
			System.out.println("Wrong message!");
		}
		
	}
	
	private void getChunkProcess() {
		// TODO Auto-generated method stub
		
	}

	private void storedProcess() {
		// TODO Auto-generated method stub
		
	}

	private void removedProcess() {
		// TODO Auto-generated method stub
		
	}

	private void deleteProcess() {
		// TODO Auto-generated method stub
		
	}

	private void decrypte(byte[] msg) {
		//separate header information considering one or more spaces betweeen words
		String dec = msg.toString();
		String[] tmp = dec.split("\\s+");
		header = new String[tmp.length];
	
		int headerLength = 0;
		for(int i=0; i<tmp.length; i++){//remove any whitespace before and after each string
			header[i] = tmp[i].trim();
			headerLength += tmp[i].length();
		}
		
		//separate body
		body = new byte[dec.length() - headerLength + 4]; // +4 because of the two CRLF in the end of the header
		
		int bodyIndex = 0;
		for(int i = headerLength + 4; i<dec.length(); i++){
			body[bodyIndex] = msg[i];
		}	
	}

}
