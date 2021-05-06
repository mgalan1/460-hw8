//package UACATS.servlets;
import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
//import UACATS.servlets.*;
//import UACATS.utils.*;
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
        {// need to edit some of the names below that have ua still

				out.println("<font size=5 face=\"Arial,Helvetica\">");

				out.println("<b>You have logged into Pet Adopt</b></br>");
				out.println("<font size=4>");
				out.println("<b>Welcome to Pet Adopt! Adopt a pet below</b><br></font>");
				out.println("</font>");

                out.println("<hr");
                out.println("<br><br>");

				// TODO: THIS NEEDS TO GO
                out.println("<form name=\"findall\" action=../../../examples/jsp/MerchandiseReportSummary.jsp>");
                	out.println("<input type=submit name=\"findallsubmit\" value=\"Show All Merchandise\">");
 				out.println("</form>");

                out.println("<br>");

				// TODO: THIS NEEDS TO GO
                out.println("<form name=\"advancedfind\" action=FindMerchandiseServlet method=get>");
                	out.println("<input type=submit name=\"Adsearch\" value=\"Advanced Search\">");
 				out.println("</form>");

                out.println("<br>");
				// TODO: THIS NEEDS TO GO
                out.println("<form name=\"addMerchandise\" action=AddMerchandiseServlet method=get>");
					out.println("<input type=submit name=\"Adsearch\" value=\"Add Merchandise\">");
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
				// TODO: check that there is a valid email address (our definition is just having one @)
				//consider adding address exception (import from javax) as ec
				String[] emailVal = request.getParameterValues("email");
				String[] pwVal = request.getParameterValues("loginPW");
				// String[] emailPre = new String[3];
				// int countA = 0;
				// try{
				// 	emailPre = emailVal[0].split("@");
				// 	if(emailPre.length() > 1){
				// 		// count the pieces of the original string, should be 2 since there is a part before the at and after
				// 		countA++;
				// 	}
				// 	// if(countA != 1){
				// 	// 	// there is not an at symbol or there is more than one which is also not valid
				// 	
				// 	// }
					
				// }catch(Exception e){
				// 	e.printStackTrace();
				// }

				//Add this stuff to the session...
				HttpSession session = request.getSession();
				session.setAttribute("email" , ""+emailVal[0]);
				session.setAttribute("loginPW" , ""+pwVal[0]);



			}
			
				//Now query the database to see if this user exists......
				try{
					Statement s = conn.createStatement();
					System.out.println("Select EmailAddress from Member where EmailAddress =" + emailVal[0]);
					ResultSet rs = s.executeQuery("Select EmailAddress from Member where EmailAddress =" + emailVal[0]);
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
				String pw = (String)session.getAttribute("loginPW");

				if(email != null && pw != null){
					try{
						//Now query the database to see if this user exists......
						try{
							Statement s = conn.createStatement();
							// TODO consider adding a check for the right password in the database
							System.out.println("Select EmailAddress from Member where EmailAddress =" + email);
							ResultSet rs = s.executeQuery("Select EmailAddress from Member where EmailAddress =" + email);
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


