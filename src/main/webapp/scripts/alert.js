//删除笔记窗口
function deleteNoteWindow(){
	$("#can").load("alert/alert_delete_note.html");
}
//删除笔记
function deleteNote(){
	var li = $("#note_ul a.checked").parent();
	var noteId = $(li).data("noteId");
	$.ajax({
		url:path+"note/changeNoteStatus.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//清除当前li
				$(li).remove();
				alert(result.msg);
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			//alert("删除失败！");
		}
	});
}
//新建笔记窗口
function alertAddNoteWindow(){
	//判断是否有笔记本被选中
	var $li = $("#book_ul a.checked").parent();
	if($li.length==0){
		alert("请选择笔记本");
	}else{//弹出创建笔记对话框
		$("#can").load("alert/alert_note.html");
		$(".opacity_bg").show();
	}
}
//创建新笔记
function addNote(){
	//获取Cookie中的userId
	var userId = getCookie("userId");
	//获取bookId
	$li =$("#book_ul a.checked").parent();
	var bookId = $li.data("bookId");
	//获取笔记名称
	var title = $("#input_note").val().trim();
	//数据格式检查
	var ok = true;
	if(title ==""){
		$("#input_note").siblings("span").html("笔记名称不能为空");
		ok=false;
	}
	if(userId ==null){//检查是否失效
		ok=false;
		window.location.href="log_in.html";
	}
	if(ok){
		$.ajax({
			url:path+"note/addNote.do",
			type:"post",
			data:{"userId":userId,"bookId":bookId,"title":title},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					//把创建的笔记加载到笔记列表中
					var lis = "";
					lis +='<li class="online">';
					lis +='<a>';
					lis +='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+title+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
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
					$li.data("noteId",result.data);
					//将笔记添加到列表
					$("#note_ul").prepend($li);
					alert(result.msg);
					//关闭创建笔记本窗口
					closeAlertWindow();
				}else{
					alert(result.msg);
					//关闭创建笔记本窗口
					closeAlertWindow();
				}
			},
			error:function(){
				alert("创建笔记失败！");
			}
		});
	}
}

//新建笔记本窗口
function alertAddBookWindow(){
		//弹出新建笔记本对话框
		$("#can").load("alert/alert_notebook.html");	
		//显示背景
		$(".opacity_bg").show();
}
//取消，退出 新建笔记本
function closeAlertWindow(){
	//清空div中的
	$("#can").html("");
	//隐藏背景色
	$(".opacity_bg").hide();
}
//创建 新笔记本
function addBook(){
	//获取 bookName 的值
	var bookName = $("#input_notebook").val();
	var userId = getCookie("userId");
	var ok= true;
	if(bookName ==""){
		ok=false;
		$("#book_span").html("笔记本名称不能为空");
	}
	if(ok){
		$.ajax({
			url:path+"/book/addbook.do",
			type:"post",
			data:{"bookName":bookName,"userId":userId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					//把创建的笔记本 添加到li
					var sli = "";
					sli +='<li class="online">';
					sli +='<a>';
					sli +='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
					sli +='</i>';
					sli += bookName;
					sli +='</a>';
					sli +='</li>';
					$("#book_ul").prepend(sli);
					//提示添加成功
					alert(result.msg);
					//退出新建笔记本
					closeAlertWindow();
				}
			},
			error:function(){
				alert("新建笔记本失败");
			}
			
		});
	}
}