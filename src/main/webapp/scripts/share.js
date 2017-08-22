//按回车，加载搜索结果的第一页
function searchShareNote(event){
	var code = event.keyCode;
	//如果是回车键 就执行
	if(code==13){
		//清空搜索结果列表
		$("#sixth_side_right ul").empty();
		//获取搜索框文字
		var keyword =  $("#search_note").val().trim();
		page = 1;
		loadPageshare(keyword,page);
	}
};
//点击更多，加载分页搜索结果。
function moreSearchShare(){
	//清空搜索结果列表
	$("#sixth_side_right ul").empty();
	page+=1;
	//获取搜索框文字
	var keyword =  $("#search_note").val().trim();
	loadPageshare(keyword,page);
}



//加载搜索分享笔记请求
function loadPageshare(keyword,page){
	$.ajax({
		url:path+"share/search.do",
		type:"post",
		data:{"keyword":keyword,"page":page},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var data = result.data;
				$("#pc_part_2").hide();
				$("#pc_part_6").show();
				var ul = $("#sixth_side_right ul");
				for(var i=0,len=data.length;i<len;i++){
					//获取分享笔记id
					var shareId = data[i].cn_share_id;
					//获取笔记标题
					var shareTitle = data[i].cn_share_title;
					//获取笔记Id
					var noteId = data[i].cn_note_id;
					//获取li对象
					var lis = "";
					lis +='<li class="online">';
					lis +='<a>';
					lis +='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					lis +=shareTitle;
					lis +='<i class="fa fa-sitemap"></i>';
					lis +='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
					lis +='</a>';
					lis +='</li>';
					var $li = $(lis);
					//绑定shareId
					$li.data("shareId",shareId);
					//加载笔记
					$("#sixth_side_right ul").append($li);
				}
			}	
		},
		error:function(){
			//alert("搜索笔记失败");
		}
	});
};