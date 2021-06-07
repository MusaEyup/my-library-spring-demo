package com.spring.app.business.services;

import java.util.List;

import com.spring.app.dto.BookDto;
import com.spring.app.entities.Book;
import com.spring.app.results.DataResult;
import com.spring.app.results.Result;


public interface BookService {
	
	DataResult<List<Book>> getAllBooks();
	DataResult<Book> getBookById(Long id);
	DataResult<Book> getByBookTitle(String title);
	DataResult<Book> addNewBook(BookDto bookModel);
	Result update(BookDto bookModel);
	Result delete(BookDto bookModel);
	
	
	
	
	
	
	
	
	

}
