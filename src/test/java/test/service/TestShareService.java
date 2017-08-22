package test.service;

import java.util.List;

import org.junit.Test;

import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.service.ShareService;
import cn.tedu.cloud_note.util.NoteResult;

public class TestShareService extends TestBase{
	@Test //用例1. 测试预计： 分享笔记成功
	public void test1(){
		ShareService service = super.getContext().getBean("shareService",ShareService.class);
		String noteId = "e9d952e73a10449d834fde57ccf9416e";
		NoteResult<Object> result = service.shareNote(noteId);
		System.out.println(result);
	}
	@Test //用例2. 测试预计：搜索到 关于测试的 笔记
	public void test2(){
		ShareService service = super.getContext().getBean("shareService",ShareService.class);
		String keyword = "测试";
		int page = 1;
		NoteResult<List<Share>> result = service.search(keyword, page);
		System.out.println(result);
	}
	@Test //用例3. 测试预计：搜索不到 关于 大师傅的笔记
	public void test3(){
		ShareService service = super.getContext().getBean("shareService",ShareService.class);
		String keyword = "大师傅";
		int page = 1;
		NoteResult<List<Share>> result = service.search(keyword, page);
		System.out.println(result);

	}
}
