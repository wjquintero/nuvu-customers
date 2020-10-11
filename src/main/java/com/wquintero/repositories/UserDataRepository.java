package com.wquintero.repositories;

import com.wquintero.model.User;
import com.wquintero.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData,Long> {
    Optional<UserData> findByUser(User user);
}
