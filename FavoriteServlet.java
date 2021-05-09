
import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class FavoriteServlet extends HttpServlet
{
	String emailMem;
    // String emailPet;
    // String petName;
    // String petBday;

	public FavoriteServlet()
	{
		super();
	}


	public void drawUpdateMessage(HttpServletRequest req, PrintWriter out)
	{
        // need to update or create a favorite instance with the current users email + the pet owners email
		String emailMem = req.getParameter("email");
		

		String blank_str = "";
		if(emailMem.equals(blank_str))
		    emailMem = "none";

		out.println("<h2 align=\"center\">Add New Favorite Success!</h2>");
		
		out.println("<form name=\"MainMenu\" action=LoginServlet>");
		out.println("<input type=submit name=\"MainMenu\" value=\"MainMenu\">");
		out.println("</form>");

		out.println("<br>");

		out.println("<form name=\"logout\" action=LogoutServlet method=get>");
		out.println("<input type=submit name=\"logoutUACATS\" value=\"Logout\">");
		out.println("</form>");

	}




	public void drawHeader(HttpServletRequest req, PrintWriter out)
	{
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Update Favorites</title>");
		out.println("</head>");

		out.println("<body>");
		out.println("<p>");
		out.println("<center>");
		out.println("<font size=5 face=\"Arial,Helvetica\">");
		out.println("<b>Update favorites</b><br></font>");

		out.println("<hr");
		out.println("<br><br>");
	}


	public void drawFooter(HttpServletRequest req, PrintWriter out)
	{
		out.println("</center>");
		out.println("</p>");
		out.println("</body>");
		out.println("</html>");
	}

	public void drawFailOption(HttpServletRequest req, PrintWriter out)
	{
		out.println("<h2 align=\"center\">Error: Request could not be carried out.</h2>");
		out.println("<form name=\"logoutbad\" action=index.html>");
		out.println("<center>");
		out.println("<input type=submit name=\"tohomebad\" value=\"Return to home\">&nbsp&nbsp");
		out.println("</center>");
		out.println("</form>");
	}



	// public void drawAddFavoriteMenu(HttpServletRequest req, PrintWriter out)
	// {
	// 	out.println("<form name=\"AddFavorite\" action=FavoriteServlet method=get>");
	// 	out.println("<br><br>");
	// 	out.println("<font size=3>");
		
	// 	out.println("<b> Email Address:</b>");
	// 	out.println("<input type=text name=\"email\" size=20<br><br>");

	// 	out.println("<p><br><b> Password:</b>");
	// 	out.println("<input type=text name=\"pw\"><br><br>");

	// 	out.println("<b> Confirm Password:</b>");
	// 	out.println("<input type=text name=\"pwconfirm\"><br><br>");
	// 	out.println("</p>");

	// 	out.println("<table>");
	// 	out.println("<tr>");
	// 	out.println("<td>");

	// 	out.println("<input type=submit name=\"Submit\" value=\"AddMe\">&nbsp&nbsp");

	// 	out.println("</td");
	// 	out.println("</tr>");
	// 	out.println("</form>");
	// 	out.println("<tr>");
	// 	out.println("<td>");
	// 	out.println("<form name=\"Cancel\" action=AddIndividualServlet method=get>");
	// 	out.println("<input type=submit name=\"Cancel\" value=\"Cancel\">&nbsp&nbsp");
	// 	out.println("</form>");
	// 	out.println("</td>");
	// 	out.println("</tr>");


	// 	out.println("<tr>");
	// 	out.println("<td>");

	// 	out.println("<form name=\"logout\" action=index.html>");
	// 	out.println("<input type=submit name=\"tohome\" value=\"Return to home\">&nbsp&nbsp");
	// 	out.println("</form>");
	// 	out.println("</tr>");
	// 	out.println("</table>");


	// 	out.println("<br><br><br>");

	// }




	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		Connection conn=null;
		try{
			Class.forName ("oracle.jdbc.OracleDriver");// register drivers
			System.out.println("Attempting to connect 111");
			conn = DriverManager.getConnection(OracleConnect.connect_string,OracleConnect.user_name,OracleConnect.password);
		}catch(Exception e){
			e.printStackTrace();
		}

		drawHeader(req,out);

		// if(req.getParameter("Submit") == null || !enterInfo(req , conn))
		// {
		// 	drawAddIndividualMenu(req,out);
		// }
		// else
		// {
		// 	drawUpdateMessage(req,out);
		// }

		drawFooter(req,out);
		try{
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	boolean enterInfo(HttpServletRequest request , Connection conn){
		// System.out.println("\nIn AddIndividualServlet enterInfo");
		// //Get the firstname
		// String[] paramValues = request.getParameterValues("firstname");
		// firstname = paramValues[0];

		// //Get the lastname
		// paramValues = request.getParameterValues("lastname");
		// lastname = paramValues[0];

		// paramValues = request.getParameterValues("DoBday");
		// dobday = paramValues[0];

		// paramValues = request.getParameterValues("DoBmonth");
		// dobmonth = paramValues[0];

		// paramValues = request.getParameterValues("DoByear");
		// dobyear = paramValues[0];

		// paramValues = request.getParameterValues("email");
		// email = paramValues[0];

		// paramValues = request.getParameterValues("phone");
		// phone = paramValues[0];

		// paramValues = request.getParameterValues("pw");
		// pw1 = paramValues[0];

		// paramValues = request.getParameterValues("pwconfirm");
		// pw2 = paramValues[0];

		// if(!pw1.equals(pw2)){
		// 	System.out.println("Here....");
		// 	errorCode = 3;
		// 	return false;
		// }

		// //Now query the database to see if this user exists......
		// try{
		// 	Statement s = conn.createStatement();

		// 	ResultSet rs = s.executeQuery("Select UserID from INDIVIDUAL where FirstName = '" + firstname
		// 												+ "' and LastName = '"+lastname
		// 												+ "' and DoB = '" + dobday +"-"+dobmonth +"-"+dobyear+"'");
		// 	int count = 0;
		// 	while(rs.next())count++;

		// 	if(count > 0){
		// 		errorCode = 1;
		// 		return false;
		// 	}
		// }catch(SQLException sqle){
		// 	sqle.printStackTrace();
		// 	System.out.println("No..here...here....");
		// 	errorCode = 2;
		// 	return false;
		// }

		// //The user does not already exits in the DB. Insert the new user in the two tables...
		// //For table Individual, get the largest id used so far....
		// int id = -1;
		// try{
		// 	Statement s = conn.createStatement();

		// 	ResultSet rs = s.executeQuery("Select UserID from INDIVIDUAL");
		// 	while(rs.next()){
		// 		int temp = rs.getInt(1);
		// 		if(temp > id)
		// 			id = temp;
		// 	}

		// 	System.out.println("Old id is "+id);

		// 	//Insert info into table CatUser...
		// 	s.executeUpdate("INSERT INTO CatUser VALUES("+(id+1)+" , "+phone+" , "+pw1+" , '"+email+"')");

		// 	//Insert info into table INDIVIDUAL....
		// 	s.executeUpdate("INSERT INTO INDIVIDUAL VALUES('"+firstname+"' , '"+lastname+"' , '"+dobday +"-"+dobmonth +"-"+dobyear+"' , "+(id+1)+")");


		// 	userId = id+1;
		// }catch(SQLException sqle){
		// 	sqle.printStackTrace();
		// 	System.out.println("No..here...over here....");
		// 	errorCode = 2;
		// 	return false;
		// }

		// //Add this info into the session...
		// HttpSession session = request.getSession();
		// session.setAttribute("loginID" , ""+userId);
		// session.setAttribute("loginPW" , ""+pw1);

		return true;
	}
}
