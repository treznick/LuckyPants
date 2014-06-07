package com.luckypants.command;

import java.util.ArrayList;

import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.luckypants.model.Book;

public class ListAllBooksCommand {
	
	public ArrayList<Book> execute(){
		ConnectionProvider booksConn = new ConnectionProvider();
		DBCollection booksCollection = booksConn.getCollection("books");
		
		DBCursor cursor = booksCollection.find();
		
		ArrayList<Book> books = new ArrayList<Book>();
		GetBookCommand getBook = new GetBookCommand();
		try {
		   while(cursor.hasNext()) {
			   Book b = getBook.execute("_id", cursor.next().get("_id").toString());
			   books.add(b);
		   }
		} finally {
		   cursor.close();
		}
		return books;
		
	}
	public static void main(String[] args) {
		ListAllBooksCommand listBooks = new ListAllBooksCommand();
		ArrayList<Book> list = listBooks.execute();
		System.out.println(list);

	}
}
