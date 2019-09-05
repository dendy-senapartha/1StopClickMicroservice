package com.microservice.auth.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@RequiredArgsConstructor
public class UserProfile {
	@Id
	private String username;

	@NonNull
	@Pattern(regexp = "^[A-Za-z.]+", message = "{validation.firstName.format}")
	@Size(min = 3, max = 60, message = "{validation.firstName.length}")
	private String firstName;

	@NonNull
	@Pattern(regexp = "^[A-Za-z.]+", message = "{validation.firstName.format}")
	@Size(min = 3, max = 60, message = "{validation.firstName.length}")
	private String lastName;

	@NotNull
	@NonNull
	@Email
	@Size(max = 60, message = "{validation.email.length}")
	private String email;

	@OneToOne
	@MapsId
	private User user;
}
