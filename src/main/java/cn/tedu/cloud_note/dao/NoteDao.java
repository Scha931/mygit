package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.tedu.cloud_note.entity.Note;


public interface NoteDao {
	public List<Map> findByBookId(String bookId);
	public Note findByNoteId(String noteId);
	public int updataNote(Note note);
	public int insertNote(Note note);
	public int changeNoteStatus(@Param("noteId")String noteId,@Param("statusId")String statusId);
}
