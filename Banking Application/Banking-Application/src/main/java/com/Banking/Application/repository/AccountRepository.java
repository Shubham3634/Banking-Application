package com.Banking.Application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Banking.Application.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
