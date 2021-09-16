package newpackage;

import java.util.HashMap;
import java.util.Scanner;

public class MainPanel {

	static String operation;

	static User loggedOnUser;

	static Scanner scanner;

	static HashMap<Integer, User> userMap;

	public static void main(String[] args) {

		operation = "";
		scanner = new Scanner(System.in);
		userMap = new HashMap<Integer, User>();

		while (!operation.equals("e")) {
                                                      System.out.println("***************************************************************");
			System.out.println("Welcome to the Online banking application,please choose the operation:");
			System.out.println("\n1.Login\n2.Register\ne.Exit\n");
                                                      System.out.println("***************************************************************");
			operation = scanner.nextLine();

			switch (operation) {
			case "1":
				Login.login();
				break;
			case "2":
				Register.register();
				break;
			case "e":
				System.out.println("\nProgram closed succesfully...");
				break;
			default:
				System.out.println("\nError Occured!!!\nYou have typed wrong command.");
				System.out.println("\n*****************************************************************\n");
				break;
			}

		}

		scanner.close();

	}

}
