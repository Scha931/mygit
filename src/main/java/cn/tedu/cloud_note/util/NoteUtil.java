package cn.tedu.cloud_note.util;

import java.security.MessageDigest;
import java.util.UUID;

import sun.misc.BASE64Encoder;

public class NoteUtil {
	
	/*
	 * ����UUID �㷨��������
	 */
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();
		return id.replace("-","");
	}
	
	//����
	public static String md5(String str){
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64 = new BASE64Encoder();
			String ret;
				ret = base64.encode(md5.digest(str.getBytes("utf-8")));
				return ret;
			} catch (Exception e) {
				throw new NoteException("�������ʧ��",e);
			}
	}
	
	public static void main(String[] args){
		System.out.println(md5("123456"));
		System.out.println(md5("123456"));
		System.out.println(md5(createId()));
		System.out.println(createId());
	}
}
