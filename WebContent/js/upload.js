/*
上传预览
*/
$(function() {
	if ($("#box").find("#modify").length > 0) {
		$("option[value=$('#content').data('content')]").attr("selected",true);
		$("option[value=$('#country').data('country')]").attr("selected",true);
		$("option[value=$('#city').data('city')]").attr("selected",true);
	}
	let output = document.getElementById('box');
	$("#file").change(function(e) {
	
	    let file = e.target.files[0];
	    let fileReader = new FileReader();
	    let type = 'default';
	
	    if(/image/.test(file.type)){
	        fileReader.readAsDataURL(file);
	        type = 'image';
	    } else {
	        fileReader.readAsText(file,'utf-8');
	        type = 'text';
	    }
	    fileReader.onerror = function () {
	        output.innerHTML = 'Could not read file, error code is ' + fileReader.error.code;
	    };
	    fileReader.onload = function () {
	
	        let html = '';
	        switch (type) {
	            case 'image':
	                html = '<img src="' + fileReader.result +'">';
	                break;
	            case 'text':
	                html = fileReader.result;
	                break;
	        }
	        $("#box").empty();
			$("#box").append(html);
	    };
	}) 
})

