package test;

/*
 * this code uses example from 
 * http://www.mkyong.com/mongodb/java-mongodb-hello-world-example/
 */
import java.net.UnknownHostException;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * this is a test class with some sample code for you to try your Mongo HQ connection
 * Do not use this same connection, create your own account/DB and user
 * 
 */

public class MongoTest {
	public static void main(String[] args) {
		
		try {
//			Connect to Mongo
			MongoClient mongo = new MongoClient("oceanic.mongohq.com", 10013);
			
//			Connect to DB
			DB db = mongo.getDB("luckypants_development");
			if (db == null) {
				System.out.println("Could not connect to Database");
			}
			
//			Auth
			boolean auth = db.authenticate("foo", "bar".toCharArray() );
			if (auth == false) {
				System.out.println("Could not authenticate");
			}
			
//			books handler
			DBCollection booksColl = db.getCollection("books");
			
//			add a book
			BasicDBObject document = new BasicDBObject();
			document.put("title", "Lucky Pants");
			document.put("author", "John Doe");
			document.put("ISBN", "1234");
			booksColl.insert(document);
			
//			find a book by title
			
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("title", "Lucky Pants");
			
			DBCursor cursor = booksColl.find(searchQuery);
			
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
			
//			update a book
			searchQuery.put("title", "Lucky Pants");
			
			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("title", "Super Pants");
			
			BasicDBObject updateObj = new BasicDBObject();
			updateObj.put("$set", newDocument);
			
			booksColl.update(searchQuery, updateObj);
			
//			Delete a book by title
			searchQuery.put("title", "Lucky Pants");
			booksColl.remove(searchQuery);
			
//			delete all books by title
			
			searchQuery.put("title", "Lucky Pants");
			
			cursor = booksColl.find(searchQuery);
			
			while (cursor.hasNext()) {
				booksColl.remove(searchQuery);
			}
			
//			end
			System.out.println("Done");
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

}
