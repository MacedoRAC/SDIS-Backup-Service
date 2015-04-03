package protocols;

import java.io.IOException;

import main.Communication;

public class Backup extends Thread{

	private static Communication com;
	private boolean working;

	public Backup() throws IOException {
		this.com = new Communication("", 0);
		this.working = true;
	}
	
	public static Communication getCom() {
		return com;
	}

	public static void setCom(Communication com) {
		Backup.com = com;
	}

	public boolean isWorking() {
		return working;
	}

	public void setWorking(boolean working) {
		this.working = working;
	}

}
