package cn.tedu.cloud_note.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("userService")//ɨ���spring����
@Transactional
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	public NoteResult<User> checkLogin(String name, String password) {
		//���ս������
		NoteResult<User> result = new NoteResult<User>();
		//������name��ѯ���ݿ�
		User user = userDao.findByName(name);
		if(user == null){
			result.setStatus(1);
			result.setMsg("�û���������");
			return result;
		}
		//�������
		String md5Password = NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(md5Password)){
			result.setStatus(2);
			result.setMsg("�������");
			return result;
		}
		//�û��������붼��ȷ
		result.setStatus(0);
		result.setMsg("��¼�ɹ�");
		result.setData(user);
		return result;
	}

	public NoteResult<Object> addUser(String name, String password, String nickname) {
		NoteResult<Object> result = new NoteResult<Object>();
		//�û����
		User hasUser = userDao.findByName(name);
		if(hasUser!=null){ //�û�������
			result.setStatus(1);
			result.setMsg("�û��ѱ�ռ��");
			return result;
		}
		//������û�
		User user = new User();
		//�����û�id
		String id = NoteUtil.createId();
		user.setCn_user_id(id);
		//�����û���
		user.setCn_user_name(name);
		//���ü��ܺ������
		String md5password = NoteUtil.md5(password); 
		user.setCn_user_password(md5password);
		//�����ǳ�
		user.setCn_user_nick(nickname);
		userDao.save(user);
		result.setStatus(0);
		result.setMsg("ע��ɹ�");
		return result;
	}
	//�޸�����
	public NoteResult<Object> changepassword(String userId, String oldpassword, String password) {
		User user = userDao.findByUserId(userId);
		NoteResult<Object> result = new NoteResult<Object>();
		String md5password = NoteUtil.md5(password);
		String md5oldpassword = NoteUtil.md5(oldpassword);
		if(user.getCn_user_password().equals(md5oldpassword)){
			Map<Object,Object> param = new HashMap<Object, Object>();
			param.put("userId", userId);
			param.put("password", md5password);
			int n = userDao.changepassword(param);
			if(n==1){
				result.setStatus(0);
				result.setMsg("�����޸ĳɹ�");
				return result;
			}else{
				result.setStatus(1);
				result.setMsg("�����޸�ʧ��");
				return result;
			}
		}else{
			result.setStatus(3);
			result.setMsg("�������");
			return result;
		}
	}
}



