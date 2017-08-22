package cn.tedu.cloud_note.dao;

import java.util.Map;

import cn.tedu.cloud_note.entity.User;

public interface UserDao {
	//��¼����
	public User findByName(String name);
	//ע�᷽��
	public void save(User user);
	//����userId��ѯuser��Ϣ
	public User findByUserId(String userId);
	//�޸�����
	public int changepassword(Map param);
}
