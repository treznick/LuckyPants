package com.luckypants.command;

import java.util.ArrayList;

import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class ListAllAuthorsCommand {
	
	public ArrayList<DBObject> execute(){
		ConnectionProvider authorsConn = new ConnectionProvider();
		DBCollection authorsCollection = authorsConn.getCollection("authors");
		
		DBCursor cursor = authorsCollection.find();
		
		ArrayList<DBObject> authors = new ArrayList<DBObject>();
		try {
		   while(cursor.hasNext()) {
			   authors.add(cursor.next());
		   }
		} finally {
		   cursor.close();
		}
		return authors;
		
	}
	public static void main(String[] args) {
		ListAllAuthorsCommand listAuthors = new ListAllAuthorsCommand();
		ArrayList<DBObject> list = listAuthors.execute();
		System.out.println(list);

	}
}