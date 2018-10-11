package ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ers.pojos.Users;
import ers.util.ConnectionFactory;

public class UsersDao implements Dao<Users, Integer> {

	
	public List<Users> getAll() {
		List<Users> users = new ArrayList<Users>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from ers_users order by ers_username";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				Users temp = new Users();
				temp.setErs_users_id(rs.getInt("ers_users_id"));
				temp.setErs_username(rs.getString("ers_username"));
				temp.setErs_password(rs.getString("ers_password"));
				temp.setUser_first_name(rs.getString("user_first_name"));
				temp.setUser_last_name(rs.getString("user_last_name"));
				temp.setUser_email(rs.getString("user_email"));
				temp.setUser_role_id(rs.getInt("user_role_id"));
				users.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public Users getOne(Integer id) {
		Users temp = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from ers_users where ers_users_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				temp = new Users();
				temp.setErs_users_id(rs.getInt(1));
				temp.setErs_username(rs.getString(2));
				temp.setErs_password(rs.getString(3));
				temp.setUser_first_name(rs.getString(4));
				temp.setUser_last_name(rs.getString(5));
				temp.setUser_email(rs.getString(6));
				temp.setUser_role_id(rs.getInt(7));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	
	public Users save(Users obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into ers_users values(null,?,?,?,?,?,?)";
			String[] keys = {"ers_users_id"};
			PreparedStatement ps = conn.prepareStatement(sql,keys);
			ps.setString(1, obj.getErs_username());
			ps.setString(2, obj.getErs_password());
			ps.setString(3, obj.getUser_first_name());
			ps.setString(4, obj.getUser_last_name());
			ps.setString(5, obj.getUser_email());
			ps.setInt(6, obj.getUser_role_id());
			
			int numRowsAffected = ps.executeUpdate();
			if(numRowsAffected == 0) {
				throw new SQLException("Creating User failed, no rows affected");
			}
			try(ResultSet generatedKeys = ps.getGeneratedKeys()){
				if(generatedKeys.next()) {
					obj.setErs_users_id(generatedKeys.getInt(1));
				}
				else {
					throw new SQLException("Creating User failed, no ID obtained.");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	
	public Users update(Users obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "update ers_Users set ers_username = ?, "
					+ "ers_password = ?, user_first_name = ?, user_last_name = ? "
					+ "user_email = ?, user_role_id = ? "
					+ "where ers_users_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, obj.getErs_username());
			ps.setString(2, obj.getErs_password());
			ps.setString(3, obj.getUser_first_name());
			ps.setString(4, obj.getUser_last_name());
			ps.setString(5, obj.getUser_email());
			ps.setInt(6, obj.getUser_role_id());
			ps.setInt(7, obj.getErs_users_id());
			
			int numRowsAffected = ps.executeUpdate();
			if(numRowsAffected>0) {
				conn.commit();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public void delete(Integer id) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "delete from ers_users where ers_users_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DELETE successful");
		
	}

}
