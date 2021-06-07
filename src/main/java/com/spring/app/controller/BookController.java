package com.spring.app.controller;

import com.spring.app.entities.Book;
import com.spring.app.results.DataResult;
import com.spring.app.business.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }



    @GetMapping(value = "/{id}")
	public DataResult<Book> getBookById(@PathVariable("id") Long id) {
		return bookService.getBookById(id);
	}


	@GetMapping(value = "/title={title}")
	public DataResult<Book> getBookByTitle(@PathVariable("title") String title) {
		return bookService.getByBookTitle(title);
	}


//	@PutMapping("/{id}")
//	public Book updateBookInfo(@RequestBody BookModel book, @PathVariable("id") Long bookId;) {
//
//		return bookService.update(book);
//	}

//	@RequestMapping(value = "books/addNewBook", method = RequestMethod.POST)
//	public Book addNewBook(BookModel book) {
//
//		 return bookService.addNewBook(book);
//	}



//	@RequestMapping(value = "books/delete", method = RequestMethod.POST)
//	public Book deleteBookById(BookModel bookModel) {
//
//		return bookService.delete(bookModel);
//	}

}
