package com.spring.app.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.app.entity.Book;
import com.spring.app.entity.User;
import com.spring.app.model.BookModel;
import com.spring.app.model.UserModel;
import com.spring.app.service.IBookService;
import com.spring.app.service.IUserService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
	
	@Autowired
	private IBookService bookService;
	
	@Autowired
	private IUserService userService;
	
	

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public List<Book> getBooks(){
		return bookService.getAll();
	}
	

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getAllUsers(){
		return userService.getAll();
	}
	

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable("id") Long id){
		return userService.getUserById(id);
	}
	

	@GetMapping(value = "/books/{id}")
	public Book getBookById(@PathVariable("id") Long id){
		return bookService.getBookById(id);
	}
	

	@GetMapping(value = "/users/name/{keyword}")
	public User getUserByName(@PathVariable("keyword") String name){
		return userService.getByName(name);
	}
	
	@GetMapping(value = "books/title/{keyword}")
	public Book getBookByTitle(@PathVariable("keyword") String title) {
		
		return bookService.getByBookTitle(title);
	}
	@GetMapping(value = "users/get/{keyword}")
	public User getUserByUserName(@PathVariable("keyword") String userName) {
		return userService.getByUserName(userName);
	}
	
	@RequestMapping(value = "books/update", method = RequestMethod.POST)
	public Book updateBookInfo(BookModel book) {
		
		return bookService.update(book);
	}
	
	@RequestMapping(value = "books/addNewBook", method = RequestMethod.POST)
	public Book addNewBook(BookModel book) {
		
		 return bookService.addNewBook(book);
	}
	
	@RequestMapping(value = "users/createNewAccount", method = RequestMethod.POST)
	public User createNewAccount(UserModel user) {
		
		return userService.createNewAccount(user);
	}
	
	@RequestMapping(value = "books/delete", method = RequestMethod.POST)
	public Book deleteBookById(BookModel bookModel) {
		
		return bookService.delete(bookModel);
	}
	
	
	
	
}
