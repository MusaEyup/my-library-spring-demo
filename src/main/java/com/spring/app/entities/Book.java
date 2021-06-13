package com.spring.app.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Table(name = "Books")
@NoArgsConstructor
public class Book implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String title;

	private String description;
	private boolean active;
	private LocalDateTime createDate = LocalDateTime.now();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "authors_books",
			joinColumns = {@JoinColumn(name = "book_id")},
			inverseJoinColumns = {@JoinColumn(name = "author_id")}
	)
	private Set<Author> author;

	@OneToMany(mappedBy = "book")
	private List<BookUserDetails> bookUserDetails;

	public Book(String title, String description, boolean active, LocalDateTime createDate, Set<Author> author) {
		this.title = title;
		this.description = description;
		this.active = active;
		this.createDate = createDate;
		this.author = author;
	}

	@JsonIgnore
	public List<BookUserDetails> getBookUserDetails() {
		return bookUserDetails;
	}

	public void setBookUserDetails(List<BookUserDetails> bookUserDetails) {
		this.bookUserDetails = bookUserDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long bookNo) {
		this.id = bookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Set<Author> getAuthor() {
		return author;
	}

	public void setAuthor(Set<Author> author) {
		this.author = author;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Book book = (Book) o;

		if (active != book.active) return false;
		if (!id.equals(book.id)) return false;
		if (!title.equals(book.title)) return false;
		if (!description.equals(book.description)) return false;
		if (!createDate.equals(book.createDate)) return false;
		if (!author.equals(book.author)) return false;
		return bookUserDetails.equals(book.bookUserDetails);
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + title.hashCode();
		result = 31 * result + description.hashCode();
		result = 31 * result + (active ? 1 : 0);
		result = 31 * result + createDate.hashCode();
		result = 31 * result + author.hashCode();
		result = 31 * result + bookUserDetails.hashCode();
		return result;
	}
}
