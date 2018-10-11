package ers.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import ers.pojos.Reimbursement;
import ers.pojos.ReimbursementStatus;
import ers.pojos.ReimbursementType;
import ers.pojos.Users;
import ers.service.ReimbursementService;
import ers.service.ReimbursementStatusService;
import ers.service.ReimbursementTypeService;
import ers.service.UsersService;

@WebServlet("/reimbType")
public class ReimbTypeServlet extends HttpServlet{
	private static Logger log = Logger.getLogger(LoginServlet.class);
	static ReimbursementTypeService rtService = new ReimbursementTypeService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		List<ReimbursementType> reimbursementTypes = rtService.getAll();

		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(reimbursementTypes);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(json);
	}
}
