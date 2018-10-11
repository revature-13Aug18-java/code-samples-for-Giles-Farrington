package ers.service;

import java.util.List;

import ers.dao.Dao;
import ers.dao.ReimbursementTypeDao;
import ers.pojos.ReimbursementType;

public class ReimbursementTypeService {
	static Dao<ReimbursementType, Integer> rtDao = new ReimbursementTypeDao();
	
	public static void main(String[] args) {
		String rType = "Medical";
		ReimbursementType input = new ReimbursementType();
		input.setReimb_type(rType);
		rtDao.save(input);
	}
	public ReimbursementType getOne(int id) {
		return rtDao.getOne(id);
	}
	
	public List<ReimbursementType> getAll(){
		return rtDao.getAll();
	}
	
	public ReimbursementType save(ReimbursementType r) {
		return rtDao.save(r);
	}
	
	public void update(ReimbursementType r) {
		rtDao.update(r);
	}
	
	public void delete(int id) {
		rtDao.delete(id);
	}
}
