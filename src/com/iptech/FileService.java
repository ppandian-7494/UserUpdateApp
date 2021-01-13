package com.iptech;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class FileService  {
	public static User[] users = new User[20];
	public void populateUserArray() {
		String fileName = "users.txt";
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = null;
			int i = 0;
			while((line = reader.readLine()) != null) {
				String[] values = line.split(", ");
				if("super_user".equals(values[3])) {
					users[i++] = new SuperUser(values[0], values[1], values[2]);
				} else {
					users[i++] = new NormalUser(values[0], values[1], values[2]);
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File Not Found!");
			ex.printStackTrace();
			System.exit(0);
		} catch (IOException ex) {
			System.out.println("Something Went Wrong");
			ex.printStackTrace();
			System.exit(0);
		} finally {
			try {
				if (reader != null) reader.close();
			} catch (IOException ex) {
				System.out.println("Could not close file reader");
				ex.printStackTrace();
				System.exit(0);
			}
			
		}
	}
	
	public void exportUserArrayToFile() {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("users_output.txt"));
			Arrays.sort(users);
			for (User user : users) {
				writer.write(UserService.getCsvOutput(user));
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File Not Found!");
			ex.printStackTrace();
			System.exit(0);
		} catch (IOException ex) {
			System.out.println("Something Went Wrong");
			ex.printStackTrace();
			System.exit(0);
		} finally {
			try {
				if (writer != null) writer.close();
			} catch (IOException ex) {
				System.out.println("Could not close file writer");
				ex.printStackTrace();
				System.exit(0);
			}
		}
	}
}
