package com.luckypants.model;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;


@JsonIgnoreProperties(ignoreUnknown = true)

public class Book {
	
	@JsonIgnore private Author author;
	private String title;
	private int pages;
	private String _author_id;
	private String isbn;
	 @JsonDeserialize(as=ArrayList.class, contentAs=String.class)
	private ArrayList<String> genres;

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

	public Author getAuthor() {
		return author;
	}
	
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public int getPages() {
		return pages;
	}
	
	public void setPages(int pages) {
		this.pages = pages;
	}
	
	public ArrayList<String> getGenres() {
		return genres;
	}
	 @JsonDeserialize(as=ArrayList.class, contentAs=String.class)
	public void setGenres(ArrayList<String> genres) {
		this.genres = genres;
	}
	
	public String get_author_id() {
		return _author_id;
	}

	public void set_author_id(String _author_id) {
		this._author_id = _author_id;
	}
}
