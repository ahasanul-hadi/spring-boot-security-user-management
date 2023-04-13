package com.demoproject.usermanagement.user;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@Data
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(usernameOrEmail);
        if (user != null) {
            System.out.println("role:"+user.getRole().name());
            return new org.springframework.security.core.userdetails.User(user.getEmail()
                    , user.getPassword(),
                    Arrays.asList(user.getRole()).stream()
                            .map((role) -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                            .collect(Collectors.toList()));
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
