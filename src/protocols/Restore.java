package protocols;

import java.io.IOException;

import main.Communication;

public class Restore extends Thread {
	private static Communication com;
	private static boolean working;

	public Restore() throws IOException {
		this.com = new Communication("", 0);
		this.working = true;
	}
	
	
}
