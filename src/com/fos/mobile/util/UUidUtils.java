package com.fos.mobile.util;

import java.util.UUID;

public class UUidUtils {
	public static String uuid() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		String uuidStr = str.replace("-", "");
		return uuidStr.toUpperCase();
	}
}
