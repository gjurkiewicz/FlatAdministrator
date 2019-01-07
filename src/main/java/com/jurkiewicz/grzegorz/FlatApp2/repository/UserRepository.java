package com.jurkiewicz.grzegorz.FlatApp2.repository;

import com.jurkiewicz.grzegorz.FlatApp2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findUserByName(String name);
//    void saveUser(User user);
//    User getActiveUser(String name);
//    boolean isUserAlreadyPresent(User user);
}
