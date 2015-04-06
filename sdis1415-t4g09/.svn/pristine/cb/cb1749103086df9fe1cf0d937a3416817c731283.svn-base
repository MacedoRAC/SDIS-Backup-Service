package protocols;

import java.nio.charset.StandardCharsets;

import main.Communication;
import main.Main;

public class Delete extends Thread{
	private static Communication com;

	public Delete(String fileID) {
		String sendMsg = "DELETE " + fileID + Main.getCRLF().toString() + Main.getCRLF().toString();
        com.send(sendMsg.getBytes(StandardCharsets.ISO_8859_1));
        
        System.out.println("Delete message sent!");
	}

}
