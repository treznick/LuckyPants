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







import com.luckypants.model.Book;

@Path("/books")
public class BookService {
	Book book1 = new Book();
	
	@GET
	@Path("/")
	public Response getBook() {
		String response = "Title: "+book1.getTitle()+" "+"Authors: "+book1.serializeAuthors();
		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getName(@PathParam("username") String userName) {
		String response = "Hello " + userName;
		return Response.status(200).entity(response).build();
	}
	
	@POST
	@Path("/")
	@Consumes("application/x-www-form-urlencoded")
	public Response setBook(@FormParam("title") String title, @FormParam("authors") String authors) {
		String response = new String();
		
		String [] authorArray = authors.split(";");
		book1.setTitle(title);
		
		for(String s : authorArray) {
			try {
				book1.addAuthor(s);
				response = "Title: " + book1.getTitle() + " " + "Author: " + book1.serializeAuthors();
			} catch (IllegalArgumentException e) {
				System.out.println(e);
				response = "Error, too many authors added!";
			}
		}

		return Response.status(200).entity(response).build();
	}
}
