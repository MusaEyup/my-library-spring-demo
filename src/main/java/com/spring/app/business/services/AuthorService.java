package com.spring.app.business.services;

import com.spring.app.entities.Author;

public interface AuthorService {

    Author getAuthorByFullName(String firstName, String lastName);
    Author addNewAuthor(String fullName);
}
