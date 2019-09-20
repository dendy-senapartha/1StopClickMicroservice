package com.microservice.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/*
 * Created by dendy-prtha on 01/03/2019.
 * User table entity
 */

@Entity
@Table(name = "users")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "email_verified")
    private Boolean emailVerified = false;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "provider")
    private String provider;

    @Column(name = "provider_id")
    private String providerId;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties("user")
    private UserProfile userProfile;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String email, boolean emailVerified, String password,
                String provider, String providerId) {
        this.id = id;
        this.email = email;
        this.emailVerified = emailVerified;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        //username is an email
        return getEmail();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        role.getUsers().remove(this);
        roles.remove(role);
    }

    public void removeAllRole() {
        for(Role role : roles)
        {
            role.getUsers().remove(this);
            //roles.remove(role);
        }
        roles.clear();
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", password=" + password + "]";
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void addUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        userProfile.setUser(this);
    }

    public void removeUserProfile(UserProfile userProfile) {
        if (userProfile != null) {
            userProfile.setUser(null);
        }
        this.userProfile = null;
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
