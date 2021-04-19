package com.spring.app.service;

import java.util.List;

import com.spring.app.entity.Book;
import com.spring.app.model.BookModel;


public interface IBookService {
	
	List<Book> getAll();
	Book getBookById(Long id);
	Book getByBookTitle(String title);
	Book addNewBook(BookModel bookModel);
	Book update(BookModel bookModel);
	Book delete(BookModel bookModel);
	
	
	
	
	
	
	
	
	

}
