//we are reusing this URL, so move it to a variable
base_url="/rest/books/";
$(document).ready(function(){
    $.getJSON(base_url,function(data){
    	console.log(data);
    	$.each( data, function( count, object ) { 
    		if(object.isbn != undefined) {//make sure isbn is valid
    			//build the URL for the book
    			$("div.books_list").append( "<br/><a href='"+base_url+object.isbn+"'>"+ object.title+"</a>"  );
    		}
    	});
    });
});