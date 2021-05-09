
import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet
{
        public LoginServlet()
        {
                super();
        }

       public void drawHeader(HttpServletRequest req, PrintWriter out)
        {
			out.println("<html>");
			out.println("<head>");
						out.println("<title>PetAdopt logged in</title>");
			out.println("</head>");

			out.println("<body>");
			out.println("<p>");
			out.println("<center>");

        }


        public void drawFooter(HttpServletRequest req, PrintWriter out)
        {
			out.println("</center>");
			out.println("</p>");
			out.println("</body>");
			out.println("</html>");
        }


        private void drawPAOptions(HttpServletRequest req, PrintWriter out)
        {

				out.println("<font size=5 face=\"Arial,Helvetica\">");

				out.println("<b>You have logged into Pet Adopt</b></br>");
				out.println("<font size=4>");
				out.println("<b>Welcome to Pet Adopt! Look at the pets or the adopters!</b><br></font>");
				out.println("</font>");

                out.println("<hr");
                out.println("<br><br>");

				out.println("<form name=\"profile\" action=MemberProfileServlet method=get>");
                out.println("<input type=submit name=\"profileMem\" value=\"My Profile\">");
 				out.println("</form>");

                out.println("<br>");

				out.println("<form name=\"showpet\" action=PetListServlet method=get>");
                out.println("<input type=submit name=\"showPet\" value=\"Show all pets\">");
 				out.println("</form>");
				out.println("<br>");

				out.println("<form name=\"showadopt\" action=AdopterListServlet method=get>");
                out.println("<input type=submit name=\"showAdopt\" value=\"Show all adopters\">");
 				out.println("</form>");

				out.println("<br>");

                out.println("<form name=\"logout\" action=index.html>");
                	out.println("<input type=submit name=\"logoutPA\" value=\"Logout\">");
 				out.println("</form>");

        }




 		private void drawFailOptions(HttpServletRequest req, PrintWriter out)
        {
				out.println("<center>");
				out.println("<font size=5 face=\"Arial,Helvetica\">");

				out.println("<b>Sorry, only registered users can log into Pet Adopt</b></br>");
				out.println("<font size=4>");
				out.println("<b>Pet Adoption System</b><br></font>");
				out.println("</font>");

				out.println("</center>");

                out.println("<hr");
                out.println("<br><br>");

                out.println("<form name=\"logout\" action=index.html>");
				out.println("<center>");
				out.println("<input type=submit name=\"home\" value=\"Return to home\">");
				out.println("</center>");
 				out.println("</form>");

                out.println("<br>");
	    }


		public void drawLoginSuccess(HttpServletRequest req, PrintWriter out)
		{
				drawHeader(req,out);
				drawPAOptions(req,out);
			    drawFooter(req,out);
		}



		public void drawLoginFail(HttpServletRequest req, PrintWriter out)
		{
				drawHeader(req,out);
				drawFailOptions(req,out);
				drawFooter(req,out);
		}


        public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
        {
			
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

			//if login success, call the following function
			if(loginSuccess(conn , req))
				drawLoginSuccess(req,out);
			//if fail, call the following function
			else
				drawLoginFail(req,out);

			try{
				conn.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
        }


        //Checks to see if the user exists.
        private boolean loginSuccess(Connection conn , HttpServletRequest request){
			// we have to use "email" because this is the name in index.html
			if(request.getParameter("email") != null){
				
				String[] emailVal = request.getParameterValues("email");
			
				//Add this stuff to the session...
				HttpSession session = request.getSession();
				session.setAttribute("email" , ""+emailVal[0]);
			
				//Now query the database to see if this user exists......
				try{
					System.out.println("SELECT EmailAddress FROM Member WHERE EmailAddress =" + emailVal[0]);
					String query = "SELECT EmailAddress FROM Member WHERE EmailAddress = ?";
					PreparedStatement  pstm = conn.prepareStatement(query);
        			pstm.setString(1, emailVal[0]);
					ResultSet rs = pstm.executeQuery();
					int count = 0;
					while(rs.next())count++;

					if(count == 0){
						System.out.println("Member does not exist");
						return false;
					}
				}catch(SQLException sqle){
					sqle.printStackTrace();
					return false;
				}

				return true;
			}
			else{
				//If the parameter is empty the session must have the info..
				HttpSession session = request.getSession();

				String email = (String)session.getAttribute("email");
				System.out.println("else: " + email);
				

				if(email != null){
					try{
						//Now query the database to see if this user exists......
						try{
							System.out.println("SELECT EmailAddress FROM Member WHERE EmailAddress =" + email);
							String query_t = "SELECT EmailAddress FROM Member WHERE EmailAddress = ?";
							PreparedStatement  pstm_t = conn.prepareStatement(query_t);
        					pstm_t.setString(1, email);
							ResultSet rs = pstm_t.executeQuery();
							int count = 0;
							while(rs.next())count++;

							if(count == 0){
								System.out.println("Member does not exist");
								return false;
							}
							else{
								return true;
							}
						}catch(SQLException sqle){
							sqle.printStackTrace();
							return false;
						}
					}
					catch(Exception e){
						e.printStackTrace();
						return false;
					}
				}
				else{
					return false;
				}
			}
		}
}


