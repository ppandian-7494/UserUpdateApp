package com.iptech;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class UpdateService {
	static Scanner scanner = new Scanner(System.in);
	public static void promptUpdateUserName(User loggedInUser) {
		System.out.println("Please type in your new username: ");
		String username = scanner.nextLine();
		loggedInUser.setUsername(username);
	}

	
	public static String promptUsernameToLoginAs() {
		System.out.println("Which user would you like to login as? (Type in a valid username");
		String usernameToLoginAs = scanner.nextLine();
		return usernameToLoginAs;
	}

	public static void promptUpdatePassword(User loggedInUser) {
		System.out.println("Please type in your new password : ");
		String password = scanner.nextLine();
		loggedInUser.setPassword(password);
	}

	public static void promptUpdateName(User loggedInUser) {
		System.out.println("Please type in your new name : ");
		String name = scanner.nextLine();
		loggedInUser.setName(name);
	}

	public static int promptOptions(User loggedInUser) {
		System.out.println("--------");
		System.out.println("Please choose from the following options:");
		if(loggedInUser instanceof SuperUser) {
			System.out.println("(0) Log in as another user ");
		}
		System.out.println("(1) Update username");
		System.out.println("(2) Update password");
		System.out.println("(3) Update name");
		System.out.println("(4) exit");
		String option = scanner.nextLine();
		try {
			Integer.parseInt(option);
		} catch (NumberFormatException ex) {
			System.out.println("Error in converting string to Integer.");
			System.exit(0);
		}
		return Integer.parseInt(option);
		
	}
}
