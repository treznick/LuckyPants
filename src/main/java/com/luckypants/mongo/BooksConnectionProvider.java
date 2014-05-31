package com.luckypants.mongo;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.luckypants.properties.PropertiesLookup;

public class BooksConnectionProvider {
	/**
	 * TODO:modify this method to allow passing the collection name to it
	 * @return
	 */
	
	public DBCollection getCollection() {
		try {
			PropertiesLookup pl = new PropertiesLookup();
			MongoClient mongo = new MongoClient(pl.getProperty("mongodbURL"), 
					Integer.parseInt(pl.getProperty("mongodbPORT")));
			
			DB db = mongo.getDB(pl.getProperty("mongodbDBNAME"));
			if (db == null) {
				System.out.println("Could not connect to DB");
			}
			
			boolean auth = db.authenticate(
					pl.getProperty("mongodbUSER"),
					pl.getProperty("mongodbPW").toCharArray() );
			if (auth == false) {
				System.out.println("Could not authenticate");
			}
			
			DBCollection booksColl = db.getCollection(
					pl.getProperty("mongodbCOLLECTION"));
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
