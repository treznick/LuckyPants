package com.luckypants.model;
import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Book {
	private String title;
	private int pages;
	private ArrayList<String> authors = new ArrayList<String>();
	private String isbn;

	public String getISBN() {
		return isbn;
	}

	public void setISBN(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<String> getAuthors() {
		return authors;
	}
	
	public String authorsToStr() {
		if (authors.size() == 1) {
			return authors.get(0).toString();
		} else if (authors.size() == 2) {
			return authors.get(0) + " and " + authors.get(1);
		} else if (authors.size() > 3) {
			String returnStr = new String();
			for (int i = 0; i < authors.size() - 2; ++i) {
				returnStr += (authors.get(i) + ", ");
			}
			returnStr += "and ";
			returnStr += authors.get(authors.size() -1);
			return returnStr;
		} else {
			return "";
		}
	}
	
	public void setAuthors(ArrayList<String> authors) {
		if(authors.size() < Book.maxAuthors()) {
			for(String author : authors) {
				this.authors.add(author);
			}
		} else {
			throw new IllegalArgumentException("Too Many Authors");
		}
	}

	public void addAuthor(String author) {
		if(this.authors.size() < Book.maxAuthors()) {
			this.authors.add(author);
		} else {
			throw new IllegalArgumentException("Too Many Authors");
		}
	}
	
	public String serializeAuthors() {
		StringBuilder out = new StringBuilder();
		for(Object o: authors) {
			out.append(o.toString());
			out.append(";");
		}
		return out.toString();
	}
	
	public int getPages() {
		return pages;
	}
	
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	protected static int maxAuthors() {
		return 3;
	}

}
