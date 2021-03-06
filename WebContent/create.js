//we are reusing this URL, so move it to a variable
base_url="/rest/books/";
meta_url="/rest/metadata/";
author_url="/rest/authors/";

$(document).ready(function(){
    $.getJSON(meta_url+"book",function(data){
    		$.each(data, function(key, value){
    			if(key != "_author_id" && key != "genres" && key != "author") {
    				$("div.book_div").append("<br/>Please enter " + key + "<input type='text' name='"+key+"'"+">");
    			} else if(key == "author") {
    				$("div.book_div").append("<br/> Please enter author: <select id='author_select' name='_author_id'></select>");
    			}
    		});
    });
    $.getJSON(author_url, function(data){
		console.log(data);
		$.each (data, function( count, object){
			if(object.fname != undefined || object.lname != undefined ) {
				$("select#author_select").append("<option value='"+object.id+"'>"+object.fname+" "+object.lname+"</option>");
			}
		});
	});
    $.fn.serializeObject = function()
    {
       var o = {};
       var a = this.serializeArray();
       $.each(a, function() {
           if (o[this.name]) {
               if (!o[this.name].push) {
                   o[this.name] = [o[this.name]];
               }
               o[this.name].push(this.value || '');
           } else {
               o[this.name] = this.value || '';
           }
       });
       return o;
    };
    $('#book_form').submit(function(){
    	console.log($('#book_form').serializeObject());
    	postData($('#book_form').serializeObject());
    	return false;
    });
    function postData(data){
        $.ajax({
                type: "PUT",
                url: "/rest/books/",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                crossDomain: true,
                dataType: "json",
                success: function (data, status, jqXHR) {
                    alert("success");
                },
                error: function (jqXHR, status) {
                    console.log(jqXHR);
                    alert('failed, please check console for errors');
                }
             });
       }
});