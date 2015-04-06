package protocols;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import persistence.Chunk;
import main.Communication;
import main.FileManager;
import main.Main;

public class SpaceReclaiming extends Thread {
	private static Communication com;
	private Chunk[] chunks;

	public SpaceReclaiming(Integer space) {
		int space_balance = space - Main.getDatabase().freeSpace();
		if(space_balance > 0){
			
			int n_del = (int) Math.ceil(space_balance / Main.getChunkSize());
			
            chunks = new Chunk[n_del];

            int counter = 0;
            while(counter < n_del){
            	chunks[counter] = Main.getDatabase().getChunks().get(Main.getChunkSize()-counter-1);
            	counter++;
            }
            
            counter = 0;
            while(counter < n_del){
            	FileManager del = new FileManager(chunks[counter].getFileID(), 0);
            	del.delete(chunks[counter].getChunkNo());
            	
            	String message = "REMOVED " + Main.getVersion() + " " + chunks[counter].getFileID() + " " + chunks[counter].getChunkNo() + Main.getCRLF().toString() + Main.getCRLF().toString();
        		com.send(message.getBytes(StandardCharsets.ISO_8859_1));
        		
            	Random r = new Random();
    	        int time = r.nextInt(401);
    	        try {
    	            sleep(time);
    	        } catch (InterruptedException e) {
    	            e.printStackTrace();
    	        }
            	counter++;
            }			
		}
		
		
		Main.setDiskSpace(Main.getDiskSpace() - space);
        System.out.println("You now have" + Main.getDiskSpace() + "in disk");
	}

}
