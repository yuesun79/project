$(function() {
	$("button").click(function() {
		
		let url = $("#button").data("url");
		$.post(url,function() {
			alert("修改成功")
			if ($("#button").text() === "不让其他人看我的收藏") {
				$("#button").text("允许其他人看我的收藏");
			}else {
				$("#button").text("不让其他人看我的收藏");
			}
			alert(("#button").text());
		})
	});
})