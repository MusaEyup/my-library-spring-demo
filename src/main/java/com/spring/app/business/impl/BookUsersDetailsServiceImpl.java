package com.spring.app.business.impl;

import com.spring.app.business.services.BookService;
import com.spring.app.business.services.UserService;
import com.spring.app.dao.BookUsersDetailsRepository;
import com.spring.app.dto.BookDto;
import com.spring.app.dto.UserDto;
import com.spring.app.entities.Author;
import com.spring.app.entities.Book;
import com.spring.app.entities.BookUserDetails;
import com.spring.app.entities.User;
import com.spring.app.results.DataResult;
import com.spring.app.results.ErrorDataResult;
import com.spring.app.results.SuccessDataResult;
import com.spring.app.business.services.BookUsersDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookUsersDetailsServiceImpl implements BookUsersDetailsService {

    private final BookUsersDetailsRepository detailsRepository;
    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public BookUsersDetailsServiceImpl(BookUsersDetailsRepository detailsRepository, BookService bookService, UserService userService) {
        this.detailsRepository = detailsRepository;
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public DataResult<List<BookDto>> getBookListByUserId(Long userId) {

        List<BookUserDetails> bookUserDetails = detailsRepository.findBooksByUserId(userId);
        if(bookUserDetails.size() > 0){

            List<BookDto> bookDtoList = bookUserDetails.stream()
                    .map(this::getBookDetails)
                    .collect(Collectors.toList());

            return new SuccessDataResult<>(bookDtoList, "Book list");
        }
        return new ErrorDataResult<>("Book list not found");
    }

    @Override
    public DataResult<List<UserDto>> getUserListByBookId(Long bookId) {

        List<BookUserDetails> bookUserDetails = detailsRepository.findUsersByBookId(bookId);
        if(bookUserDetails.size() > 0){

            List<UserDto> users = bookUserDetails.stream()
                    .map(detail -> getUserDto(detail.getUser()))
                    .collect(Collectors.toList());

            return new SuccessDataResult<>(users,"Users list");
        }
        return new ErrorDataResult<>("User list not found");
    }

//    @Override
//    public DataResult<List<BookUserDetails>> getBookUsersDetailsByUserId(Long userId) {
//
//        List<BookUserDetails> bookUserDetailsList = detailsRepository.findBookUserDetailsByUserId(userId);
//        if(bookUserDetailsList.size() > 0){
//            return new SuccessDataResult<>(bookUserDetailsList, "BookUserDetailsList successfully found");
//        }
//        return new ErrorDataResult<>("BookUserDetailsList  not found");
//    }

    @Override
    public DataResult<List<BookUserDetails>> getAll() {

        List<BookUserDetails> all = detailsRepository.findAll();
        if(all.size() > 0){
            return new SuccessDataResult<>(all);
        }
        return new ErrorDataResult<>("Details not found") ;
    }

    @Override
    public DataResult<BookDto> addBookToUserLibrary(BookDto bookDto, Long userId) {
        DataResult<Book> book = bookService.getByBookTitle(bookDto.getBookTitle().toLowerCase());

        User user = userService.loadUser(userId);
        BookUserDetails bookUserDetails = null;
        if(book.isSuccess()){

            bookUserDetails = new BookUserDetails(user, book.getData(), bookDto.getUserNotes());
            bookUserDetails = detailsRepository.save(bookUserDetails);
            BookDto bookDetails = getBookDetails(bookUserDetails);
            return new SuccessDataResult<>(bookDetails,"Completed Successfully");

        }



        return null;
    }

    private BookDto getBookDetails(BookUserDetails detail){

        BookDto dto = new BookDto();

        dto.setBookTitle(detail.getBook().getTitle());
        dto.setId(detail.getBook().getId());
        dto.setDescription(detail.getBook().getDescription());
        dto.setUserNotes(detail.getUserNotes());

        Set<String> authorSet = getAuthorDetails(detail.getBook().getAuthor());
        dto.setAuthors(authorSet);
        return dto;
    }

    private Set<String> getAuthorDetails(Set<Author> authors){
        return authors.stream()
                .map(author -> author.getFirstName() + " " + author.getLastName())
                .collect(Collectors.toSet());
    }

    private UserDto getUserDto(User user){

        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                null,
                user.isEnabled()
        );
    }
}
