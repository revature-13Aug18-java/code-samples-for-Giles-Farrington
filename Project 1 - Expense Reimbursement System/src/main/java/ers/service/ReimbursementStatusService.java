package ers.service;

import java.util.List;

import ers.dao.Dao;
import ers.dao.ReimbursementStatusDao;
import ers.pojos.ReimbursementStatus;

public class ReimbursementStatusService {
	static Dao<ReimbursementStatus, Integer> rsDao = new ReimbursementStatusDao();
	
	public static void main(String[] args) {
		String rStatus = "Denied";
		ReimbursementStatus input = new ReimbursementStatus();
		input.setReimb_status(rStatus);
		rsDao.save(input);
	}
	public ReimbursementStatus getOne(int id) {
		return rsDao.getOne(id);
	}
	
	public List<ReimbursementStatus> getAll(){
		return rsDao.getAll();
	}
	
	public ReimbursementStatus save(ReimbursementStatus r) {
		return rsDao.save(r);
	}
	
	public void update(ReimbursementStatus r) {
		rsDao.update(r);
	}
	
	public void delete(int id) {
		rsDao.delete(id);
	}

}
