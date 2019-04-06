package com.fos.mobile.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	public static String md5(String input){
		if (null == input) {
			input = "";
		}
		String result = "";
		try {
			// MessageDigest������ΪӦ�ó����ṩ��ϢժҪ�㷨�Ĺ��ܣ���MD5��SHA�㷨
			MessageDigest md = MessageDigest.getInstance("MD5");
			// ��ȡ����
			md.update(input.getBytes());
			// ��ò������з��ŵĹ�ϣֵ�ֽ����飬����16��Ԫ�أ�
			byte output[] = md.digest();

			// 32λ�ļ����ַ���
			StringBuilder builder = new StringBuilder(32);
			// �������ʮ�����Ƶ�ת��
			for (int offset = 0; offset < output.length; offset++) {
				// ת��ɶ�Ӧ��ASSICֵ
				int value = output[offset];
				// ������תΪ���������շ��ؽ�����޷��ŵģ�
				if (value < 0) {
					value += 256;
				}
				// С��16��תΪʮ�����ƺ�ֻ��һ���ֽڣ����׷��0������2���ֽ�
				if (value < 16) {
					builder.append("0");
				}
				// ��16λbyte[]ת��Ϊ32λ�޷���String
				builder.append(Integer.toHexString(value));
			}
			result = builder.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
		
	}
}
