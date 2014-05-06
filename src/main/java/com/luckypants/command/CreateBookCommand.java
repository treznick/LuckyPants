package com.luckypants.command;

import org.codehaus.jackson.map.ObjectMapper;

import com.luckypants.model.Book;
import com.luckypants.mongo.BooksConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import java.util.ArrayList;

public class CreateBookCommand {
	
	public boolean execute(Book book) {
		BooksConnectionProvider booksConn = new BooksConnectionProvider();
		DBCollection booksCollection = booksConn.getCollection();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			DBObject dbObject = (DBObject) JSON.parse(mapper
					.writeValueAsString(book));
			booksCollection.insert(dbObject);
		} catch (Exception e) {
			System.out.println("Error during mapping book to Mongo Object");
			return false;
		}
		
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateBookCommand create = new CreateBookCommand();
		Book book = new Book();
		ArrayList<String> authors = new ArrayList<String>();
		authors.add("John Doe");
		authors.add("Jane Doe");
		book.setAuthors(authors);
		book.setTitle("Book1");
		book.setPages(200);
		book.setISBN("AB1200101FR1");
		if (create.execute(book)) {
			System.out.println("Success: Book Created!");
		} else {
			System.out.println("Error: Failed to create book");
		}
	}

}
