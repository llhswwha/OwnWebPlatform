package com.GetData.unit;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Unit {

	/**
	* Ĭ�ϵ������ַ�����ϣ��������ֽ�ת���� 16 ���Ʊ�ʾ���ַ�,apacheУ�����ص��ļ�����ȷ���õľ���Ĭ�ϵ�������
	*/
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
	'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	
	protected static MessageDigest messagedigest = null;
	static {
	try {
	messagedigest = MessageDigest.getInstance("MD5");
	} catch (NoSuchAlgorithmException nsaex) {
	System.err.println(MD5Unit.class.getName()+ "��ʼ��ʧ�ܣ�MessageDigest��֧��MD5Unit��");
	nsaex.printStackTrace();
	}
	}
	
	/**
	* �����ַ�����md5У��ֵ
	* 
	* @param s
	* @return
	*/
	public static String getMD5String(String s) 
	{
	return getMD5String(s.getBytes());
	}
	
	
	/**
	* �����ֽ������md5У��ֵ
	* 
	* @param s
	* @return
	*/
	public static String getMD5String(byte[] bytes) 
	{
	messagedigest.update(bytes);
	return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) 
	{
	return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
	StringBuffer stringbuffer = new StringBuffer(2 * n);
	int k = m + n;
	for (int l = m; l < k; l++) {
	appendHexPair(bytes[l], stringbuffer);
	}
	return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
	char c0 = hexDigits[(bt & 0xf0) >> 4];// ȡ�ֽ��и� 4 λ������ת��, >>>
	// Ϊ�߼����ƣ�������λһ������,�˴�δ�������ַ����кβ�ͬ
	char c1 = hexDigits[bt & 0xf];// ȡ�ֽ��е� 4 λ������ת��
	stringbuffer.append(c0);
	stringbuffer.append(c1);
	}
}
