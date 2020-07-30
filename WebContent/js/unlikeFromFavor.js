$(function() {
	

	$(".unlikeButton").click(function() {
		var result=confirm("你确定要取消喜欢吗？");
    	if (result==true){
			var imageID = $(this).data("imageid");
			var url = $(this).data("url");
			
			if (!$(this).data("user")) {
				alert("先登录才能收藏图片噢")
			}else {
	       		var uid = $(this).data("user");
				var info = {"imageID":imageID,"uid":uid};
				$.post(url,info,function(){
					alert("OK 取消喜欢完成");
				},"JSON")
			}
    	}
    	else{
        	
    	}
		
	})	
})
