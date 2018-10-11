package ers.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ers.pojos.Reimbursement;
import ers.util.ConnectionFactory;

public class ReimbursementDao implements Dao<Reimbursement, Integer> {

	
	public List<Reimbursement> getAll() {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from ers_reimbursement order by reimb_author";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				Reimbursement temp = new Reimbursement();
				temp.setReimb_id(rs.getInt("reimb_id"));
				temp.setReimb_amount(rs.getDouble("reimb_amount"));
				temp.setReimb_submitted(rs.getTimestamp("reimb_submitted"));
				temp.setReimb_resolved(rs.getTimestamp("reimb_resolved"));
				temp.setReimb_description(rs.getString("reimb_description"));
				Blob blob = rs.getBlob("reimb_receipt");
				byte[] bytes = blob.getBytes(1, 10);
				temp.setReimb_receipt(bytes);
				temp.setReimb_author(rs.getInt("reimb_author"));
				temp.setReimb_resolver(rs.getInt("reimb_resolver"));
				temp.setReimb_status_id(rs.getInt("reimb_status_id"));
				temp.setReimb_type_id(rs.getInt("reimb_type_id"));
				reimbursements.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}
	
	public Reimbursement getOne(Integer id) {
		Reimbursement temp = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from ers_reimbursement where reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				temp = new Reimbursement();
				temp.setReimb_id(rs.getInt(1));
				temp.setReimb_amount(rs.getDouble(2));
				temp.setReimb_submitted(rs.getTimestamp(3));
				temp.setReimb_resolved(rs.getTimestamp(4));
				temp.setReimb_description(rs.getString(5));
				Blob blob = rs.getBlob("reimb_receipt");
				byte[] bytes = blob.getBytes(1, 10);
				temp.setReimb_receipt(bytes);
				temp.setReimb_author(rs.getInt(7));
				temp.setReimb_resolver(rs.getInt(8));
				temp.setReimb_status_id(rs.getInt(9));
				temp.setReimb_type_id(rs.getInt(10));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println(temp);
		return temp;
	}

	
	public Reimbursement save(Reimbursement obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String[] keys = {"REIMB_ID"};
			String sql = "insert into ers_reimbursement values(null,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql,keys);
			Blob receipt = conn.createBlob();	//JUST FOR TESTING PURPOSES
			receipt.setBytes(1, new byte[10]);	//ALSO ^
			ps.setDouble(1, obj.getReimb_amount());
			ps.setTimestamp(2, obj.getReimb_submitted());
			ps.setTimestamp(3, obj.getReimb_resolved());
			ps.setString(4, obj.getReimb_description());
			ps.setBlob(5, receipt);	//WILL CHANGE from receipt to obj.getReimb_receipt()
			ps.setInt(6, obj.getReimb_author());
			ps.setInt(7, obj.getReimb_resolver());
			ps.setInt(8, obj.getReimb_status_id());
			ps.setInt(9, obj.getReimb_type_id());
			int numRowsAffected = ps.executeUpdate();
			if(numRowsAffected == 0) {
				throw new SQLException("Creating Reimbursment failed, no rows affected");
			}
			try(ResultSet generatedKeys = ps.getGeneratedKeys()){
				if(generatedKeys.next()) {
					obj.setReimb_id(generatedKeys.getInt(1));
				}
				else {
					throw new SQLException("Creating Reimbursement failed, no ID obtained.");
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	
	public Reimbursement update(Reimbursement obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			conn.setAutoCommit(false);
			String sql = "update ers_Reimbursement set reimb_amount = ?, "
					+ "reimb_submitted = ?, reimb_resolved = ?, reimb_description = ?, "
					+ "reimb_receipt = ?, reimb_author = ?, reimb_resolver = ?, "
					+ "reimb_status_id = ?, reimb_type_id = ? "
					+ "where reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, obj.getReimb_amount());
			ps.setTimestamp(2, obj.getReimb_submitted());
			ps.setTimestamp(3, obj.getReimb_resolved());
			ps.setString(4, obj.getReimb_description());
			ps.setBytes(5, obj.getReimb_receipt());
			ps.setInt(6, obj.getReimb_author());
			ps.setInt(7, obj.getReimb_resolver());
			ps.setInt(8, obj.getReimb_status_id());
			ps.setInt(9, obj.getReimb_type_id());
			ps.setInt(10, obj.getReimb_id());
			
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
			String sql = "delete from ers_reimbursement where reimbursement_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DELETE successful");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
