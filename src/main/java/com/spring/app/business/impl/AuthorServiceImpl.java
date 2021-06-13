package com.spring.app.business.impl;

import com.spring.app.business.services.AuthorService;
import com.spring.app.dao.AuthorRepository;
import com.spring.app.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getAuthorByFullName(String firstName, String lastName) {
        return authorRepository.findAuthorByFullName(firstName, lastName);
    }

    @Override
    public Author addNewAuthor(String fullName) {


        String[] name = fullName.split(" ");
        if(name.length > 1){
            Author author = authorRepository.findAuthorByFullName(name[0], name[1]);
            if(author == null){
                Author newAuthor = new Author(
                        name[0],
                        name[1],
                        true,
                        LocalDateTime.now()
                );
                Author save = authorRepository.save(newAuthor);
            }
        }


        return ;
    }
}
