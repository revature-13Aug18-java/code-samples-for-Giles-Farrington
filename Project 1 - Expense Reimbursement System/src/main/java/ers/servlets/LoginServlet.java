package ers.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import ers.pojos.Reimbursement;
import ers.pojos.Users;
import ers.service.UsersService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	static UsersService uService = new UsersService();
	private static Logger log = Logger.getLogger(LoginServlet.class);
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		log.trace("Initializing LogInServlet");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Started doGet in LoginServlet!!!!!!!!");
		List<Users> users = uService.getAll();
		if(users.size()>0) {
			log.trace("USERS.SIZE>0 in LoginServlet!!!!!!!!");
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(users);
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			out.write(json);
		}else {
			resp.setStatus(404);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("Started doPost in LoginServlet!!!!!!!!");
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String json = "";
		if(br != null) {
			json = br.readLine();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		log.trace(json.toString());
		String[] input = json.toString().split(",");
		log.trace("NEW SPLIT VALUES: " + input[0] + " & " + input[1] + " & " + input[2]
				+ " & " + input[3] + " & " + input[4] + " & " + input[5]);
		Users toAdd = new Users();
		toAdd.setErs_username(input[0]);
		toAdd.setErs_password(input[1]);
		toAdd.setUser_first_name(input[2]);
		toAdd.setUser_last_name(input[3]);
		toAdd.setUser_email(input[4]);
		toAdd.setUser_role_id(Integer.parseInt(input[5]));
		uService.save(toAdd);
		
	}
}
