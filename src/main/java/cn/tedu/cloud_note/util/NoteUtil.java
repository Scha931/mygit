package cn.tedu.cloud_note.util;

import java.security.MessageDigest;
import java.util.UUID;

import sun.misc.BASE64Encoder;

public class NoteUtil {
	
	/*
	 * 利用UUID 算法生成主键
	 */
	public static String createId(){
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();
		return id.replace("-","");
	}
	
	//加密
	public static String md5(String str){
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64 = new BASE64Encoder();
			String ret;
				ret = base64.encode(md5.digest(str.getBytes("utf-8")));
				return ret;
			} catch (Exception e) {
				throw new NoteException("密码加密失败",e);
			}
	}
	
	public static void main(String[] args){
		System.out.println(md5("123456"));
		System.out.println(md5("123456"));
		System.out.println(md5(createId()));
		System.out.println(createId());
	}
}
