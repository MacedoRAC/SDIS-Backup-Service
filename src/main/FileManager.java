package main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class FileManager {

	private static File file;
	private static int chunkSize;
	private static BufferedInputStream input;
	private static String filename;
	private static StringBuffer hashFilename;
	private static int repDegree;

	public FileManager(String file2, int repDeg) {
		FileManager.filename = file2;
		FileManager.chunkSize = 64000;
		/*
		 * FileManager.file = new File(filename); try { FileManager.input = new
		 * BufferedInputStream(new FileInputStream(FileManager.file)); } catch
		 * (FileNotFoundException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		FileManager.repDegree = repDeg;

		hashName();
	}

	private void hashName() {
		hashFilename = new StringBuffer(filename);

		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		String metadata = filename + file.lastModified();
		byte[] hash = null;

		try {
			assert digest != null;
			hash = digest.digest(metadata.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// convert byte[] to hex
		for (byte a : hash) {
			hashFilename.append(Integer.toString((a & 0xff) + 0x100, 16)
					.substring(1));
		}

	}

	public static void splitFile(File f) throws FileNotFoundException,
			IOException {
		int partCounter = 1;// give names to chunks. change this to unique id

		byte[] buf = new byte[chunkSize];
		File newFile = new File("");

		int tmp = 0;
		while ((tmp = input.read(buf)) > 0) {
			// write each chunk of data into separate file
			newFile = new File(file.getParent(), filename + "."
					+ String.format("%03d", partCounter++));
			try (FileOutputStream out = new FileOutputStream(newFile)) {
				out.write(buf, 0, tmp);// tmp is chunk final size
			}
		}

		if (newFile.length() == chunkSize) {
			newFile = new File(file.getParent(), filename + "."
					+ String.format("%03d", partCounter++));
			try (FileOutputStream out = new FileOutputStream(newFile)) {
				out.write(new byte[0], 0, 0);// one last chunk with size 0 to
												// end file
			}
		}
	}

	public static void mergeFiles(List<File> chunks, File merged)
			throws IOException {

		BufferedOutputStream mergingStream = new BufferedOutputStream(
				new FileOutputStream(merged));

		for (File f : chunks) {
			Files.copy(f.toPath(), mergingStream);
		}
	}

	public static int getRepDegree() {
		return repDegree;
	}

	public static void setRepDegree(int repDegree) {
		FileManager.repDegree = repDegree;
	}

	public static File getFile() {
		return file;
	}

	public static void setFile(File file) {
		FileManager.file = file;
	}

	public static int getChunkSize() {
		return chunkSize;
	}

	public static void setChunkSize(int chunkSize) {
		FileManager.chunkSize = chunkSize;
	}

	public static BufferedInputStream getInput() {
		return input;
	}

	public static void setInput(BufferedInputStream input) {
		FileManager.input = input;
	}

	public static String getFilename() {
		return filename;
	}

	public static void setFilename(String filename) {
		FileManager.filename = filename;
	}

	public static StringBuffer getHashFilename() {
		return hashFilename;
	}

	public static void setHashFilename(StringBuffer hashFilename) {
		FileManager.hashFilename = hashFilename;
	}

	public void writeToFile(int parseInt, byte[] body) {
		File dir = new File(hashFilename.toString());

		if (!dir.exists()) {
			dir.mkdir();
		}

		File newFile;

		if (parseInt >= 0) {
			newFile = new File(hashFilename.toString() + "/" + parseInt + ".part");
		} else {
			newFile = new File(Main.getDatabase().getPersistence().toString());
		}

		if (!newFile.exists()) {
			try {
				newFile.createNewFile();
			} catch (IOException e) {
				System.out.println("Error creating file!");
				e.printStackTrace();
			}
		}

		/*
		try {
			out = new BufferedOutputStream(new FileOutputStream(newFile, true));
		} catch (FileNotFoundException e) {
			System.out.println("Error creating output stream");
			e.printStackTrace();
		}

		try {
			out.write(data);
		} catch (IOException e) {
			System.out.println("Error writing chunk to file");
			e.printStackTrace();
		}

		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

}
