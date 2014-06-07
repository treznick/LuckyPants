package com.luckypants.command;

import java.util.ArrayList;

import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.luckypants.model.Author;

public class ListAllAuthorsCommand {
	
	public ArrayList<Author> execute(){
		ConnectionProvider authorsConn = new ConnectionProvider();
		DBCollection authorsCollection = authorsConn.getCollection("authors");
		
		DBCursor cursor = authorsCollection.find();
		
		ArrayList<Author> authors = new ArrayList<Author>();
		GetAuthorCommand getAuthor = new GetAuthorCommand();
		try {
		   while(cursor.hasNext()) {
			   Author a = getAuthor.execute("_id", cursor.next().get("_id").toString());
			   authors.add(a);
		   }
		} finally {
		   cursor.close();
		}
		return authors;
		
	}
	public static void main(String[] args) {
		ListAllAuthorsCommand listAuthors = new ListAllAuthorsCommand();
		ArrayList<Author> list = listAuthors.execute();
		System.out.println(list);

	}
}