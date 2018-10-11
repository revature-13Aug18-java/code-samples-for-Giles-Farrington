package ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ers.pojos.UserRoles;
import ers.util.ConnectionFactory;

public class UserRolesDao implements Dao<UserRoles, Integer> {

	
	public List<UserRoles> getAll() {
		List<UserRoles> reType = new ArrayList<UserRoles>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from ers_user_roles order by user_role";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				UserRoles temp = new UserRoles();
				temp.setErs_user_role_id(rs.getInt("ers_user_role_id"));
				temp.setUser_role(rs.getString("user_role"));
				reType.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reType;
	}
	
	public UserRoles getOne(Integer id) {
		UserRoles temp = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from ers_user_roles where ers_user_role_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				temp = new UserRoles();
				temp.setErs_user_role_id(rs.getInt("1"));
				temp.setUser_role(rs.getString("2"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	
	public UserRoles save(UserRoles obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into ers_user_roles values(null,?)";
			String[] keys = {"ers_user_role_id"};
			PreparedStatement ps = conn.prepareStatement(sql,keys);
			ps.setString(1, obj.getUser_role());
			
			int numRowsAffected = ps.executeUpdate();
			if(numRowsAffected == 0) {
				throw new SQLException("Creating User Role failed, no rows affected");
			}
			try(ResultSet generatedKeys = ps.getGeneratedKeys()){
				if(generatedKeys.next()) {
					obj.setErs_user_role_id(generatedKeys.getInt(1));
				}
				else {
					throw new SQLException("Creating User Role failed, no ID obtained.");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	
	public UserRoles update(UserRoles obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "update ers_user_roles set user_role = ?, "
					+ "where ers_user_role_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, obj.getUser_role());
			ps.setInt(2, obj.getErs_user_role_id());
			
			int numRowsAffected = ps.executeUpdate();
			if(numRowsAffected>0) {
				conn.commit();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}


	public void delete(Integer id) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "delete from ers_user_roles where ers_user_role_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DELETE successful");
		
	}
}
