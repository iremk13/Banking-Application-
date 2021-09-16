package newpackage;

import java.util.HashMap;
import java.util.Scanner;

public class Login {

	private static final int MAX_LOGIN_TRIAL_COUNT = 3;

	public static void login() {

		Scanner scanner = MainPanel.scanner;
		User loggedOnUser = MainPanel.loggedOnUser; //giriş yapan user tutuluyor
		HashMap<Integer, User> userMap = MainPanel.userMap;

		System.out.print("\nEnter customer number: ");
		int customerNumber = Integer.parseInt(scanner.nextLine());

		if (userMap.containsKey(customerNumber)) { //customer number varsa user vardır
			for (int i = 0; i < MAX_LOGIN_TRIAL_COUNT; i++) {
				System.out.print("\nEnter password: ");
				String password = scanner.nextLine();

				if (userMap.get(customerNumber).getPassword().equals(password)) {
					loggedOnUser = userMap.get(customerNumber); //userı buraya atadık
					System.out.println("\nSuccesfully logged on. Welcome " + loggedOnUser.getName() + " "
							+ loggedOnUser.getSurName() + "\n");
					MainPanel.loggedOnUser = loggedOnUser;
					UserOperations.userOperations();
					break;

				} else {
					System.out.println("\nWrong password!!!\nPlease try again...");
				}
			}

		} else {
			System.out.println("\nUser not defined. Please register first!\n");
		}
	}

}
