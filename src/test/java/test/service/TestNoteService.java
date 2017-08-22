package test.service;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

public class TestNoteService extends TestBase{

	@Test //����1. ����Ԥ�⣺�鵽noteName status=0  ����ͨ��noteId����cn_share���������ͬnoteId���shareIdд��map��
	public void test1(){
		NoteService server = super.getContext().getBean("noteService",NoteService.class);
		NoteResult<List<Map>> list = server.loadNote("0cd94778-4d52-486d-a35d-263b3cfe6de9");
		List<Map> data = list.getData();
		for(Map map:data){
			System.out.println(map);
		}
	}
	@Test //����2. ����Ԥ�⣺û�бʼ�
	public void test2(){
		NoteService server = super.getContext().getBean("noteService",NoteService.class);
		NoteResult<List<Map>> list = server.loadNote("1111");
		System.out.println(list);
	}
	
	@Test //����3. ����Ԥ�ڣ� ����noteId �鵽��Ӧ�ıʼ�����
	public void test3(){
		NoteService service = super.getContext().getBean("noteService",NoteService.class);
		NoteResult<Note> result = service.loadNoteBody("e9d952e73a10449d834fde57ccf9416e");
		System.out.println(result);
	}
	@Test //����4. ����Ԥ�ڣ� �������ݳɹ�
	public void test4(){
		NoteService service = super.getContext().getBean("noteService",NoteService.class);
		String noteId = "7e87de1e-0963-4fe0-b996-d0b13481786e";
		String note_title ="�ܼ�111";
		String note_body = "��ˮ�����̿�ʥ���ڷ���ʢ���˿��Ȼ�����˹���˷������������ᷢ��˹�ٷ�,..";
		NoteResult<Object> result = service.updataNote(noteId, note_title, note_body);
		System.out.println(result);
	}
	@Test //����5. ����Ԥ�⣺��ӱʼǳɹ�
	public void test5(){
		NoteService service = super.getContext().getBean("noteService",NoteService.class);
		String userId = "48595f52-b22c-4485-9244-f4004255b972";
		String bookId = "1454cd9b8fee4b62808e68e5fd8f9c6c";
		String noteId = NoteUtil.createId();
		String title = "���Աʼ����a2";
		NoteResult<Object> result = service.addNote(userId, bookId, title);
		System.out.println(result);
	}
	@Test
	public void test6(){
		NoteService service = super.getContext().getBean("noteService",NoteService.class);
		NoteResult<Object> result = service.changeNoteStatus("3e001c6d01394d83bc9fc4d466ca81bb");
		System.out.println(result);
	}

}
