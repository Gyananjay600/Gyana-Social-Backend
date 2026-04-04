package com.gyana.Gyana_Social.repository;

import com.gyana.Gyana_Social.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findAllById(Integer id);// this repo for the user class and the unique identifier of the class is integer

    public User findByEmail(String email);

    @Query("select u from User u where u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
    public List<User>searchUser(@Param("query") String query);


}
