package com.wquintero.services;

import com.wquintero.model.CreditCard;
import com.wquintero.model.UserData;
import com.wquintero.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository rp;

    public void save(CreditCard creditCard){
        creditCard.setCreationDate(new Date());
        rp.save(creditCard);
    }

    public void save(List<CreditCard> creditCards){
        rp.saveAll(creditCards);
    }

    public void update(CreditCard creditCard) {
        creditCard.setUpdatedDate(new Date());
        rp.save(creditCard);
    }

    public CreditCard findByUserData(UserData userData) {
        return rp.findByUserData(userData).orElse(null);
    }

    public CreditCard findByNumber(String number) {
        return rp.findByNumber(number).orElse(null);
    }

    public List<CreditCard> findAll() {
        return rp.findAll();
    }
}
