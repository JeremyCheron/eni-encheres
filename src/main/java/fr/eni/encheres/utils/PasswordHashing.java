package fr.eni.encheres.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordHashing {

	public static String hashPassword(String plainPassword) {

		char[] passwordChars = plainPassword.toCharArray();
        String hashedPassword = BCrypt.withDefaults().hashToString(12, passwordChars);

        return hashedPassword;
	
	}
	
	public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(plainPassword.toCharArray(), hashedPassword);
        return result.verified;
	}
	
	
	
}
