package com.spring.app.dao;

import com.spring.app.entities.Book;
import com.spring.app.entities.BookUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookUsersDetailsRepository extends JpaRepository<BookUserDetails, Long> {

    @Query(value = "SELECT D FROM BookUserDetails D WHERE D.user.id = ?1")
    List<BookUserDetails> findBooksByUserId(Long userId);

    @Query(value = "SELECT D FROM BookUserDetails D WHERE D.user.id = :id")
    List<BookUserDetails> findBookUserDetailsByUserId(@Param("id") Long userId);

    @Query(value = "SELECT D FROM BookUserDetails D WHERE D.book.id = ?1")
    List<BookUserDetails> findUsersByBookId(Long bookId);

}
