package persistence;

public class Chunk {
	
	private int replDegree;
	private String fileID;
	private int number;
	private String ip;
	private int chunkNo;
	
	
	public Chunk(String fID, int n, int rD) {
		this.replDegree = rD;
		this.fileID = fID;
		this.number = n;
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


	public int getChunkNo() {
		return chunkNo;
	}


	public void setChunkNo(int chunkNo) {
		this.chunkNo = chunkNo;
	}

}
