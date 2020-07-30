$(function() {
	$(".delete").click(function() {
		let result=confirm("你确定要删除该图片吗？");
    	if (result==true){
			let imageID = $(this).data("imageid");
			let url = $(this).data("url");
			if (!$(this).data("user")) {
				alert("先登录才能收藏图片噢")
			}else {
	       		let uid = $(this).data("user");
				let info = {"imageID":imageID,"uid":uid};
				$.post(url,info,function(){
					alert("删除成功");
				})
				
				let baseUrl = $(".delete").data("baseurl");
				window.location.href = baseUrl + "/myPhotoServlet";
			}
    	}
	})	
})