package com.microservice.auth.fixture;


import com.microservice.auth.domain.Role;
import com.microservice.auth.domain.User;
import com.microservice.auth.domain.UserProfile;
import com.microservice.auth.repository.UserProfileRepository;
import com.microservice.auth.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

/**
 *
 */
@Configuration
@Profile("fixture")
@RequiredArgsConstructor
public class UserFixture implements CommandLineRunner {
	private final UserRepository userRepository;
	private final UserProfileRepository userProfileRepository;
	private final PasswordEncoder passwordEncoder;

	/**
	 * Insert user sample data.
	 * @param args not used
	 */
	@Transactional
	@Override
	public void run(String... args) {
		for (UserFixtureEnum afe : UserFixtureEnum.values()) {
			User user = User.builder()
					.username(afe.getUsername())
					.password(passwordEncoder.encode(afe.getPassword()))
					.roles(afe.getRoles())
					.build();
			user = userRepository.save(user);
			UserProfile up = UserProfile.builder()
					.firstName(afe.getFirstName())
					.lastName(afe.getLastName())
					.email(afe.getEmail())
					.build();
			up.setUser(user);
			userProfileRepository.save(up);
		}
	}

	@RequiredArgsConstructor
	@Getter
	public enum UserFixtureEnum{
		STEPHANIE_BRUNNING("stephanie.brunning", "Pa$$w0rd", "Stephanie", "Brunning", "StephanieBrunning@dayrep.com", Collections
				.singleton(Role.USER)),
		MICHAEL_BENN("michael.benn", "Pa$$w0rd", "Michael", "Benn", "MichaelBenn@armyspy.com", Collections
				.singleton(Role.USER));
		private final String username;
		private final String password;
		private final String firstName;
		private final String lastName;
		private final String email;
		private final Set<Role> roles;
	}
}
