package com.luckypants.books;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.luckypants.command.CreateBookCommand;
import com.luckypants.command.DeleteBookCommand;
import com.luckypants.command.GetBookCommand;
import com.luckypants.command.ListAllBooksCommand;
import com.luckypants.command.FindBooksCommand;
import com.luckypants.model.Book;
import com.mongodb.DBObject;


@Path("/books")
public class BookService {
	ObjectMapper mapper = new ObjectMapper();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listBooks() {
		ListAllBooksCommand listBooks = new ListAllBooksCommand();
		ArrayList<DBObject> list = listBooks.execute();
		return Response.status(200).entity(list).build();
	}
	
	@GET
	@Path("/{key}/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooksByValue(@PathParam("key") String key, @PathParam("value") String value) {
		FindBooksCommand findBooks = new FindBooksCommand();
		ArrayList<DBObject> list = findBooks.execute(key, value);
		return Response.status(200).entity(list).build();
	}
	
	@GET
	@Path("/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBook(@PathParam("isbn") String isbn) {
		GetBookCommand getBookCommand = new GetBookCommand();
		DBObject book = getBookCommand.execute(isbn);
		return Response.status(200).entity(book).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	public Response createBook(String bookStr) {
		
		try {
			CreateBookCommand create = new CreateBookCommand();
			Book book = mapper.readValue(bookStr, Book.class);
			boolean success = create.execute(book);
			String bookJSON = mapper.writeValueAsString(book);
			if (success) {
				return Response.status(201).entity(bookJSON).build();
			} else {
				return Response.status(500).entity("").build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(e.toString()).build();
		}
	}
	
	@DELETE
	@Path("/{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBook(@PathParam("isbn") String isbn) {
		DeleteBookCommand delete = new DeleteBookCommand();
		if(delete.execute(isbn)){
			return Response.status(200).entity(isbn).build();
		} else {
			return Response.status(500).entity("Error: Could not delete "+isbn).build();
		}
	}
		

}
