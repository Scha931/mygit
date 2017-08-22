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
	//���رʼ�
	public NoteResult<List<Map>> loadNote(String bookId) {
		List<Map> list = notedao.findByBookId(bookId);
		NoteResult<List<Map>> result = new NoteResult<List<Map>>();
		if(list ==null || list.isEmpty()){
			result.setStatus(1);
			result.setMsg("��û�бʼ�");
			return result;
		}
		//����Map ��
		for(Map map:list){
			//��ѯ�ñʼǱ��е�noteId
			String noteId = (String)map.get("cn_note_id");
			//ͨ��noteId ����cn_share���еĶ�Ӧ������
			List<Map> shares = shareDao.findShareIdByNoteId(noteId);
			//����share ��
			for(Map share:shares){
				//�ж�share����noteId ��note����noteId�Ƿ���ͬ
				if(map.get("cn_note_id").equals(share.get("cn_note_id"))){
					//�����ͬ��share ���е�shareId ��ӵ�map��
					map.put("cn_share_id",share.get("cn_share_id"));
				}
			}
		}
		result.setStatus(0);
		result.setData(list);
		result.setMsg("���سɹ�");
		return result;
	}
	//���رʼ�����
	public NoteResult<Note> loadNoteBody(String noteId) {
		Note note = notedao.findByNoteId(noteId);
		NoteResult<Note> result = new NoteResult<Note>();
		if(note==null){
			result.setStatus(1);
			result.setMsg("û���ҵ��ʼ�����");
			return result;
		}
		result.setStatus(0);
		result.setData(note);
		result.setMsg("�ҵ��ʼ�����");
		return result;
	}
	//���±ʼ�
	public NoteResult<Object> updataNote(String noteId, String note_title, String note_body) {
		NoteResult<Object> result = new NoteResult<Object>();
		//�����ݴ������ݿ���и��¡�
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(note_title);
		note.setCn_note_body(note_body);
		Long time = System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		//�������ݿ��¼
		int rows = notedao.updataNote(note);
		if(rows==1){
			result.setStatus(0);
			result.setData(note);
			result.setMsg("����ʼǳɹ�");
			return result;
		}else{
			result.setStatus(1);
			result.setMsg("����ʼ�ʧ��");
			return result;
		}	
	}
	//��ӱʼ�
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
			result.setMsg("��ӱʼǳɹ�");
			return result;
		}else{
			result.setStatus(1);
			result.setMsg("��ӱʼ�ʧ��");
			return result;
		}
	}
	//�ƶ��ʼǵ�����վ
	public NoteResult<Object> changeNoteStatus(String noteId) {
		int n = notedao.changeNoteStatus(noteId, "0");
		NoteResult<Object> result = new NoteResult<Object>();
		if(n==1){
			result.setStatus(0);
			result.setMsg("ɾ���ʼǳɹ�");
			return result;
		}else{
			result.setStatus(1);
			result.setMsg("ɾ���ʼǳɹ�");
			return result;
		}
	}
}









