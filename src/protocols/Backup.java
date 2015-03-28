package protocols;

import java.io.IOException;

import main.Communication;

public class Backup {
	private static Communication com;
	private boolean working;

	public Backup() throws IOException {
		this.com = new Communication("", 0);
		this.working = true;
	}

	public void start() {

		while(working){
			
		}
		
	}

}
