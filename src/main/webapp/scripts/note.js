

//分享笔记
function shareNote(){
	//获取笔记id
	var $li = $(this).parents("li");
	var noteId = $($li).data("noteId");
	$.ajax({
		url:path+"/share/add.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var noteTitle = $li.text();
				var sli="";
					sli +='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					sli +=noteTitle;
					sli +='<i class="fa fa-sitemap"></li>';
					sli +='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
					//将笔记li元素的<a>标记内容替换
					$li.find("a").html(sli);
				alert(result.msg);
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("分享失败");
		}
	});

}

//显示笔记下拉菜单
function showNoteMenu(){
	//显示点击菜单
	var $note_menu = $(this).parents("li").find("div");
	$($note_menu).slideDown(800);
	return false; //组织冒泡事件
}
//保存笔记
function saveNote(){
	//获取选定的li
	var $li = $("#note_ul a.checked").parent();
	//获取li 中的noteId
	var noteId = $($li).data("noteId");
	//获取标题
	var title = $("#input_note_title").val().trim();
	//获取body
	var body = um.getContent();
	$.ajax({
		url:path+"/note/updataNote.do",
		type:"post",
		data:{"noteId":noteId,"note_title":title,"note_body":body},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var str="";
				str +='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
				str +=title;
				str +='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down">';
				str +='</i></button>';
				$li.find("a").html(str);
				//提示成功
				alert(result.msg);
			}

		},
		error:function(){
			alert("保存笔记失败");
		}
	});	
}

//显示笔记内容信息
function loadNoteBody(){
		//清空笔记内容。
		$("#input_note_title").val("");
		//设置笔记内容
		um.setContent("");
		//清除 note 中a 标签中的checked 样式
		$("#note_ul a").removeClass("checked");
		//获取当前li 中的noteId
		var noteId = $(this).data("noteId");
		// 给点击的 li 中a标签中添加checked 样式
		$(this).find("a").addClass("checked");
		$.ajax({
			url:path+"/note/load.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					//取出结果中的data
					var data = result.data;
					//取出data中 标题 和内容数据
					var note_title = data.cn_note_title;
					var note_body = data.cn_note_body;
					//服务器查询到的标题 添加到 标题中
					$("#input_note_title").val(note_title);
					//设置笔记内容
					um.setContent(note_body);								
				}
			},
			error:function(){
				alert("笔记信息加载失败");
			}	
		});
}

//加载每条笔记
function loadnotes(){
		$("#book_ul a").removeClass("checked");
		//设置选中效果
		$(this).find("a").addClass("checked");
		//清空原列表的笔记
		$("#note_ul").empty();
		//获取参数
		var bookId=$(this).data("bookId");
		//发送ajax请求
		$.ajax({
			url:path+"/note/loadnotes.do",
			type:"post",
			data:{"bookId":bookId},
			dataType:"json",
			success:function(result){
				if(result.status ==0){
					//取出data中的数据
					var notes = result.data;
					//遍历数据
					for(var i=0; i<notes.length;i++){
						//获取笔记ID
						var noteId = notes[i].cn_note_id;
						//获取笔记标题
						var noteTitle = notes[i].cn_note_title;
						if("cn_share_id" in notes[i]){
							addlihasShare(noteId,noteTitle);
						}else{
							addli(noteId,noteTitle);	
						}					
					}
				}
			},
			error:function(){
				alert("笔记加载失败");
			}
	});	
}
//没有分享的笔记
function addli(noteId,noteTitle){
	var lis = "";
	lis +='<li class="online">';
	lis +='<a>';
	lis +='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	lis +=noteTitle;
	lis +='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
	lis +='</a>';
	lis +='<div class="note_menu" tabindex="-1">';
	lis +='<dl>';
	lis +='<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	lis +='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	lis +='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	lis +='</dl>';
	lis +='</div>';
	lis +='</li>';
	//将字符串转换成 jquery li对象
	var $li = $(lis);
	//将noteId绑定到$li中
	$li.data("noteId",noteId);
	//将li元素添加到笔记ul列表区
	$("#note_ul").append($li);	
}
//分享过的笔记
function addlihasShare(noteId,noteTitle){
	var lis = "";
	lis +='<li class="online">';
	lis +='<a>';
	lis +='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	lis +=noteTitle;
	lis +='<i class="fa fa-sitemap"></i>';
	lis +='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
	lis +='</a>';
	lis +='<div class="note_menu" tabindex="-1">';
	lis +='<dl>';
	lis +='<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	lis +='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	lis +='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	lis +='</dl>';
	lis +='</div>';
	lis +='</li>';
	//将字符串转换成 jquery li对象
	var $li = $(lis);
	//将noteId绑定到$li中
	$li.data("noteId",noteId);
	//将li元素添加到笔记ul列表区
	$("#note_ul").append($li);	
}