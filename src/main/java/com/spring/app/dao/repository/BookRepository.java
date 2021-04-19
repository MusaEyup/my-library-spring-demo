package com.spring.app.dao.repository;





import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.app.entity.Book;


@Repository("bookRepository")
public interface BookRepository extends CrudRepository<Book, Long>{
	
	@Query(value = "select b from Book b ")
	public List<Book> findAllBooks();

	@Query(value = "SELECT MAX(b.id) FROM Book b")
	public Long findMaxId();
	
	@Query(value = "SELECT b FROM Book b where b.bookNo = :bookNo")
	public Book findBookById(@Param("bookNo") Long id);
	
	@Query(value = "SELECT b FROM Book b WHERE b.title = :title")
	public Book findBookByTitle(@Param("title") String title);
	

	
	
	
	
	
	
	
}
