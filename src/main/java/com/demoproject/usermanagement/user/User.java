package com.demoproject.usermanagement.user;

import com.demoproject.usermanagement.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "role")
    private Role role;

    public User(String name, String email, String mobile, String password, Role role) {
        this.name = name;
        this.email = email;
        this.mobile= mobile;
        this.password = password;
        this.role = role;
    }
}
