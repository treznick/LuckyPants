package com.luckypants.command;

import java.util.ArrayList;

import com.luckypants.mongo.BooksConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class FindBooksCommand {
	public ArrayList<DBObject> execute(String key, String value){
		BooksConnectionProvider booksConn = new BooksConnectionProvider();
		DBCollection booksCollection = booksConn.getCollection();
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put(key, value);
		
		DBCursor cursor = booksCollection.find(searchQuery);
		
		ArrayList<DBObject> books = new ArrayList<DBObject>();
		try {
			while(cursor.hasNext()) {
				books.add(cursor.next());
			}
		} finally {
			cursor.close();
		} 
		return books;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FindBooksCommand command = new FindBooksCommand();
		ArrayList<DBObject> list = command.execute("ISBN","1234");
		System.out.println(list);
		list = command.execute("title", "Super Pants");
		System.out.println(list);
	}

}
