$(function() {
	$("#likeButton").click(function() {
		var imageID = $("#picture").data("imageid");
		var baseUrl = $(this).data("url");
		var url = baseUrl+"?method="+$(this).data("type");
		if (!$(this).data("user")) {
			alert("先登录才能收藏图片噢")
		}else {
			/*alert("here");//*/
			var uid = $(this).data("user");
			var info = {"imageID":imageID,"uid":uid};
			$.post(url,info,function(data){
				/*alert(data.likes);//*/
				if($("#likeButton").data("type") === "likeButton"){
					$("#likeButton").data("type","unlikeButton");
					$("#likeButton").val("取消喜欢");
				}else if ($("#likeButton").data("type") === "unlikeButton") {
					$("#likeButton").data("type","likeButton");
					$("#likeButton").val("喜欢")
				}
				
				$("p.like").text(data.likes)

			},"JSON")
		}
	})	

	$("#unlikeButton").click(function() {
		var result=confirm("你确定要取消喜欢吗？");
    	if (result==true){
			var imageID = $("#picture").data("imageid");
			var baseUrl = $(this).data("url");
			var url = baseUrl+"?method="+$(this).attr("id");
			if (!$(this).data("user")) {
				alert("先登录才能收藏图片噢")
			}else {
	       		var uid = $(this).data("user");
				var info = {"imageID":imageID,"uid":uid};
				$.post(url,info,function(data){
					alert(data.likes);
					$("#unlikeButton").val("喜欢");
					$("p.like").text(data.likes)
					alert($("#unlikeButton").data("url"));
					
					$("#unlikeButton").attr("id","likeButton");
					
				},"JSON")
			}
    	}
    	else{
        	
    	}
		
	})	
})
