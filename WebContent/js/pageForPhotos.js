$(function() {
	let uid = $(".another-button").data("user");
	$(".singlePage").click(function() {
		
		alert(uid);
		let page = $(this).data("page");
		let baseUrl = $(this).data("url");
		let contextUrl = $(this).data("baseurl");
		let url = baseUrl + "?page=" + page;
		let info = {"page":page};
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
					let buttons;
					if ($("#page").data("pagetype") === "favor") {
						buttons = "<input class=\"unlikeButton\" type=\"button\" name=\"like\" value=\"取消喜欢\"" +
	                        "data-url=\""+ contextUrl +"/likeButtonServlet?method=unlikeButton\"" +
	                        "data-imageid=\""+ imageID +"\"" +
	                        "data-user=\""+uid+"\"" +
	                        ">"
					}else {
						buttons = "<input type=\"button\" value=\"修改\" class=\"another-button\" data-imageid=\""+ imageID +"\"" + 
							"data-url=\""+ contextUrl +"/modifyServlet\" data-path=\""+ path +"\">" +
                    		"<input class=\"delete\" type=\"button\" value=\"删除\"" +
                    		"data-imageid=\""+ imageID +"\" data-user=\""+ uid +"\" data-url=\""+ contextUrl +"/deleteMyPhotoServlet\"" + 
							"data-baseurl=\""+ contextUrl +"\""
					}
					
					$("#txtHint").append(
					"<figure>" +
				        "<a target='_Blank' href='" + contextUrl + "/pageServlet?imageID=" + imageID + "&page=" + page + "'>" +
				        	"<img src='"+ contextUrl +"/static/photos/" + path + "' alt='" + title + "'></a>" +
					        "<figcaption>" +
				            "<h2>"+title+"</h2>" +
				            "<p>"+description+"</p>" +
				            "<p class='differButtons'>"+buttons+"</p>" +
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