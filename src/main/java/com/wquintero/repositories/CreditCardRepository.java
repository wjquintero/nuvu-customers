package com.wquintero.repositories;

import com.wquintero.model.CreditCard;
import com.wquintero.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {
    Optional<CreditCard> findByUserData(UserData userData);
    Optional<CreditCard> findByNumber(String number);
}
