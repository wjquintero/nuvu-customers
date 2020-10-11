package com.wquintero;

import com.wquintero.model.User;
import com.wquintero.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class NuvuCustomersApp {
    

    @Bean
    public CommandLineRunner setupDefaultUser(UserService service) {
        return args -> {
            service.save(new User(
                    "admin",
                    "qwrty123",
                    "ROLE_ADMIN",
                    true
            ));
        };
    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }    

    public static void main(String[] args) {
        SpringApplication.run(NuvuCustomersApp.class, args);
    }

}
