package com.microservice.auth.repository;

import com.microservice.auth.domain.UserProfile;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserProfileRepository extends PagingAndSortingRepository<UserProfile, String>{
}
