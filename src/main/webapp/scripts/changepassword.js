//修改密码
function changepassword(){
	var userId = getCookie("userId");
	var oldpassword = $("#last_password").val();
	var newpassword = $("#new_password").val();
	var final_password = $("#final_password").val();
	var ok = true;
	if(userId==null){
		ok=false;
		window.location.href="log_in.html";
	}
	if(oldpassword==""){
		ok=false;
		$("#warning_1 span").html("密码不能为空");
		$("#warning_1").show();
	}
	if(newpassword==""){
		ok=false;
		$("#warning_2 span").html("密码不能为空");
		$("#warning_2").show();
	}
	if(newpassword.length >0 && newpassword.length <6){
		ok=false;
		$("#warning_2 span").html("密码需要大于6位");
		$("#warning_2").show();
	}
	if(newpassword!=final_password){
		ok=false;
		$("#warning_3 span").html("密码输入不一致");
		$("#warning_3").show();
	}
	if(ok){
		$.ajax({
			url:path+"user/changepwd.do",
			type:"post",
			data:{"userId":userId,"oldpassword":oldpassword,"newpassword":newpassword},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					$("#warning_1 span").html(result.msg);
					window.location.href="log_in.html";
				}
				if(result.status==3){
					$("#warning_1 span").html(result.msg);
				}else{
					alert(result.msg);
				}
			},
			error:function(){
				alert("修改密码失败");
			}
		});
	}
}




