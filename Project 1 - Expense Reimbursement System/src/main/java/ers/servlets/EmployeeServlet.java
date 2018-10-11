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

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet{
	private static Logger log = Logger.getLogger(LoginServlet.class);
	static ReimbursementService rService = new ReimbursementService();
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
		Reimbursement toAdd = new Reimbursement();
		log.trace(json.toString());
		String[] input = json.toString().split(">");
		log.trace("NEW SPLIT VALUES: " + input[0] + " & " + input[1] + " & " + input[2] + " & " + input[3]);
		toAdd.setReimb_amount(Double.parseDouble(input[0]));
		toAdd.setReimb_description(input[1]);
		toAdd.setReimb_author(Integer.parseInt(input[2]));
		toAdd.setReimb_receipt(null);
		toAdd.setReimb_resolved(null);
		toAdd.setReimb_resolver(1);
		toAdd.setReimb_status_id(1);
		Timestamp rSubmitted = new Timestamp(Calendar.getInstance().getTime().getTime()); 
		toAdd.setReimb_submitted(rSubmitted);
		toAdd.setReimb_type_id(Integer.parseInt(input[3]));
		rService.save(toAdd);
		
		
		
		//Users u = mapper.readValue(json,  Users.class);
		//System.out.println(u.toString());
	}

}
