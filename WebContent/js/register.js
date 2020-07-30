$(function() {
	$("#tip").empty();
	$("#validationCode_img").attr("src","validate.jsp?time=" + new Date().getTime());
	let url = $("#validationCode").data("url") + "/getSessionServlet";
	
	$("#validationCode_img").click(function() {
		$("#validationCode_img").attr("src","validate.jsp?time=" + new Date().getTime());
		alert(randomCode)
	});

	$("#validationCode").keyup(function() {
		let code = $("#validationCode").val();
		$.get(url,function(data) {
			if (code == data.code) {
				$("#tip").empty();
				$("#submit").removeAttr("disabled");
			}else if (code == ""){
				$("#tip").empty();
				$("#submit").attr("disabled","disabled"); 
			}else {
				$("#tip").html("验证码错误");
				$("#submit").attr("disabled","disabled"); 
			}
		},"JSON")
	})
	
	$("#user").keyup(function() {
		let username = $("#user").val();
		if (username.length < 4) {
			$("#tip").html("用户名长度小于四位");
			$("#submit").attr("disabled","disabled");  
		}else if (username.length > 15) {
			$("#tip").html("用户名长度大于十五位");
			$("#submit").attr("disabled","disabled");
		}else if (username == ""){
				$("#tip").empty();
				$("#submit").attr("disabled","disabled"); 
		}else {
		$("#tip").empty();
		$("#submit").removeAttr("disabled");
		}
	})
	
	$("#password").keyup(function() {
		let pass = $("#password").val();
		if (pass.length < 6) {
			$("#tip").html("密码长度小于六位");
			$("#submit").attr("disabled","disabled");
		}else if (pass.length > 12) {
			$("#tip").html("密码长度大于十二位");
			$("#submit").attr("disabled","disabled");
		}else if (pass == ""){
				$("#tip").empty();
				$("#submit").attr("disabled","disabled"); 
		}else {
			$("#tip").empty();
			$("#submit").removeAttr("disabled");
		}
	})
	
	$("#repassword").keyup(function validate() {
	    let pwd = $("#password").val();
	    let pwd1 = $("#repassword").val();
	    if(pwd == pwd1)
	    {
	        $("#tip").empty();
	    	$("#submit").removeAttr("disabled");
	    }else if (pwd1 == ""){
				$("#tip").empty();
				$("#submit").attr("disabled","disabled"); 
		}else {
			$("#tip").html("两次密码不相同");
			$("#submit").attr("disabled","disabled");  
	     }
	})
	
	
	
});
