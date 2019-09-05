package com.microservice.auth.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
@Entity(name = "tbl_user")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User implements UserDetails {
	@Id
	@NotNull
	@Pattern(regexp = "^[A-Za-z0-9.]+", message = "{username.format}")
	@Size(min = 3, max = 60, message = "{username.length}")
	private String username;

	@NotNull(groups = PasswordEncoder.class)
	@Pattern(groups = PasswordEncoder.class,
			regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9`~!@#$%^&*_-]+)$",
			message = "{password.format}")
	@Size(groups = PasswordEncoder.class, min = 8, max = 60, message = "{password.length}")
	private String password;

	@Builder.Default
	@ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles().stream().map( r -> new SimpleGrantedAuthority("ROLE_" + r)).collect(Collectors.toSet());
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
