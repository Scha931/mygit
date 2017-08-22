package cn.tedu.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.service.ShareService;
import cn.tedu.cloud_note.util.NoteResult;
@Controller
@RequestMapping("/share")
public class SearchShareController {
	@Resource
	private ShareService service;
	@RequestMapping("/search.do")
	@ResponseBody
	public NoteResult<List<Share>> execute(String keyword,int page){
		System.out.println(service.search(keyword,page));
		return service.search(keyword,page);
	}
}
