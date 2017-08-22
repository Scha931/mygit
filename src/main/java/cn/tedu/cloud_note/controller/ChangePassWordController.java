package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;
@Controller
@RequestMapping("/user")
public class ChangePassWordController {
	@Resource
	private UserService service;
	@RequestMapping("/changepwd.do")
	@ResponseBody
	public NoteResult<Object> execute(String userId,String oldpassword,String newpassword){
		System.out.println(service.changepassword(userId, oldpassword, newpassword));
		return service.changepassword(userId, oldpassword, newpassword);
	}
}
