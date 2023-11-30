package it.sincrono.services.utils;

import java.security.SecureRandom;

public class TokenGenerator {

	public static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static final int SECURE_TOKEN_LENGTH = 60;

	private static final SecureRandom random = new SecureRandom();

	private static final char[] symbols = CHARACTERS.toCharArray();
	private static final char[] buffer = new char[SECURE_TOKEN_LENGTH];

	public static String nextToken() {

		for (int i = 0; i < buffer.length; ++i) {
			buffer[i] = symbols[random.nextInt(symbols.length)];
		}

		return new String(buffer);
	}

}
