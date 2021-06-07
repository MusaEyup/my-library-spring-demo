package com.spring.app.business.services;

import com.spring.app.dto.BookDto;
import com.spring.app.dto.UserDto;
import com.spring.app.entities.BookUserDetails;
import com.spring.app.results.DataResult;
import com.spring.app.results.Result;


import java.util.List;

public interface BookUsersDetailsService {
    DataResult<List<BookDto>> getBookListByUserId(Long userId);
    DataResult<List<UserDto>> getUserListByBookId(Long bookId);
    DataResult<List<BookUserDetails>> getAll();
    DataResult<BookDto> addBookToUserLibrary(BookDto bookDto, Long userId);


}
