package com.luckypants.command;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;

import com.luckypants.model.Author;
import com.luckypants.mongo.ConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class GetAuthorCommand {
	ObjectMapper mapper = new ObjectMapper();
	
	public Author execute(String key, String value) {
		ConnectionProvider conn = new ConnectionProvider();
		DBCollection authorsCollection = conn.getCollection("authors");

		BasicDBObject searchQuery = new BasicDBObject();
		if (key.equals("_id")) {
			searchQuery.put(key, new ObjectId(value));
		} else {
			searchQuery.put(key, value);
		}

		DBObject author = authorsCollection.findOne(searchQuery);

		Author vAuthor = null;
		try {
			vAuthor = mapper.readValue(author.toString(), Author.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vAuthor;
	}

	public static void main(String[] args) {
		GetAuthorCommand command = new GetAuthorCommand();

		Author a = command.execute("fname", "John");
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(a));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}