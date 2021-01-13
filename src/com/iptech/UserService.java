package com.iptech;

public class UserService {
	public User getValidUser(String username, String password) {
		for (User user : FileService.users) {
			if (user.getUsername().equalsIgnoreCase(username) &&
					user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
	
	public User getUserByUsername(String username) {
		for(User user : FileService.users) {
			if(user.getUsername().equalsIgnoreCase(username)) {
				return user;
			}
		}
		return null;
	}
	
	public static String getCsvOutput(User user) {
		return user.getUsername() + ", " + user.getPassword() + ", " + user.getName() + ", " + user.getRole() + "\n";
	}
}
