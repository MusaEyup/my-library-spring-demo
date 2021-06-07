package com.spring.app.dao;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.app.entities.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	@Query(value = "SELECT B FROM Book B WHERE B.active = TRUE")
	List<Book> findAllBooks();

	@Query(value = "SELECT B FROM Book B WHERE B.id = :id AND B.active = TRUE")
	Book findBookById(@Param("id") Long id);
	
	@Query(value = "SELECT B FROM Book B WHERE lower(B.title) = :title AND B.active = TRUE")
	Book findBookByTitle(@Param("title") String title);




	

	
	
	
	
	
	
	
}
