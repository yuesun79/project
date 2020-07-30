$(function() {
	let url = $("#country").data("url") + "/selectServlet";
	$.get(url,function(data) {

	    for (let key in data){
	        let option_country = document.createElement("option");
	        option_country.innerHTML = key + "";
	        $("#country").append(option_country);
	    }

		for (let i = 0; i < data["Angola"].length; i++) {
	            let option_city = document.createElement("option");
	            option_city.innerHTML = data["Angola"][i] + "";
	            $("#city").append(option_city);
	        }
	
		$("#country").change(function() {
	        let choice = $("#country option:selected").text();
		console.log(choice);
	        $("#city").empty();
			
	        for (let i = 0; i < data[choice].length; i++) {
	            let option_city = document.createElement("option");
	            option_city.innerHTML = data[choice][i] + "";
	            $("#city").append(option_city);
	        }
		
		})
			
	},"JSON")
})
