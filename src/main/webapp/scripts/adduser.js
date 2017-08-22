//获取参数
function adduser(){
			var name = $("#regist_username").val().trim();
			var nickname = $("#nickname").val().trim();
			var password = $("#regist_password").val().trim();
			var final_password = $("#final_password").val().trim();
			//alert(name+","+nickname+","+password+","+final_password);
			//检测数据格式
			//检测用户
			
			var ok = true; //表示参数状态
			if(name == ""){
				ok= false;
				$("#warning_1 span").html("用户不能为空");
				$("#warning_1").show();
			}
			//检测密码:1.非空 ，2.不能小于6位
			if(password==""){
				ok= false;
				$("#warning_2 span").html("密码不能为空");
				$("#warning_2").show();
			}else if(password.length >0 && password.length <6){
				ok= false;
				$("#warning_2 span").html("密码不能小于6位");
				$("#warning_2").show();
			}

			//检测确认密码：1.非空，是否与密码一致
			if(final_password != password){
				ok= false;
				$("#warning_3 span").html("密码输入不一致");
				$("#warning_3").show();
			}
			//数据校验通过
			if(ok){
				$.ajax({
					url:path+"/user/add.do",
					type:"post",
					data:{"name":name,"password":password,"nick":nickname},
					dataType:"json",
					success:function(result){
						if(result.status==0){
							$("#warning_1 span").html(result.msg);
							//返回到登录页面
							$("#back").click();
						}else if(result.status==1){
							//用户名被占用
							//alert(result.msg);
							$("#warning_1 span").html(result.msg);
							$("#warning_1").show();
							
						}
			
					},
					error:function(){
						alert("注册失败");
					}
				});
			}

		};
		
