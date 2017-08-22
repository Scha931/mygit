package test.service;

import java.util.List;

import org.junit.Test;

import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.service.ShareService;
import cn.tedu.cloud_note.util.NoteResult;

public class TestShareService extends TestBase{
	@Test //����1. ����Ԥ�ƣ� ����ʼǳɹ�
	public void test1(){
		ShareService service = super.getContext().getBean("shareService",ShareService.class);
		String noteId = "e9d952e73a10449d834fde57ccf9416e";
		NoteResult<Object> result = service.shareNote(noteId);
		System.out.println(result);
	}
	@Test //����2. ����Ԥ�ƣ������� ���ڲ��Ե� �ʼ�
	public void test2(){
		ShareService service = super.getContext().getBean("shareService",ShareService.class);
		String keyword = "����";
		int page = 1;
		NoteResult<List<Share>> result = service.search(keyword, page);
		System.out.println(result);
	}
	@Test //����3. ����Ԥ�ƣ��������� ���� ��ʦ���ıʼ�
	public void test3(){
		ShareService service = super.getContext().getBean("shareService",ShareService.class);
		String keyword = "��ʦ��";
		int page = 1;
		NoteResult<List<Share>> result = service.search(keyword, page);
		System.out.println(result);

	}
}
