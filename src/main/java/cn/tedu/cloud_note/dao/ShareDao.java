package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.cloud_note.entity.Share;

public interface ShareDao {
	public int save(Share share);
	public List<Map> findShareIdByNoteId(String NoteId);
	public List<Share> findShareBysearch(Map param);
}
