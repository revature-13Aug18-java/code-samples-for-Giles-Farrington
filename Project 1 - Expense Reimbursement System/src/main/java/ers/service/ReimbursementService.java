package ers.service;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import ers.dao.Dao;
import ers.dao.ReimbursementDao;
import ers.pojos.Reimbursement;
import ers.util.ConnectionFactory;

public class ReimbursementService {
		
	static Dao<Reimbursement, Integer> rDao = new ReimbursementDao();
	
	public static void main(String[] args) throws SQLException {
		/*
		int rAmount = 200;
		Timestamp rSubmitted = new Timestamp(Calendar.getInstance().getTime().getTime()); 
		Timestamp rResolved = new Timestamp(Calendar.getInstance().getTime().getTime()); 
		String rDescription = "Description";
		Connection conn = ConnectionFactory.getInstance().getConnection();
		Blob receipt = conn.createBlob();
		receipt.setBytes(1, new byte[10]);
		int rAuthor = 1;
		int resolver = 2;
		int statusId = 2;
		int type_id = 1;
		
		Reimbursement toAdd = new Reimbursement(0, rAmount,rSubmitted,rResolved,rDescription,
				receipt, rAuthor, resolver, statusId, type_id);
		rDao.save(toAdd); */
		
		System.out.println(rDao.getAll());
	}
		
	public Reimbursement getOne(int id) {
		return rDao.getOne(id);
	}
		
	public List<Reimbursement> getAll(){
		return rDao.getAll();
	}
		
	public Reimbursement save(Reimbursement r) {
		return rDao.save(r);
	}
		
	public void update(Reimbursement r) {
		rDao.update(r);
	}
		
	public void delete(int id) {
		rDao.delete(id);
	}
}
