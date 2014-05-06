package com.luckypants.mongo;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class BooksConnectionProvider {
	/**
	 * TODO:modify this method to allow passing the collection name to it
	 * @return
	 */
	
	public DBCollection getCollection() {
		try {
			MongoClient mongo = new MongoClient("oceanic.mongohq.com", 10013);
			
			DB db = mongo.getDB("luckypants_development");
			if (db == null) {
				System.out.println("Could not connect to DB");
			}
			
			boolean auth = db.authenticate("lpuser", "&aJmXAcd%B7@&425".toCharArray() );
			if (auth == false) {
				System.out.println("Could not authenticate");
			}
			
			DBCollection booksColl = db.getCollection("books");
			return booksColl;
			
		} catch(UnknownHostException e) {
			e.printStackTrace();
		} catch(MongoException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BooksConnectionProvider books = new BooksConnectionProvider();
		DBCollection booksCollection = books.getCollection();
		if(booksCollection == null) {
			System.out.println("Error: No Connection");
		} else {
			System.out.println("Success: connected!");
		}
		

	}

}
