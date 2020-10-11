package com.wquintero.controllers;

import com.wquintero.model.CreditCard;
import com.wquintero.model.ResponseMsg;
import com.wquintero.model.User;
import com.wquintero.services.CreditCardService;
import com.wquintero.services.UserDataService;
import com.wquintero.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
public class CreditCardController {

    @Autowired
    UserService userService;

    @Autowired
    UserDataService userDataService;

    @Autowired
    CreditCardService creditCardService;

    @PostMapping(path = "/private/user/cc/assign")
    public ResponseEntity assign(Authentication authentication, @RequestBody CreditCard creditCard) {
        //System.out.println(creditCard);
        if (creditCard==null) {
            return new ResponseEntity(new ResponseMsg(-200,"Missing credit card information"), HttpStatus.NOT_FOUND);
        }
        if (!validateCreditCard(creditCard)) {
            return new ResponseEntity(new ResponseMsg(-201,"Missing information for credit card assignment " +
                    "(all fields are required)"), HttpStatus.NOT_FOUND);
        }
        User user = userService.findByUserName(authentication.getName());
        if (user.getUserData().getCreditCard() != null) {
            return new ResponseEntity(new ResponseMsg(-202,"User already have a credit card assigned"), HttpStatus.NOT_FOUND);
        }
        if (creditCardService.findByNumber(creditCard.getNumber())!=null) {
            return new ResponseEntity(new ResponseMsg(-203,"Credit card already assigned to another user"), HttpStatus.NOT_FOUND);
        }
        try {
            creditCard.setUserData(user.getUserData());
            creditCardService.save(creditCard);
            return new ResponseEntity(new ResponseMsg(200,"Credit card assigned to user"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(new ResponseMsg(-204,"Error assigning credit card - errMsg: " + ex.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/private/user/cc/update")
    public ResponseEntity udateCreditCard(Authentication authentication, @RequestBody CreditCard creditCard) {
        //System.out.println(creditCard);
        if (creditCard==null) {
            return new ResponseEntity(new ResponseMsg(-205,"Missing user information for credit card " +
                    "updating (all fields are required"), HttpStatus.NOT_FOUND);
        }
        CreditCard curCC = userService.findByUserName(authentication.getName()).getUserData().getCreditCard();
        if (curCC==null) {
            return new ResponseEntity(new ResponseMsg(-206,"User does not have a credit card assigned"
            ), HttpStatus.NOT_FOUND);
        }
        if (!validateCreditCard(creditCard)) {
            return new ResponseEntity(new ResponseMsg(-205,"Missing user information for credit card " +
                    "updating (all fields are required"), HttpStatus.NOT_FOUND);
        }
        CreditCard cc = creditCardService.findByNumber(creditCard.getNumber());
        if (cc!=null && !cc.getUserData().getUser().getUsername().equals(authentication.getName())) {
            return new ResponseEntity(new ResponseMsg(-203,"Credit card already assigned to another user"), HttpStatus.NOT_FOUND);
        }
        try {
            mergeCreditCard(curCC, creditCard);
            creditCardService.update(curCC);
            return new ResponseEntity(new ResponseMsg(201,"Credit card updated"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(new ResponseMsg(-207,"EError updating credit card - errMsg: " + ex.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/private/user/cc/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity getCreditCards(){
        List<CreditCard> creditCards = creditCardService.findAll();
        if (creditCards == null) {
            return new ResponseEntity(new ResponseMsg(-200,"Credit cards not found."), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(creditCards, HttpStatus.OK);
        }
    }

    private CreditCard mergeCreditCard(CreditCard currentCC, CreditCard newCC) {
        if (currentCC == null || newCC == null) return currentCC;
        currentCC.setNumber(newCC.getNumber())
                .setExpDate(newCC.getExpDate())
                .setStatus(newCC.getStatus())
                .setCvv(newCC.getCvv());
        currentCC.setUpdatedDate(new Date());
        return currentCC;
    }

    private boolean validateCreditCard(CreditCard creditCard) {
        if (creditCard == null ||
                creditCard.getNumber()==null || creditCard.getExpDate()==null ||
                creditCard.getCvv()==null) {
            return false;
        }
        return true;
    }

}
