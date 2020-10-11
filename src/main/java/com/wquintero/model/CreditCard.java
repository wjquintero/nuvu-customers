package com.wquintero.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@NoArgsConstructor
@ToString
public class CreditCard {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JsonIgnore
    private UserData userData;

    private String number;
    private String expDate;
    private boolean status;
    private Integer cvv;
    private Date creationDate;
    private Date updatedDate;

    public Long getId() {
        return id;
    }

    public CreditCard setId(Long id) {
        this.id = id;
        return this;
    }

    public UserData getUserData() {
        return userData;
    }

    public CreditCard setUserData(UserData userData) {
        this.userData = userData;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public CreditCard setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getExpDate() {
        return expDate;
    }

    public CreditCard setExpDate(String expDate) {
        this.expDate = expDate;
        return this;
    }

    public Integer getCvv() {
        return cvv;
    }

    public CreditCard setCvv(Integer cvv) {
        this.cvv = cvv;
        return this;
    }

    public boolean getStatus() {
        return status;
    }

    public CreditCard setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public CreditCard setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public CreditCard setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

}
