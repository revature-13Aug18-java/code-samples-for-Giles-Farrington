package com.bank.service;

import java.util.List;

import com.bank.dao.BankAccountDao;
import com.bank.dao.Dao;
import com.bank.pojos.BankAccount;


public class BankAccountService {
	
	
	static Dao<BankAccount, Integer, String> bDao = new BankAccountDao();
	
	public BankAccount saveNewAccount(BankAccount b) {
		return bDao.saveNewAccount(b);
	}
	
	public List<BankAccount> getAll(int id){
		return bDao.getAll(id);
	}
	
	public BankAccount getOne(int id) {
		return bDao.getOne(id);
	}
	
	public BankAccount getOneFrmStrn(String str) {
		return bDao.getOneFrmStrng(str);
	}
	
	public void update(BankAccount b) {
		bDao.update(b);
	}
	
	public void delete(int id) {
		bDao.delete(id);
	}
}
