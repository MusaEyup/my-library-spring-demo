package com.spring.app.business.services;

import com.spring.app.entities.Author;
import com.spring.app.results.DataResult;

public interface AuthorService {

    DataResult<Author> getAuthorByFullName(String fullName);
    DataResult<Author> addNewAuthor(String fullName);
}
