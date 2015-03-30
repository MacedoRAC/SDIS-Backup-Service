package persistence;

public class Chunk {
	
	private int replDegree;
	private String fileID;
	private int number;
	private String ip;
	
	
	public Chunk(int rD, String fID, int n, String ip) {
		this.replDegree = rD;
		this.fileID = fID;
		this.number = n;
		this.ip = ip;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}


	public int getReplDegree() {
		return replDegree;
	}


	public void setReplDegree(int replDegree) {
		this.replDegree = replDegree;
	}


	public String getFileID() {
		return fileID;
	}


	public void setFileID(String fileID) {
		this.fileID = fileID;
	}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}

}
