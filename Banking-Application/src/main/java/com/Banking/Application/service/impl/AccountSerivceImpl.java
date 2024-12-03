package com.Banking.Application.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Banking.Application.dto.AccountDto;
import com.Banking.Application.entity.Account;
import com.Banking.Application.mapper.AccountMapper;
import com.Banking.Application.repository.AccountRepository;
import com.Banking.Application.service.AccountService;

@Service
public class AccountSerivceImpl implements AccountService {

	private AccountRepository accountRepository;

	public AccountSerivceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountdto) {

		Account account = AccountMapper.mapToAccount(accountdto);
		Account savedAccount = accountRepository.save(account);

		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));

		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));

		double totalBalance = account.getBalance() + amount;
		account.setBalance(totalBalance);

		Account savedAccount = accountRepository.save(account);

		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {

		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));

		if (account.getBalance() < amount) {
			throw new RuntimeException("Insufficient Balance");

		}

		double totalBalance = account.getBalance() - amount;
		account.setBalance(totalBalance);
		Account savedAccount = accountRepository.save(account);

		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		
	return accountRepository.findAll().stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
		
	}

	@Override
	public void deleteAccount(Long id) {
			
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Account does not exist"));
		
		
		accountRepository.delete(account);
	}

}
