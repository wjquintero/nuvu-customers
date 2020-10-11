package com.wquintero.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString
public class UserData {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JsonIgnore
    private User user;

    private String firstName;
    private String secondName;
    private String lastName;
    private String secondLastName;
    private ID_TYPE idType;
    private String idNumber;
    private Integer age;
    private String phoneNumber;
    private String email;
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "userData")
    private List<Address> addresses;
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "userData")
    private CreditCard creditCard;

    public Long getId() {
        return id;
    }

    public UserData setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserData setUser(User user) {
        this.user = user;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserData setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getSecondName() {
        return secondName;
    }

    public UserData setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserData setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public UserData setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
        return this;
    }

    public ID_TYPE getIdType() {
        return idType;
    }

    public UserData setIdType(ID_TYPE idType) {
        this.idType = idType;
        return this;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public UserData setIdNumber(String idNumber) {
        this.idNumber = idNumber;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserData setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserData setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserData setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public UserData setAddresses(List<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public UserData setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    public enum ID_TYPE {
        IDENTIFICATION,
        SOCIAL_SECURITY,
        PASSPORT,
        OTHER
    }

}


