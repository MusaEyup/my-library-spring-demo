package com.spring.app.model;

public class BookModel {
	
	private String userNo;
	private String bookNo;
	private String bookTitle;
	private String author;
	private String description;
	private String note;




	public BookModel() {};
	public BookModel(String userNo, String bookNo, String bookTitle, String author, String description, String note) {
		this.userNo = userNo;
		this.bookNo = bookNo;
		this.bookTitle = bookTitle;
		this.author = author;
		this.description = description;
		this.note = note;
	}
	public BookModel( String bookTitle, String author, String description, String note) {
		this.bookTitle = bookTitle;
		this.author = author;
		this.description = description;
		this.note = note;
	}

	
	public String getUserNo() { return userNo; }
	public void setUserNo(String userNo) { this.userNo = userNo; }
	public String getBookNo() {return bookNo;}
	public void setBookNo(String bookNo) { this.bookNo = bookNo; }
	public String getTitle() { return bookTitle; }
	public void setBookTitle(String title) { this.bookTitle = title; }
	public String getAuthor() { return author; }
	public void setAuthor(String author) { this.author = author; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	public String getNote() { return note; }
	public void setNote(String note) { this.note = note; }
}
