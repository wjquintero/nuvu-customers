package com.wquintero.model;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private boolean active;
    private String roles;
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "user")
    private UserData userData;
    private Date creationDate;
    private Date updatedDate;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public User setActive(boolean active) {
        this.active = active;
        return this;
    }

    public String getRoles() {
        return roles;
    }

    public User setRoles(String roles) {
        this.roles = roles;
        return this;
    }

    public UserData getUserData() {
        return userData;
    }

    public User setUserData(UserData userData) {
        this.userData = userData;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public User setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public User setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public User(String username, String password, String roles, boolean active) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = active;
    }

    public enum ID_TYPE {
        IDENTIFICATION,
        SOCIAL_SECURITY,
        PASSPORT,
        OTHER
    }

}


