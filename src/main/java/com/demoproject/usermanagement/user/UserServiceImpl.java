package com.demoproject.usermanagement.user;

import com.demoproject.usermanagement.enums.Role;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Data
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDTO userDto) {

        User user = new User(userDto.getName(), userDto.getEmail(), userDto.getMobile(), passwordEncoder.encode(userDto.getPassword()), Role.USER);
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}