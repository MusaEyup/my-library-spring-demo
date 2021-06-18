package com.spring.app.dao;

import com.spring.app.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT A FROM Author A WHERE lower(A.firstName) = ?1 AND lower(A.lastName) = ?2")
    Author findAuthorByFullName(String firstName, String lastName);

    @Query(value = "SELECT CASE WHEN COUNT(A) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Author A WHERE lower(A.firstName) = ?1 AND lower(A.lastName) = ?2")
    Boolean isAuthorExist(String firstName, String lastName);


}
