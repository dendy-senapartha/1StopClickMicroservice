package com.microservice.auth.service;

import com.microservice.auth.dao.UserDao;
import com.microservice.auth.model.Role;
import com.microservice.auth.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
@Service(value = "userService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User name not found : " + username);
        }
        Set grantedAuthorities = getAuthorities(user.get());
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(),
                user.get().getPassword(), grantedAuthorities);
    }

    private Set getAuthorities(User user) {
        Set<Role> roleByUserId = user.getRoles();
        final Set authorities = roleByUserId.stream().map(
                role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toString().toUpperCase()))
                .collect(Collectors.toSet()
                );
        return authorities;
    }
}
