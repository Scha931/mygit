package cn.tedu.cloud_note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.dao.ShareDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;
@Service("shareService")
public class ShareServiceImpl implements ShareService{
		@Resource
		private ShareDao shareDao;
		@Resource
		private NoteDao noteDao;
		
	public NoteResult<Object> shareNote(String noteId) {
		Note note = noteDao.findByNoteId(noteId);
		//��cn_share ������¼
		Share share = new Share();
		String shareId = NoteUtil.createId();
		share.setCn_share_id(shareId);
		share.setCn_note_id(noteId);
		share.setCn_share_title(note.getCn_note_title());
		share.setCn_share_body(note.getCn_note_body());
		//��������¼
		shareDao.save(share);
		//����result
		NoteResult<Object> result = new NoteResult<Object>();
		result.setStatus(0);
		result.setMsg("����ʼǳɹ�");
		return result;
	}

	public NoteResult<List<Share>> search(String keyword,int page) {
		Map<Object,Object> param = new HashMap<Object, Object>();
		keyword = "%"+keyword+"%";
		page = (page-1)*3;
		param.put("title", keyword);
		param.put("page", page);
		List<Share> shares = shareDao.findShareBysearch(param);
		NoteResult<List<Share>> result = new NoteResult<List<Share>>();
		if(shares.isEmpty()){
			result.setStatus(1);
			result.setMsg("û���������ʼ�");
			return result;
		}else{
			result.setStatus(0);
			result.setData(shares);
			result.setMsg("�������ʼ�");
			return result;
		}
	}
}







