package com.tecnolpet.ot.utils;

public class ClavesUtils {

	public static String generarClaves(String key, int length){
		String pswd = "";
		for (int i = 0; i < length; i++) {
			pswd += (key.charAt((int) (Math.random() * key.length())));
		}
		return pswd;		
	}
}
