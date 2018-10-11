package ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ers.pojos.ReimbursementStatus;
import ers.util.ConnectionFactory;

public class ReimbursementStatusDao implements Dao<ReimbursementStatus, Integer> {

	
	public List<ReimbursementStatus> getAll() {
		List<ReimbursementStatus> reStatus = new ArrayList<ReimbursementStatus>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from ers_reimbursement_status order by reimb_status";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				ReimbursementStatus temp = new ReimbursementStatus();
				temp.setReimb_status_id(rs.getInt("reimb_status_id"));
				temp.setReimb_status(rs.getString("reimb_status"));
				reStatus.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reStatus;
	}
	
	public ReimbursementStatus getOne(Integer id) {
		ReimbursementStatus temp = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from ers_reimbursement_status where reimb_status_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				temp = new ReimbursementStatus();
				temp.setReimb_status_id(rs.getInt(1));
				temp.setReimb_status(rs.getString(2));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	
	public ReimbursementStatus save(ReimbursementStatus obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "insert into ers_reimbursement_status values(null,?)";
			String[] keys = {"Reimb_status_Id"};
			PreparedStatement ps = conn.prepareStatement(sql,keys);
			ps.setString(1, obj.getReimb_status());
			
			int numRowsAffected = ps.executeUpdate();
			if(numRowsAffected == 0) {
				throw new SQLException("Creating Reimbursment Status failed, no rows affected");
			}
			try(ResultSet generatedKeys = ps.getGeneratedKeys()){
				if(generatedKeys.next()) {
					obj.setReimb_status_id(generatedKeys.getInt(1));
				}
				else {
					throw new SQLException("Creating Reimbursement Status failed, no ID obtained.");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	
	public ReimbursementStatus update(ReimbursementStatus obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "update ers_reimbursement_Status set reimb_status = ?, "
					+ "where reimb_status_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, obj.getReimb_status());
			ps.setInt(2, obj.getReimb_status_id());
			
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
			String sql = "delete from ers_reimbursement_status where reimb_status_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DELETE successful");
		
	}

}
