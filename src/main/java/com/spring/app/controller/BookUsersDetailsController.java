package com.spring.app.controller;

import com.spring.app.dto.BookDto;
import com.spring.app.dto.UserDto;
import com.spring.app.entities.BookUserDetails;
import com.spring.app.results.DataResult;
import com.spring.app.business.services.BookService;
import com.spring.app.business.services.BookUsersDetailsService;
import com.spring.app.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user_details")
public class BookUsersDetailsController {

    private final UserService userService;
    private final BookUsersDetailsService detailsService;

    @Autowired
    public BookUsersDetailsController(UserService userService, BookUsersDetailsService detailsService) {
        this.userService = userService;
        this.detailsService = detailsService;
    }


    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResult<List<BookUserDetails>> getAll(){
        return detailsService.getAll();
    }

    @GetMapping("/user_id={id}")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResult<List<BookDto>> getBookListByUserId(@PathVariable("id") Long userId){
        return detailsService.getBookListByUserId(userId);
    }

    @GetMapping("/book_id={id}")
    @PreAuthorize("hasRole('ADMIN')")
    public DataResult<List<UserDto>> getUsersListById(@PathVariable("id") Long bookId){
        return detailsService.getUserListByBookId(bookId);
    }


}
