package com.spring.app.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.app.dao.repository.BookRepository;
import com.spring.app.dao.repository.UserRepository;
import com.spring.app.entity.Book;
import com.spring.app.entity.User;
import com.spring.app.model.BookModel;
import com.spring.app.service.IBookService;

@Service("bookService")
public class BookService implements IBookService{

	@Autowired
	@Qualifier("bookRepository")
	private BookRepository bookRepository;
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Override
	public List<Book> getAll() {
		
		return  (List<Book>) bookRepository.findAll();
	}

	@Override
	public Book getBookById(Long id) {
		
		return bookRepository.findBookById(id);
	}

	@Override
	public Book getByBookTitle(String title) {
		
		return bookRepository.findBookByTitle(title);
	}
	
	@Override
	@Transactional
	public Book update(BookModel bookModel) {


		User user = userRepository.findUserById(Long.valueOf(bookModel.getUserNo()));
		List<Book> bookList = user.getBookList();
		Book updatedBook = new Book();

		for (int i = 0; i < bookList.size(); i++) {

			if(bookList.get(i).getBookNo().equals(Long.valueOf(bookModel.getBookNo()))) {
				updatedBook.setBookNo(Long.valueOf(bookModel.getBookNo()));
				updatedBook.setTitle(bookModel.getTitle());
				updatedBook.setAuthor(bookModel.getAuthor());
				updatedBook.setDescription(bookModel.getDescription());
				updatedBook.setNote(bookModel.getNote());
				updatedBook.setUser(bookList.get(i).getUser());
				break;
			}
		}
		/*for (Book book : bookList) {
			if(book.getTitle().equals(updatedBook.getTitle()))

				return null;
		}*/
		bookRepository.save(updatedBook);
		return updatedBook;

	}
		
		
	
	
	@Override
	@Transactional
	public Book addNewBook(BookModel book) {
		User user = userRepository.findUserById(Long.valueOf(book.getUserNo()));
		List<Book> bookList = user.getBookList();
		for (Book book2 : bookList) {
			if(book2.getTitle().equals(book.getTitle()))
				return null;
		}
			Long id = (bookRepository.findMaxId()==null) ?  1l : bookRepository.findMaxId() + 1;
			Book newBook = new Book(
					id,
					book.getTitle(),
					book.getAuthor(),
					book.getDescription(),
					book.getNote()
					);
			
			
			
			
			bookList.add(newBook);
			
			user.setBookList(bookList);
			newBook.setUser(user);
			bookRepository.save(newBook);
			userRepository.save(user);
			return newBook;
		
	}

	@Override
	public Book delete(BookModel bookModel) {
		Book book = bookRepository.findBookById(Long.valueOf(bookModel.getBookNo()));
		bookRepository.deleteById(Long.valueOf(bookModel.getBookNo()));
		bookRepository.delete(book);
		return book;
	}
	
}




	