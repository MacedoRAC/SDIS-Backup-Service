package protocols;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

import persistence.Chunk;
import main.Communication;
import main.FileManager;
import main.Main;

public class Restore extends Thread {
	private static Communication com;
	private static boolean working;
	private String[] header;
	private byte[] msg;
	private byte[] body;

	public Restore() throws IOException {
		this.com = new Communication("", 0);
		this.working = true;
	}
	
	
	public void start(){		
		while(working){
			msg = com.receive();
			
			if(msg != null)
				receiving(msg);
		}
	}
	
	public void receiving(byte[] msg){
		if(header[0] == "GETCHUNK"){
			if(header[1] == Main.getVersion())
				restoreProcess();
		}else{
			System.out.println("Wrong message: expected GETCHUNK, but " + header[0] + " received.");
		}
	}
	
	public void restoreProcess(){
		boolean ChunkExists = false;
		int replDegree = 0;

		for(int i = 0; i < Main.getDatabase().getChunks().size(); i++){
			if(Main.getDatabase().getChunks().get(i).getFileID() == header[2]){
				if(Main.getDatabase().getChunks().get(i).getChunkNo() == Integer.parseInt(header[3])){
					ChunkExists = true;
					replDegree = Main.getDatabase().getChunks().get(i).getReplDegree();
				}
			}
		}
		
		if(!ChunkExists){
			decrypte(msg);
			FileManager fileMan =  new FileManager(header[2], replDegree);
			fileMan.writeToFile(Integer.parseInt(header[3]), body);
			Chunk chunk = new Chunk(header[2], Integer.parseInt(header[3]), replDegree);
			Main.getDatabase().getChunks().add(chunk);
			Main.getRestore().setWaitingConfirm(body.length);
		}
	}
	
	
	
	private void setWaitingConfirm(int length) {
		int currentChunk = 0;
		FileManager fMan;
		boolean waitingconf = false;
		String sendMsg = "";
		List<Chunk> ChunkList;

        
		while(true){
			currentChunk++;
			
			sendMsg = "GETCHUNK " + Main.getVersion() + " " + header[2] + " " + header[3] + Main.getCRLF().toString() + Main.getCRLF().toString();

	        

	        com.send(sendMsg.getBytes(StandardCharsets.ISO_8859_1));
			
			Random r = new Random();
	        int time = r.nextInt(401);
	        try {
	            sleep(time);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        fMan = new FileManager(header[2], 0);
	        if(fMan.getChunkSize() < 64000){
                break;
            }
			
		}
		
		fMan = new FileManager(header[2], 0);
		/*fMan.mergeFiles(ChunkList, );*/
		
		System.out.println("Chunck restore completed");
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
