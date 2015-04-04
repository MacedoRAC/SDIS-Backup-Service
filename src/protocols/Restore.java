package protocols;

import java.io.IOException;

import persistence.Chunk;
import main.Communication;
import main.FileManager;
import main.Main;

public class Restore extends Thread {
	private static Communication com;
	private static boolean working;
	private String[] header;
	private byte[] msg;

	public Restore() throws IOException {
		this.com = new Communication("", 0);
		this.working = true;
	}
	
	
	public void start(){
		byte[] msg;
		
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
			FileManager fileMan =  new FileManager(header[2]);
			fileMan.writeToFile(Integer.parseInt(header[3]), msg);
			Chunk chunk = new Chunk(replDegree, header[2], Integer.parseInt(header[3]), "sad" /*VER O IP*/ );
			Main.getDatabase().getChunks().add(chunk);
			//Main.getRestore().
		}
	}
	
	
	
}
