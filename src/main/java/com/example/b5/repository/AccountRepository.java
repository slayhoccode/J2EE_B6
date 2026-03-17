package com.example.b5.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.b5.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT a FROM Account a JOIN FETCH a.roles WHERE a.login_name = :loginName")
    Optional<Account> findByLoginName(@Param("loginName") String loginName);
}