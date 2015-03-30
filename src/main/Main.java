package main;

import java.io.IOException;
import java.util.Hashtable;

import persistence.Persistence;
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
	private static Persistence database;

	public static void main(String[] args) throws IOException {
		//initialize database
		//if a config file is loaded call another method
		database = new Persistence();
		
		//start protocols
		backup.start();
		restore.start();
		delete.start();
		spaceRec.start();
		
		
	}

}
