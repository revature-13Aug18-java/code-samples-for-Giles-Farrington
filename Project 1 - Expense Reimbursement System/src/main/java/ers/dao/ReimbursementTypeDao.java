package ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ers.pojos.ReimbursementType;
import ers.util.ConnectionFactory;

public class ReimbursementTypeDao implements Dao<ReimbursementType, Integer> {

	
	public List<ReimbursementType> getAll() {
		List<ReimbursementType> reType = new ArrayList<ReimbursementType>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from ers_reimbursement_type order by reimb_type";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				ReimbursementType temp = new ReimbursementType();
				temp.setReimb_type_id(rs.getInt("reimb_type_id"));
				temp.setReimb_type(rs.getString("reimb_type"));
				reType.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reType;
	}
	
	public ReimbursementType getOne(Integer id) {
		ReimbursementType temp = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from ers_reimbursement_type where reimb_type_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				temp = new ReimbursementType();
				temp.setReimb_type_id(rs.getInt("1"));
				temp.setReimb_type(rs.getString("2"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	
	public ReimbursementType save(ReimbursementType obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into ers_reimbursement_type values(null,?)";
			String[] keys = {"reimb_type_id"};
			PreparedStatement ps = conn.prepareStatement(sql,keys);
			ps.setString(1, obj.getReimb_type());
			
			int numRowsAffected = ps.executeUpdate();
			if(numRowsAffected == 0) {
				throw new SQLException("Creating Reimbursment Type failed, no rows affected");
			}
			try(ResultSet generatedKeys = ps.getGeneratedKeys()){
				if(generatedKeys.next()) {
					obj.setReimb_type_id(generatedKeys.getInt(1));
				}
				else {
					throw new SQLException("Creating Reimbursement Type failed, no ID obtained.");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	
	public ReimbursementType update(ReimbursementType obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "update ers_reimbursement_type set reimb_type = ?, "
					+ "where reimb_type_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, obj.getReimb_type());
			ps.setInt(2, obj.getReimb_type_id());
			
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
			String sql = "delete from ers_reimbursement_type where reimb_type_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DELETE successful");
		
	}

}