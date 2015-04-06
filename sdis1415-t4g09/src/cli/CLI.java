package cli;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Scanner;

import protocols.Backup;
import protocols.Backup.BackupSend;
import protocols.Control;
import protocols.Restore;
import protocols.SpaceReclaiming;
import main.Main;

public class CLI {

	public CLI() throws IOException {
		configMenu();
	}

	private void configMenu() throws IOException {

		clearConsole();
		
		serviceTitle();
		
		//backup config
		System.out.println("\nConfigure for each protocol an IP and a control Port:\n"
				+ "Backup IP: ");
		Scanner in = new Scanner(System.in);
		String backupIP = in.nextLine();
		System.out.println("Backup Port: ");
		int backupPort = in.nextInt();
		
		Main.setBackup(new Backup(backupIP, backupPort));
		Main.getBackup().start();
		
		//restore config
		Scanner in2 = new Scanner(System.in);
		System.out.println("Restore IP: ");
		String restoreIP = in2.nextLine();
		System.out.println("Restore Port: ");
		int restorePort = in2.nextInt();
		
		Main.setRestore(new Restore(restoreIP, restorePort));
		
		//control config
		Scanner in3 = new Scanner(System.in);
		System.out.println("Control IP: ");
		String controlIP = in3.nextLine();
		System.out.println("Control Port: ");
		int controlPort = in3.nextInt();

		Main.setControl(new Control(controlIP, controlPort));
		Main.getControl().start();
		
		//open main menu
		menu();
	}

	@SuppressWarnings("static-access")
	public void menu() throws IOException {
		int option;
		Scanner in = new Scanner(System.in);
		clearConsole();
		
		serviceTitle();
		
		System.out.println("\n\nWhat do you want to do?");
		System.out.println("1. Backup File");
		System.out.println("2. Restore File");
		System.out.println("3. Delete File");
		System.out.println("4. Space Reclaiming");
		System.out.println("5. Exit");
		System.out.println("\nChoose an option: ");
		
		option = in.nextInt();
		
		switch(option){
		case 1:
			backupMenu();
			break;
		case 2:
			restoreMenu();
			break;
		case 3:
			deleteMenu();
			break;
		case 4:
			spaceRecMenu();
			break;
		case 5:
			Main.getBackup().setWorking(false);
            Main.getRestore().setWorking(false);
            Main.getBackup().getCom().getSocket().close();
            Main.getRestore().getCom().getSocket().close();
			return;
		}
	}

	public void clearConsole() throws IOException {
		//method found in stackOverflow
		String os = System.getProperty("os.name");

        if (os.contains("Windows"))
        {
            Runtime.getRuntime().exec("cls");
        }
        else
        {
            Runtime.getRuntime().exec("clear");
        }
		
	}

	public void spaceRecMenu() throws IOException {
		clearConsole();

		serviceTitle();
		System.out.println("***    Space Reclaiming ***");
		System.out.println("***************************");

		System.out.println("\n\nHow much space do you want to reclaim? To go back write 'back'!");

		Scanner in = new Scanner(System.in);
		String space = in.nextLine();

		if(space == "back") //go back to main menu
			menu();
		else{
			System.out.println("Space reclaiming started...");
			Main.setSpaceRec(new SpaceReclaiming(Integer.parseInt(space))); 
		}
	}

	public void deleteMenu() throws IOException {
		clearConsole();
		
		serviceTitle();
		System.out.println("***    Delete ***");
		System.out.println("*****************");
		
		System.out.println("\n\nWrite the filename! To go back write 'back'!");
		
		Scanner in = new Scanner(System.in);
		String path = in.nextLine();
		
		if(path == "back") //go back to main menu
			menu();
		else{
			// call delete method to file selected
		}
		
	}

	public void restoreMenu() throws IOException {
		clearConsole();
		
		serviceTitle();
		System.out.println("***    Restore ***");
		System.out.println("*******************");
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("\n\nWrite the file you want to restore by choosing the number in the left of each entry! To go back write 'back'!");
		
		String file = in.nextLine();
		
		if(file == "back") //go back to main menu
			menu();
		else{
			//show all hashtable entries
			int i=1;
			for(Entry<String, String> entry: Main.getDatabase().getPersistence().entrySet()){
				System.out.println(i + ". " + " " + entry.getValue() + entry.getKey());
				
				i++;
			}
			//choose one entry
			i = 1;
			String fileID = null;
			for(Entry<String, String> entry: Main.getDatabase().getPersistence().entrySet()){
				if(i == Integer.parseInt(file))
					fileID = entry.getKey();
				
				i++;
			}
			
			if(fileID == null){
				System.err.println("File not found in database. Please insert an existing ID.");
			}else{
				System.out.println("Restore initialized...");
				Main.getRestore().start();
			}
		}
		
	}

	public void backupMenu() throws IOException {
		clearConsole();
		
		serviceTitle();
		System.out.println("***    Backup ***");
		System.out.println("*****************");
		
		System.out.println("\n\nWrite the file path! To go back write 'back'!");
		
		Scanner in2 = new Scanner(System.in);
		String path = in2.nextLine();
		
		if(path == "back") //go back to main menu
			menu();
		else{
			if(new File(path).exists()){
				System.out.println("Replication degree: ");
				String repDeg = System.console().readLine();
				
				if(Integer.parseInt(repDeg) >= 1){
					BackupSend s = new BackupSend(path, 0, Integer.parseInt(repDeg));
					Main.getBackup().getSendingArray().addElement(s);
					System.out.println("Backup initialized...");
					s.start();
				}
			}else{
				System.err.println("File not found. Please verify the file path");
			}
		}
	}

	public void serviceTitle() {
		System.out.println("*********************************");
		System.out.println("***    BACKUP FILE SERVICE    ***");
		System.out.println("*********************************");
	}

}
