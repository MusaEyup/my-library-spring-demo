package com.spring.app.dao;

import com.spring.app.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT A FROM Author A WHERE A.firstName = ?1 AND A.lastName = ?2")
    Author findAuthorByFullName(String firstName, String lastName);
}
