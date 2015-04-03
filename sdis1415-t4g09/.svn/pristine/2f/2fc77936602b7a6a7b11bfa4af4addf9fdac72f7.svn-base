package persistence;

import java.util.Hashtable;
import java.util.Vector;

public class Persistence {
	
	private Hashtable<String, String> persistence; //Hashtable<id, filename>
	private Vector<Chunk> chunks;

	public Persistence() {
		persistence = new Hashtable<String, String>();
	}
	
	public Persistence(Hashtable<String, String> persistence, Vector<Chunk> chunks) {
		this.persistence = persistence;
		this.chunks = chunks;
	}
	
	public void addFile(String fID, String name){
		persistence.put(fID, name);
	}
	
	public void deleteFile(String fID){
		persistence.remove(fID);
	}
	
	public void addChunksVector(Vector<Chunk> newChunks){
		for(int i = 0; i<newChunks.size(); i++){
			this.chunks.addElement(newChunks.elementAt(i));
		}
	}

	public Hashtable<String, String> getPersistence() {
		return persistence;
	}

	public void setPersistence(Hashtable<String, String> persistence) {
		this.persistence = persistence;
	}

	public Vector<Chunk> getChunks() {
		return chunks;
	}

	public void setChunks(Vector<Chunk> chunks) {
		this.chunks = chunks;
	}

}
