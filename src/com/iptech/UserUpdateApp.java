package com.iptech;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class UserUpdateApp {
	
	
	public static void main(String[] args)  {
		UserService userService = new UserService();
		FileService fileService = new FileService();
		fileService.populateUserArray();

		Scanner scanner = new Scanner(System.in);
		User loggedInUser = null;
		int numberOfLoginAttempts = 0;
		while (loggedInUser == null && numberOfLoginAttempts < 5) {	
			System.out.println("Enter your email:");
			String username = scanner.nextLine();
			System.out.println("Enter your password: ");
			String password = scanner.nextLine();
			
			loggedInUser = userService.getValidUser(username, password);
			if (loggedInUser == null) {
				numberOfLoginAttempts++;
				System.out.println("Invalid login, please try again");
				if (numberOfLoginAttempts == 5) {
					System.out.println("Too many failed login attempts, you are now locked out.");
				}
			} else {
				int option = 0;
				while(option != 4) {
					System.out.println("Welcome: " + loggedInUser.getName());
					option = UpdateService.promptOptions(loggedInUser);
					if(option == 0 && "super_user".equals(loggedInUser.getRole())) {
						String usernameToUpdate = UpdateService.promptUsernameToLoginAs();
						User userToLoginAs = userService.getUserByUsername(usernameToUpdate);
						if(userToLoginAs == null) {
							System.out.println("Invalid username.");
						} else {
							loggedInUser = userToLoginAs;
						}	
					} else if(option == 1) {
						UpdateService.promptUpdateUserName(loggedInUser);
					} else if(option == 2) {
						UpdateService.promptUpdatePassword(loggedInUser);
					} else if(option == 3) {
						UpdateService.promptUpdateName(loggedInUser);
					} else if (option != 4) {
						System.out.println("Invalid input, try again");
					}
				}
				fileService.exportUserArrayToFile();
		}
		scanner.close();
		
		}
	}
}
