<%@ page import="com.luckypants.model.Book"%>
Lucky Pants Books
<br />
<%
	Book book1 = new Book();
	book1.setTitle(request.getParameter("title"));
	String authorString = request.getParameter("authors");
	String returnStatus = new String();
	boolean errorFlag = false;
	String [] authorInputs = authorString.split(";");
	
	for(int i = 0; i<authorInputs.length; ++i) {
		if(book1.addAuthor(authorInputs[i])) {
			returnStatus = "You Have Successfully Created a Book!";
			errorFlag = false;
		} else {
			returnStatus = "Error, Book Unsuccessfully Created.";
			errorFlag = true;
		}
	}
	
	out.println("<br/>");
	out.println(returnStatus);
	out.println("<br/>");
	
	if(!errorFlag) {
		out.println("Title: " + book1.getTitle() + "<br/>");
		for(int i=0; i<book1.getAuthors().size(); ++i) {
			out.println("Author " + (i+1) +": " + book1.getAuthors().get(i) + "<br/>");
		}
	}
%>