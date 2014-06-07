package com.luckypants.books;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;

import com.luckypants.command.ListAllAuthorsCommand;
import com.luckypants.model.Author;

@Path("/authors")
public class AuthorService {
	ObjectMapper mapper = new ObjectMapper();
	
//	List authors
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAuthors() {
		ListAllAuthorsCommand listAuthors = new ListAllAuthorsCommand();
		ArrayList<Author> list = listAuthors.execute();
		String authorString = null;
		try {
			authorString = mapper.writeValueAsString(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(authorString).build();
	}

}
