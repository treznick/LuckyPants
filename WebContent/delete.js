//we are reusing this URL, so move it to a variable
base_url="/rest/books/";
meta_url="/rest/metadata/";
$(document).ready(function(){

    $('#book_form').submit(function(){
    	console.log($('.isbn').val());
    	deleteData($('.isbn').val());
    	return false;
    });
    function deleteData(isbn){
        $.ajax({
                type: "DELETE",
                url: "/rest/books/"+isbn,
                success: function (data, status, jqXHR) {
                    alert("deleted");
                },
                error: function (jqXHR, status) {
                    console.log(jqXHR);
                    alert('failed, please check console for errors');
                }
             });
       }
});
