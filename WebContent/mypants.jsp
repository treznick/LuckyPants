<%@ page import="com.luckypants.model.Book"%>
Lucky Pants Books
<br/>
Create a Book:
<br/>
<form action="mypants_create.jsp" method="POST">
	Title: <input type="text" name="title"><br/>
	Input Authors Separated by ';'<br/>
	Authors: <input type="text" name="authors"><br/>
	Pages: <input type="number" name="pages"><br/>
	<input type="submit" value="Create my Book!">
</form>
