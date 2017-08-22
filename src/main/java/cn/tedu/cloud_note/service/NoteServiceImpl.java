package cn.tedu.cloud_note.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.dao.ShareDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("noteService")
public class NoteServiceImpl implements NoteService{
	@Resource
	private NoteDao notedao;
	@Resource
	private ShareDao shareDao;
	//加载笔记
	public NoteResult<List<Map>> loadNote(String bookId) {
		List<Map> list = notedao.findByBookId(bookId);
		NoteResult<List<Map>> result = new NoteResult<List<Map>>();
		if(list ==null || list.isEmpty()){
			result.setStatus(1);
			result.setMsg("还没有笔记");
			return result;
		}
		//遍历Map ，
		for(Map map:list){
			//查询该笔记本中的noteId
			String noteId = (String)map.get("cn_note_id");
			//通过noteId 查找cn_share表中的对应的数据
			List<Map> shares = shareDao.findShareIdByNoteId(noteId);
			//遍历share 表
			for(Map share:shares){
				//判断share表中noteId 和note表中noteId是否相同
				if(map.get("cn_note_id").equals(share.get("cn_note_id"))){
					//如果相同把share 表中的shareId 添加到map中
					map.put("cn_share_id",share.get("cn_share_id"));
				}
			}
		}
		result.setStatus(0);
		result.setData(list);
		result.setMsg("加载成功");
		return result;
	}
	//加载笔记内容
	public NoteResult<Note> loadNoteBody(String noteId) {
		Note note = notedao.findByNoteId(noteId);
		NoteResult<Note> result = new NoteResult<Note>();
		if(note==null){
			result.setStatus(1);
			result.setMsg("没有找到笔记内容");
			return result;
		}
		result.setStatus(0);
		result.setData(note);
		result.setMsg("找到笔记内容");
		return result;
	}
	//更新笔记
	public NoteResult<Object> updataNote(String noteId, String note_title, String note_body) {
		NoteResult<Object> result = new NoteResult<Object>();
		//将数据传入数据库进行更新。
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(note_title);
		note.setCn_note_body(note_body);
		Long time = System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		//更新数据库记录
		int rows = notedao.updataNote(note);
		if(rows==1){
			result.setStatus(0);
			result.setData(note);
			result.setMsg("保存笔记成功");
			return result;
		}else{
			result.setStatus(1);
			result.setMsg("保存笔记失败");
			return result;
		}	
	}
	//添加笔记
	public NoteResult<Object> addNote(String userId, String bookId,String title) {
		String noteId = NoteUtil.createId();
		Long createTime = System.currentTimeMillis();
		String status = "1";
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(bookId);
		note.setCn_user_id(userId);
		note.setCn_note_status_id(status);
		note.setCn_note_title(title);
		note.setCn_note_create_time(createTime);
		int n = notedao.insertNote(note);
		NoteResult<Object> result = new NoteResult<Object>();
		if(n==1){
			result.setStatus(0);
			result.setData(note.getCn_note_id());
			result.setMsg("添加笔记成功");
			return result;
		}else{
			result.setStatus(1);
			result.setMsg("添加笔记失败");
			return result;
		}
	}
	//移动笔记到回收站
	public NoteResult<Object> changeNoteStatus(String noteId) {
		int n = notedao.changeNoteStatus(noteId, "0");
		NoteResult<Object> result = new NoteResult<Object>();
		if(n==1){
			result.setStatus(0);
			result.setMsg("删除笔记成功");
			return result;
		}else{
			result.setStatus(1);
			result.setMsg("删除笔记成功");
			return result;
		}
	}
}









