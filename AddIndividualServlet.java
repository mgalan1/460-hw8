import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AddIndividualServlet extends HttpServlet{
	String email;
	
	public AddIndividualServlet() {
		super();
	}
	
	 public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 //connect to oracle

		 	email = req.getParameter("email");
			Connection conn=null;
			try{
				Class.forName("oracle.jdbc.OracleDriver");  // load drivers
				System.out.println("Attempting to connect 000");
				conn = DriverManager.getConnection(OracleConnect.connect_string,OracleConnect.user_name,OracleConnect.password);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			printMenu(out);
		 
     }
	 
	 public void printMenu(PrintWriter out) {
		 out.println("<!DOCTYPE html>");
		 out.println(" <html>");
		 out.println("<head>");
		 out.println("<title>Registration</title>");
		 out.println(" </head>");
		 out.println("<body>");
		
		 out.println("<center>");
		 out.println(" <h1>Register</h1>");
		 out.println(" <p>* indicates a required field</p>");
		 out.println(" <br>");


		 //out.println("<form name=\"register\" action = addNM()>");
		 out.println("<b>Name*		</b>");
		 out.println("<input type=text name=\"name\"><br><br>");
 
		 out.println("<b>Self-Introduction*	</b>");
		 out.println("<input type = text name = \"intro\"><br><br>");
		             
		 out.println("<b>Location*	</b>");
		 out.println("<input type = text name = \"location\"><br><br>");
		             
		 out.println("<b>Birthday*	</b>");
		 out.println("<input type = date name = \"birthday\"><br><br>");
		             
		 out.println("<input type = checkbox name = \"isAdopter\">");
		 out.println("<b>Register as an adopter	</b><br><br>");
		             
		 out.println("<input type = checkbox name = \"isSender\">");
		 out.println("<b>Register as a sender	</b><br><br>");
		             
		 out.println("<b>Adopting Experience (adopters only)	</b>");
		 out.println("<input type = text name = \"birthday\"><br><br>");
		             
		 out.println("</form>");

		 out.println("</body>");
		 out.println("</html>");
	 }
	 
	 // This function causes errors at compile time
	//  public void addNM(HttpServletRequest req, HttpServletResponse res) {
	// 	 String name = req.getName();
	//  }
	

}
