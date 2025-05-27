package com.robel.bookstore.repository;

import com.robel.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUserName(String userName);

    Optional<User> findByUserNameAndUserIdNot(String userName, Long userId);

    Optional<User> findByEmailAndUserIdNot(String email, Long userId);
}
