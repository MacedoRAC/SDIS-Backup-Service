package main;

import java.io.IOException;
import java.util.Hashtable;

import protocols.Backup;
import protocols.Delete;
import protocols.Restore;
import protocols.SpaceReclaiming;
import cli.CLI;

public class Main {
	private static CLI cli;
	private	static Restore restore;
	private static Backup backup;
	private static Delete delete;
	private static SpaceReclaiming spaceRec;
	private static String CRLF = "\r\n";
	private static int chunkSize = 64000;
	private static Hashtable<String, Integer> database = new Hashtable<String, Integer>(); 

	public static void main(String[] args) throws IOException {
		//populate database with id's and port
		database.put("224.0.0.1", 4000);
		database.put("224.0.0.2", 4000);
		database.put("224.0.0.3", 4000);
		database.put("224.0.0.4", 4000);
		database.put("224.0.0.5", 4000);
		database.put("224.0.0.6", 4000);
		
		
		//start protocols
		backup.start();
		restore.start();
		delete.start();
		spaceRec.start();
		
		
	}

}
