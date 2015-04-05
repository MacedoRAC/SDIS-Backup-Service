package protocols;

import java.nio.charset.StandardCharsets;

import main.Communication;
import main.Main;

public class Delete extends Thread{
	private static Communication com;

	public Delete() {
		String sendMsg = "DELETE " + header[2] + " " + header[3] + Main.getCRLF().toString() + Main.getCRLF().toString();

        

        com.send(sendMsg.getBytes(StandardCharsets.ISO_8859_1));
	}

}
