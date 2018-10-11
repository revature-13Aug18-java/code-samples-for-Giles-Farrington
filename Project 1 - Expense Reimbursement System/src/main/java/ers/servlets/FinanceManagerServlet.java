package ers.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import ers.pojos.OutputReimbursement;
import ers.pojos.Reimbursement;
import ers.service.ReimbursementService;
import ers.service.UsersService;

@WebServlet("/financeManager")
public class FinanceManagerServlet extends HttpServlet{
	private static Logger log = Logger.getLogger(LoginServlet.class);
	static ReimbursementService rService = new ReimbursementService();
	static UsersService uService = new UsersService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ReimbursementService rService = new ReimbursementService();
		List<Reimbursement> reimbursements = rService.getAll();
		List<OutputReimbursement> outputs = new ArrayList<OutputReimbursement>();
		System.out.println(reimbursements);
		for(int i=0;i<reimbursements.size();i++) {
			String s = reimbursements.get(i).getReimb_submitted().toString();
			String r = "";
			if(reimbursements.get(i).getReimb_resolved() != null) {
				r = reimbursements.get(i).getReimb_resolved().toString();
			}
			OutputReimbursement temp = new OutputReimbursement(reimbursements.get(i).getReimb_id(), reimbursements.get(i).getReimb_amount(),
					s, r, reimbursements.get(i).getReimb_description(), reimbursements.get(i).getReimb_receipt(), reimbursements.get(i).getReimb_author(),
					reimbursements.get(i).getReimb_resolver(), reimbursements.get(i).getReimb_status_id(), reimbursements.get(i).getReimb_type_id());
			outputs.add(temp);
			
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(outputs);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(json);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Started doPost in EmployeeServlet!!!!!!!!");
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = "";
		
		if(br != null) {
			log.trace("BUFFERED READER IS NOT NULL");
			log.trace("br: " + br);
			json = br.readLine();
		}
		
		//ObjectMapper mapper = new ObjectMapper();
		Reimbursement toUpdate = new Reimbursement();
		Reimbursement toAdd = new Reimbursement();
		log.trace(json.toString());
		String[] input = json.toString().split(",");
		//log.trace(input[0] + ", " + Integer.parseInt(input[1]) + ", " + input[2]);
		toUpdate = rService.getOne(Integer.parseInt(input[0]));
		toAdd.setReimb_id(toUpdate.getReimb_id());
		toAdd.setReimb_amount(toUpdate.getReimb_amount());
		toAdd.setReimb_description(toUpdate.getReimb_description());
		toAdd.setReimb_author(toUpdate.getReimb_author());
		toAdd.setReimb_receipt(toUpdate.getReimb_receipt());
		toAdd.setReimb_submitted(toUpdate.getReimb_submitted());
		toAdd.setReimb_resolver(Integer.parseInt(input[2]));
		toAdd.setReimb_status_id(Integer.parseInt(input[1]));
		Timestamp rSubmitted = new Timestamp(Calendar.getInstance().getTime().getTime()); 
		toAdd.setReimb_resolved(rSubmitted);
		toAdd.setReimb_type_id(toUpdate.getReimb_type_id());
		rService.update(toAdd);
	} 

}
