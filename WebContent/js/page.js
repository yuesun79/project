$(function() {
	$(".singlePage").click(function() {
		let page = $(this).data("page");
		let baseUrl = $(this).data("url");
		let contentRadio = $(this).data("radio");
		let orderRadio = $(this).data("order");
		let content = $(this).data("content");
		let url = baseUrl + "/searchServlet?page=" + page;
		let info = {"page":page,"contentRadio":contentRadio,"orderRadio":orderRadio,"content":content};
		$.post(url,info,function(data){
			if (data.length == 0) {
					alert("此页为空");
			}
			else {
				$("#txtHint").empty()
				for (let i = 0; i < data.length; i++) {
					let imageID = data[i].imageID;
					let path = data[i].path;
					let title = data[i].title;
					let description = data[i].description;
					$("#txtHint").append(
					"<figure>" +
				        "<a target='_Blank' href='"+baseUrl+"/pageServlet?imageID=" + imageID + "&page=" + page + "'>" +
				        	"<img src='"+ baseUrl +"/static/photos/" + path + "' alt='" + title + "'></a>" +
					        "<figcaption>" +
				            "<h2>"+title+"</h2>" +
				            "<p>"+description+"</p>" +
			       			"</figcaption>" +
	        		"</figure>")
				}
				let totalpage = $("#page").children("li").length - 2;
				$("#prev").data("page",(page-1)>0?page-1:1);
				$("#next").data("page",(page+1)>totalpage?totalpage:page+1);
			}
		},"JSON")
		return false;
	})
})