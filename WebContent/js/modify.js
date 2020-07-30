$(function() {
	$(".another-button").click(function() {
		window.location.href=$(this).data("url")+"?imageID="+$(this).data("imageid")+"&path="+$(this).data("path");
	})
})