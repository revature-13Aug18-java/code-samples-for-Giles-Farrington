package com.bank.service;

import java.util.List;

import com.bank.dao.CustomerDao;
import com.bank.dao.Dao;
import com.bank.pojos.Customer;

public class CustomerService {
	
	
	static Dao<Customer, Integer, String> cDao = new CustomerDao();
	
	
	public List<Customer> getAll(){
		return cDao.getAll(0);
	}
	
	public void save(Customer c){
		cDao.saveNewAccount(c);
	}
	
	public Customer getOne(int id) {
		return cDao.getOne(id);
	}
	
	public Customer getFromString(String un) {
		return cDao.getOneFrmStrng(un);
	}
	
	public Customer update(Customer c) {
		return cDao.update(c);
	}
	
	public void delete(int id) {
		cDao.delete(id);
	}
	
}
