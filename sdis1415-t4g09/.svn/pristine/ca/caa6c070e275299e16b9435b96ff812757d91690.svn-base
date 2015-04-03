package main;

import java.io.IOException;
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
	private static String version;
	private static int diskSpace = 1000000;

	public static void main(String[] args) throws IOException {
		//initialize database
		//if a config file is loaded call another method
		database = new Persistence();
		version = "1.0";
		
		//start protocols
		backup.start();
		restore.start();
		delete.start();
		spaceRec.start();
		
		
	}

	public static CLI getCli() {
		return cli;
	}

	public static void setCli(CLI cli) {
		Main.cli = cli;
	}

	public static Restore getRestore() {
		return restore;
	}

	public static void setRestore(Restore restore) {
		Main.restore = restore;
	}

	public static Backup getBackup() {
		return backup;
	}

	public static void setBackup(Backup backup) {
		Main.backup = backup;
	}

	public static Delete getDelete() {
		return delete;
	}

	public static void setDelete(Delete delete) {
		Main.delete = delete;
	}

	public static SpaceReclaiming getSpaceRec() {
		return spaceRec;
	}

	public static void setSpaceRec(SpaceReclaiming spaceRec) {
		Main.spaceRec = spaceRec;
	}

	public static String getCRLF() {
		return CRLF;
	}

	public static void setCRLF(String cRLF) {
		CRLF = cRLF;
	}

	public static int getChunkSize() {
		return chunkSize;
	}

	public static void setChunkSize(int chunkSize) {
		Main.chunkSize = chunkSize;
	}

	public static Persistence getDatabase() {
		return database;
	}

	public static void setDatabase(Persistence database) {
		Main.database = database;
	}

	public static String getVersion() {
		return version;
	}

	public static void setVersion(String version) {
		Main.version = version;
	}

	public static int getDiskSpace() {
		return diskSpace;
	}

	public static void setDiskSpace(int diskSpace) {
		Main.diskSpace = diskSpace;
	}
}
