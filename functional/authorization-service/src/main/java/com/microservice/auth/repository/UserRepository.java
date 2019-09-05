package com.microservice.auth.repository;

import com.microservice.auth.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String>{
}
