package newpackage;

import java.util.HashMap;
import java.util.Scanner;

public class Register {

	public static void register() {
		
		Scanner scanner = MainPanel.scanner;
		HashMap<Integer, User> userMap = MainPanel.userMap;

		try {
			System.out.println("\nFor registration please follow the steps and enter user information.\n");

			System.out.print("User's name: ");
			String userName = scanner.nextLine();

			System.out.print("\nUser's Surname: ");
			String userSurname = scanner.nextLine();

			System.out.print("\nCustomer Number: ");
			int customerNumber = Integer.parseInt(scanner.nextLine());

			System.out.print("\nPassword: ");
			String password = scanner.nextLine();

			System.out.print("\nE-mail Address: ");
			String emailAddress = scanner.nextLine();

			System.out.print("\nPhone Number: ");
			String phoneNumber = scanner.nextLine();

			if (!userMap.containsKey(customerNumber)) {
				userMap.put(customerNumber,
						new User(userName, userSurname, customerNumber, emailAddress, password, phoneNumber));
				MainPanel.userMap = userMap; //?
				System.out.println("\nSucessfully Registered. Please Login...");
				System.out.println("\n-----------------------------------------------------------------\n");
				return;
			} else {
				System.out.println("\nError Occured!!! Customer number already owned!!!\nRegistration Failed.");
				System.out.println("\n*****************************************************************\n");
				return;
			}
		} catch (NumberFormatException e) {
			System.out.println("\nError Occured!!! Customer number must be a NUMBER!!!\nRegistration Failed.");
			System.out.println("\n*****************************************************************\n");
			return;
		}

	}
	
}
