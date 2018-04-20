package com.example.project.repository;

import com.example.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findById(int id);

    String FIND_EMAILS = "SELECT email FROM User";

    @Query(value = FIND_EMAILS, nativeQuery = true)
    List<String> findEmails();
}
