package com.spring.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "books")
public class Book implements Serializable{
	
	private static final long serialVersionUID = 185501327896028093L;

	@Id
	@org.springframework.data.annotation.Id
	@Column(name = "book_no")
	private Long bookNo;
	
	@Column(name = "book_title")
	private String title;
	
	@Column(name = "author_name")
	private String author;
	
	@Column(name = "_description")
	private String description;
	
	@Column(name ="note")
	private String note;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_no", nullable=false) 
	private User user;

	
	public Book() {}
	public Book(Long bookNo, String title, String author, String description, String note) {
		super();
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
		this.description = description;
		this.note = note;
	}

	public Long getBookNo() {
		return bookNo;
	}

	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bookNo == null) ? 0 : bookNo.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookNo == null) {
			if (other.bookNo != null)
				return false;
		} else if (!bookNo.equals(other.bookNo))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
