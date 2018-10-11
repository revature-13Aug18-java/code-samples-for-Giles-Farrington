package com.bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.bank.pojos.Customer;
import com.bank.util.ConnectionFactory;


public class CustomerDao implements Dao<Customer, Integer, String> {

	//STATEMENT
	public List<Customer> getAll(Integer id) {
		List<Customer> customers = new ArrayList<Customer>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from customer order by username asc";
			
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				Customer temp = new Customer();
				temp.setCustomer_id(rs.getInt("customer_id"));
				temp.setUsername(rs.getString("username"));
				temp.setPassword(rs.getString("password"));
				temp.setFirstname(rs.getString("firstname"));
				temp.setLastname(rs.getString("lastname"));
				customers.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}
	//PREPARED STATEMENT
	public Customer getOne(Integer id) {
		Customer c = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from customer where customer_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id); //The 1 represents the ? in String sql
			ResultSet info = ps.executeQuery();
			while(info.next()) {
				c = new Customer();
				c.setCustomer_id(info.getInt(1));
				c.setUsername(info.getString(2));
				c.setPassword(info.getString(3));
				c.setFirstname(info.getString(4));
				c.setLastname(info.getString(5));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	//CALLABLE STATEMENT
	public Customer getOneFrmStrng(String un) {
		Customer c = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "call getCustomerByUsername(?,?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, un); //The 1 represents the ? in String sql
			cs.registerOutParameter(2, java.sql.Types.INTEGER);
			cs.registerOutParameter(3, java.sql.Types.VARCHAR);
			cs.registerOutParameter(4, java.sql.Types.VARCHAR);
			cs.registerOutParameter(5, java.sql.Types.VARCHAR);
			cs.registerOutParameter(6, java.sql.Types.VARCHAR);
			
			
			cs.executeQuery();
				c = new Customer();
				c.setCustomer_id(cs.getInt(2));
				c.setUsername(cs.getString(3));
				c.setPassword(cs.getString(4));
				c.setFirstname(cs.getString(5));
				c.setLastname(cs.getString(6));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public Customer saveNewAccount(Customer obj) {
		try(Connection conn = ConnectionFactory.getInstance()
				.getConnection()){
			/*
			 * connections automatically commit after tx is 
			 * complete/right before connection closes
			 * set to false to do some sort of validation
			 * before committing
			 */
			conn.setAutoCommit(false);
			String sql = "insert into Customer values(null, ?, ?, ?, ?)";
			
			//code to get back auto-generated PK (other columns can be auto generated too!)
			String[] keys = {"Customer_id"};
			PreparedStatement ps = conn.prepareStatement(sql, keys);
			ps.setString(1, obj.getUsername());
			ps.setString(2, obj.getPassword());
			ps.setString(3, obj.getFirstname());
			ps.setString(4, obj.getLastname());
			
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
	public Customer update(Customer obj) {
		try(Connection conn = ConnectionFactory.getInstance()
				.getConnection()){
			/*
			 * connections automatically commit after tx is 
			 * complete/right before connection closes
			 * set to false to do some sort of validation
			 * before committing
			 */
			conn.setAutoCommit(false);
			String sql = "update Customer set username = ?, "
					+ "password = ?, firstname = ?, lastname = ? "
					+ "where customer_id = ?";
			
			//code to get back auto-generated PK (other columns can be auto generated too!)
			String[] keys = {"Customer_id"};
			PreparedStatement ps = conn.prepareStatement(sql, keys);
			ps.setString(1, obj.getUsername());
			ps.setString(2, obj.getPassword());
			ps.setString(3, obj.getFirstname());
			ps.setString(4, obj.getLastname());
			ps.setInt(5, obj.getCustomer_id());
			
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
			String sql = "delete from customer where customer_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id); //The 1 represents the ? in String sql
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DELETE successful");
		
	}

}
