package com.spring.app.business.impl;

import com.spring.app.business.services.AuthorService;
import com.spring.app.dao.AuthorRepository;
import com.spring.app.dto.AuthorDto;
import com.spring.app.entities.Author;
import com.spring.app.results.DataResult;
import com.spring.app.results.ErrorDataResult;
import com.spring.app.results.SuccessDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public DataResult<Author> getAuthorByFullName(String authorFullName) {

        AuthorDto authorDto = getAuthorDto(authorFullName);
        Author author = authorRepository.findAuthorByFullName(authorDto.getFirstName().toLowerCase(), authorDto.getLastName().toLowerCase());
        if(author != null){
            return new SuccessDataResult<>(author);
        }
        return new ErrorDataResult<>("Author not found");
    }

    @Override
    public DataResult<Author> addNewAuthor(String authorFullName) {

        if(authorFullName.length() <= 0){
            return new ErrorDataResult<>("authorFullName is empty!");
        }
        AuthorDto authorDto = getAuthorDto(authorFullName);

        if(isAuthorExist(authorDto)){
            Author existAuthor = authorRepository.findAuthorByFullName(
                    authorDto.getLastName().toLowerCase(),
                    authorDto.getLastName().toLowerCase()
            );
            return new ErrorDataResult<>(existAuthor, String.format("Author with name %s is already exist", authorFullName));
        }


        Author author = new Author(
                authorDto.getFirstName(),
                authorDto.getLastName(),
                true,
                LocalDateTime.now()
        );

        author = authorRepository.save(author);
        return new SuccessDataResult<>(author, String.format("Author with name %s added successfully", authorFullName));

    }

    private AuthorDto getAuthorDto(String fullName){

        AuthorDto authorDto ;

        String[] name = fullName.split(" ");
        authorDto = new AuthorDto();
        for (int i = 0; i < name.length; i++) {

            if(i == 0)
                authorDto.setFirstName(name[i]);
            authorDto.setLastName(authorDto.getLastName().concat(" " + name[i]));
        }

        return authorDto;
    }

    private boolean isAuthorExist(AuthorDto author){
        return authorRepository.isAuthorExist(author.getFirstName().toLowerCase(), author.getLastName().toLowerCase());
    }

}
