package com.demoproject.usermanagement.user;



public interface UserService {
    void saveUser(UserDTO userDto);
    User findUserByEmail(String email);
}