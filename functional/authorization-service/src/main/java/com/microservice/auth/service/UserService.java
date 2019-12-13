package com.microservice.auth.service;

import com.microservice.auth.dto.UserDTO;
import com.microservice.auth.dto.response.DefaultResponse;
import com.microservice.auth.model.Role;
import com.microservice.auth.model.User;
import com.microservice.auth.repository.RoleRepository;
import com.microservice.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
@Service(value = "userService")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

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

    public DefaultResponse RegisterUser(User user) {
        for (Role requestedRole : user.getRoles()) {
            Optional<Role> roleOptional = roleRepository.findById(requestedRole.getId());
            if (roleOptional.isPresent()) {
                user.addRole(roleOptional.get());
            }
        }
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            return new DefaultResponse(false, "Email already Used!", null);
        } else {
            return new DefaultResponse(true, "User saved!", userRepository.save(user));
        }
    }

    public DefaultResponse updateUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isPresent()) {
            return new DefaultResponse(true, "User Updated!", userRepository.save(user));
        } else {
            return new DefaultResponse(false, "User Not found!", null);
        }
    }

    public DefaultResponse getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            UserDTO dto = modelMapper.map(userOptional.get(), UserDTO.class);
            return new DefaultResponse(true, "Email found!", dto);
        } else {
            return new DefaultResponse(false, "Email not found!", null);
        }
    }

    public DefaultResponse getAllUser() {
        List<User> userList = userRepository.findAll();
        if (!userList.isEmpty()) {
            List<UserDTO> userDTOS = new ArrayList<>();
            for (User user : userList) {
                userDTOS.add(modelMapper.map(user, UserDTO.class));
            }
            return new DefaultResponse(true, "Users found!", userDTOS);
        } else {
            return new DefaultResponse(false, "No User found!", null);
        }
    }

    public DefaultResponse deleteUser(String usedId) {
        Optional<User> userOptional = userRepository.findById(usedId);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return new DefaultResponse(true, "Delete Success!", null);
        } else {
            return new DefaultResponse(false, "User to Delete Not found!", null);
        }
    }

}
