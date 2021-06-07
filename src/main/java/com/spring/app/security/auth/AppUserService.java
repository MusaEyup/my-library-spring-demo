package com.spring.app.security.auth;

import com.spring.app.entities.User;
import com.spring.app.results.DataResult;
import com.spring.app.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public AppUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        DataResult<User> data = userService.getUserByUsername(username);
        User user = data.getData();
        if(user != null){
            return new AppUserDetails(user);
        } else {
            throw new UsernameNotFoundException(data.getMessage());
        }
    }
}
