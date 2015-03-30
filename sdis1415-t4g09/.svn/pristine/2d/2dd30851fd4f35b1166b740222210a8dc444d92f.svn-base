package cli;

import java.io.IOException;

public class CLI {

	public CLI() throws IOException {
		menu();
	}

	public void menu() throws IOException {
		int option;
		
		clearConsole();
		
		serviceTitle();
		
		System.out.println("\n\nWhat do you want to do?");
		System.out.println("1. Backup File");
		System.out.println("2. Restore File");
		System.out.println("3. Delete File");
		System.out.println("4. Space Reclaiming");
		System.out.println("5. Exit");
		System.out.println("\nChoose an option: ");
		
		option = Integer.parseInt(System.console().readLine());
		
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

	public void spaceRecMenu() {
		// TODO Auto-generated method stub
		
	}

	public void deleteMenu() throws IOException {
		clearConsole();
		
		serviceTitle();
		System.out.println("***    Delete ***");
		System.out.println("*******************");
		
		System.out.println("\n\nWrite the filename! To go back write 'back'!");
		
		String path = System.console().readLine();
		
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
		
		System.out.println("\n\nWrite the filename! To go back write 'back'!");
		
		String path = System.console().readLine();
		
		if(path == "back") //go back to main menu
			menu();
		else{
			// call restore method to file selected
		}
		
	}

	public void backupMenu() throws IOException {
		clearConsole();
		
		serviceTitle();
		System.out.println("***    Backup ***");
		System.out.println("*******************");
		
		System.out.println("\n\nWrite the file path! To go back write 'back'!");
		
		String path = System.console().readLine();
		
		if(path == "back") //go back to main menu
			menu();
		else{
			// call backup method to file selected
		}
	}

	public void serviceTitle() {
		System.out.println("*********************************");
		System.out.println("***    BACKUP FILE SERVICE    ***");
		System.out.println("*********************************");
	}

}
