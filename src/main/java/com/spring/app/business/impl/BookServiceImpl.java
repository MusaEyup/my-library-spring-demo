package com.spring.app.business.impl;

import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import com.spring.app.business.services.BookService;
import com.spring.app.dto.BookDto;
import com.spring.app.results.DataResult;
import com.spring.app.results.ErrorDataResult;
import com.spring.app.results.Result;
import com.spring.app.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.app.dao.BookRepository;
import com.spring.app.dao.UserRepository;
import com.spring.app.entities.Book;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final UserRepository userRepository;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
	}



	@Override
	public DataResult<List<Book>> getAllBooks() {

		List<Book> books = bookRepository.findAll();
		if(books != null){
			return new SuccessDataResult<>(books);
		}
		return new ErrorDataResult<>("Not found");
	}


	@Override
	public DataResult<Book> getBookById(Long id) {
		Book book = bookRepository.findBookById(id);
		if(book != null){
			return new SuccessDataResult<>(book);
		}
		return new ErrorDataResult<>(String.format("Book with id '%s' not found",id));
	}

	@Override
	public DataResult<Book> getByBookTitle(String title) {

		Book book = bookRepository.findBookByTitle(title.toLowerCase());
		if(book != null){
			return new SuccessDataResult<>(book);
		}
		return new ErrorDataResult<>(String.format("Book with title: '%s' not found",title));
	}

	@Override
	public DataResult<Book> addNewBook(BookDto bookModel) {
		return null;
	}

	@Override
	@Transactional
	public Result update(BookDto bookModel) {


		/*User user = userRepository.findUserById(Long.valueOf(bookModel.getUserNo()));
		List<Book> bookList = user.getBookList();
		Book updatedBook = new Book();

		for (int i = 0; i < bookList.size(); i++) {

			if(bookList.get(i).getId().equals(Long.valueOf(bookModel.getBookNo()))) {
				updatedBook.setId(Long.valueOf(bookModel.getBookNo()));
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
		}//
		bookRepository.save(updatedBook);
		return updatedBook;
*/		return null;
	}

	@Override
	public Result delete(BookDto bookModel) {
		return null;
	}


//	@Override
//	@Transactional
//	public DataResult<Book> addNewBook(BookModel bookModel) {
//		/*User user = userRepository.findUserById(Long.valueOf(book.getUserNo()));
//		List<Book> bookList = user.getBookList();
//		for (Book book2 : bookList) {
//			if(book2.getTitle().equals(book.getTitle()))
//				return null;
//		}
//			/*Long id = (bookRepository.findMaxId()==null) ?  1l : bookRepository.findMaxId() + 1;
//			Book newBook = new Book(
//					id,
//					book.getTitle(),
//					book.getAuthor(),
//					book.getDescription(),
//					book.getNote()
//					);
//
//
//
//
//			bookList.add(newBook);
//
//			user.setBookList(bookList);
//			newBook.setUser(user);
//			bookRepository.save(newBook);
//			userRepository.save(user);
//			return newBook;*/
//		return null;
//
//	}

//	@Override
//	public Result update(BookModel bookModel) {
//		return null;
//	}
//
//	@Override
//	public Result delete(BookModel bookModel) {
//		Book book = bookRepository.findBookById(Long.valueOf(bookModel.getBookNo()));
//		bookRepository.deleteById(Long.valueOf(bookModel.getBookNo()));
//		bookRepository.delete(book);
//		return null;
//	}
	
}




	
