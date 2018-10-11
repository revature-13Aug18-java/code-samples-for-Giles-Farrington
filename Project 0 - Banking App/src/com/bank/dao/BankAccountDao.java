package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bank.pojos.BankAccount;
import com.bank.util.ConnectionFactory;

public class BankAccountDao implements Dao<BankAccount, Integer, String> {

	@Override
	public List<BankAccount> getAll(Integer id) {
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from bank_account where customer_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id); //The 1 represents the ? in String sql
			ResultSet info = ps.executeQuery();
			while(info.next()) {
				BankAccount b = new BankAccount();
				b = new BankAccount();
				b.setAccount_id(info.getInt(1));
				b.setAccount_type(info.getString(2));
				b.setAccount_name(info.getString(3));
				b.setBalance(info.getDouble(4));
				b.setCustomer_id(info.getInt(5));
				bankAccounts.add(b);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return bankAccounts;
	}

	public BankAccount getOne(Integer id) {
		BankAccount b = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from bank_account where account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id); //The 1 represents the ? in String sql
			ResultSet info = ps.executeQuery();
			while(info.next()) {
				b = new BankAccount();
				b.setAccount_id(info.getInt(1));
				b.setAccount_type(info.getString(2));
				b.setAccount_name(info.getString(3));
				b.setBalance(info.getDouble(4));
				b.setCustomer_id(info.getInt(5));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public BankAccount getOneFrmStrng(String un) {
		BankAccount b = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from bank_account where account_name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, un); //The 1 represents the ? in String sql
			ResultSet info = ps.executeQuery();
			while(info.next()) {
				b = new BankAccount();
				b.setAccount_id(info.getInt(1));
				b.setAccount_type(info.getString(2));
				b.setAccount_name(info.getString(3));
				b.setBalance(info.getDouble(4));
				b.setCustomer_id(info.getInt(5));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public BankAccount saveNewAccount(BankAccount obj) {
		try(Connection conn = ConnectionFactory.getInstance()
				.getConnection()){
			/*
			 * connections automatically commit after tx is 
			 * complete/right before connection closes
			 * set to false to do some sort of validation
			 * before committing
			 */
			conn.setAutoCommit(false);
			String sql = "insert into Bank_account values(null, ?, ?, ?, ?)";
			
			//code to get back auto-generated PK (other columns can be auto generated too!)
			String[] keys = {"Customer_id"};
			PreparedStatement ps = conn.prepareStatement(sql, keys);
			ps.setString(1, obj.getAccount_type());
			ps.setString(2, obj.getAccount_name());
			ps.setDouble(3, obj.getBalance());
			ps.setInt(4, obj.getCustomer_id());
			
			//UPDATES return num rows added/updated/deleted
			//QUERIES return result sets
			int numRowsAffected = ps.executeUpdate();
			if(numRowsAffected>0) {
				ResultSet pk = ps.getGeneratedKeys();
				while(pk.next()) {
					obj.setCustomer_id(pk.getInt(1));
				}
				
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public BankAccount update(BankAccount obj) {
		try(Connection conn = ConnectionFactory.getInstance()
				.getConnection()){
			/*
			 * connections automatically commit after tx is 
			 * complete/right before connection closes
			 * set to false to do some sort of validation
			 * before committing
			 */
			conn.setAutoCommit(false);
			String sql = "update Bank_account set account_type = ?, "
					+ "account_name = ?, "
					+ "balance = ?, customer_id = ? "
					+ "where account_id = ?";
			
			//code to get back auto-generated PK (other columns can be auto generated too!)
			String[] keys = {"Account_id"};
			PreparedStatement ps = conn.prepareStatement(sql, keys);
			ps.setString(1, obj.getAccount_type());
			ps.setString(2, obj.getAccount_name());
			ps.setDouble(3, obj.getBalance());
			ps.setInt(4, obj.getCustomer_id());
			ps.setInt(5, obj.getAccount_id());
			
			//UPDATES return num rows added/updated/deleted
			//QUERIES return result sets
			int numRowsAffected = ps.executeUpdate();
			if(numRowsAffected>0) {
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public void delete(Integer id) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "delete from bank_account where account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id); //The 1 represents the ? in String sql
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DELETE successful");
		
	}

}


