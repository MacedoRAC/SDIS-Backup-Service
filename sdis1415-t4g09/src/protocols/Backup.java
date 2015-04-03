package protocols;

import java.io.IOException;

import persistence.Chunk;
import main.Communication;
import main.FileManager;
import main.Main;

public class Backup extends Thread{

	private static Communication com;
	private boolean working;
	private String[] header;
	private byte[] body;
	private BackupSend[] sendingArray;

	public Backup(String ip, int port) throws IOException {
		Backup.com = new Communication(ip, port);
		this.working = true;
	}
	
	@Override
	public void start(){
		byte[] msg;
		
		while(working){
			msg = com.receive();
			
			if(msg != null)
				receiving(msg);
		}
	}
	
	private void receiving(byte[] msg) {
		decrypte(msg);
		
		if(header[0] == "PUTCHUNK"){
			if(header[1] == Main.getVersion()){
				backupProcess();
			}
		}else{
			System.out.println("Wrong message: expected PUTCHUNK, but " + header[0] + " received.");
		}
		
	}

	private void backupProcess() {

		for(int i = 0; i < sendingArray.length; i++){
			if(sendingArray[i].getHash() == header[2] && sendingArray[i].getChunkNo() == Integer.parseInt(header[3])){
				sendingArray[i].setToSend(true);
				break;
			}
		}
		
		//Analyze if there is enough space to store chunks
		if(Main.getDatabase().freeSpace() < body.length){
			System.out.println("Not ennough space to save a new chunk!");
			return;
		}
		
		//verify if the chunk already exists
		boolean chunkExists = false;
		
		for(int i = 0; i < Main.getDatabase().getChunks().size(); i++){
			if(Main.getDatabase().getChunks().get(i).getFileID() == header[2]){
				if(Main.getDatabase().getChunks().get(i).getChunkNo() == Integer.parseInt(header[3])){
					chunkExists = true;
					break;
				}
			}
		}
		
		//if not exists create a new chunk and send STORED message
		if(!chunkExists){
			Chunk newChunk;
			
		}
		
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

	public Communication getCom() {
		return com;
	}

	public void setCom(Communication com) {
		Backup.com = com;
	}

	public boolean isWorking() {
		return working;
	}

	public void setWorking(boolean working) {
		this.working = working;
	}
	
	
	public class BackupSend extends Thread{
		
		private FileManager fileMan;
		private int repDegree;
		private int chunkNo;
		private int stored;
		private int tries;
		private int time;
		private String hash;
		private boolean file;
		private boolean toSend;
		
		
		public BackupSend(String file, int chunkN, int repD){
			
			this.fileMan = new FileManager(file);
			
			this.time = 400;
			this.tries = 0;
			this.chunkNo = chunkN;
			this.repDegree = repD;
			this.stored = 0;
			this.toSend = false;
			this.file = false;
			this.hash = fileMan.getHashFilename().toString();
		}
		
		public FileManager getFileMan() {
			return fileMan;
		}
		public void setFileMan(FileManager fileMan) {
			this.fileMan = fileMan;
		}
		public int getRepDegree() {
			return repDegree;
		}
		public void setRepDegree(int repDegree) {
			this.repDegree = repDegree;
		}
		public int getChunkNo() {
			return chunkNo;
		}
		public void setChunkNo(int chunkNo) {
			this.chunkNo = chunkNo;
		}
		public int getStored() {
			return stored;
		}
		public void setStored(int stored) {
			this.stored = stored;
		}
		public int getTries() {
			return tries;
		}
		public void setTries(int tries) {
			this.tries = tries;
		}
		public int getTime() {
			return time;
		}
		public void setTime(int time) {
			this.time = time;
		}
		public String getHash() {
			return hash;
		}
		public void setHash(String hash) {
			this.hash = hash;
		}
		public boolean isFile() {
			return file;
		}
		public void setFile(boolean file) {
			this.file = file;
		}
		public boolean isToSend() {
			return toSend;
		}
		public void setToSend(boolean toSend) {
			this.toSend = toSend;
		}
		
	}

}
