<%@ page language="java" contentType="text/html" %>
<%@ page import="java.sql.*" %>

<html>

		<head><title>Pet Adopt</title></head>
		<body link=#f0f0ff alink vlink=#f0f0ff>
		<p>
		<center>
		<font size=5 face=Arial, Helvetica >
		<b>Pet Adopt</b><br>
		<font size=4>
		Pet Adoption System<br>
		</font>
		</font>
		<hr>
		<br>

		<b>Pets found for Adoption</b>

<br>


<%
	Class.forName ("oracle.jdbc.OracleDriver");// register drivers
	System.out.println("Attempting to connect 22222");
	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle",OracleConnect.user_name,OracleConnect.password);

	Statement s = conn.createStatement();
	String query = "SELECT * from Pet";
	System.out.println(query);
	ResultSet rs = s.executeQuery(query);
	System.out.println("Executed the query");
	int count =0;
	while(rs.next()){
		System.out.println("Query results");
		String owneremail=rs.getString("OwnerEmailAddress");
		String petname=rs.getString("PetName");
		String petbday=rs.getString("PetBirthday");
		String sterile=rs.getString("IsSterilized");
		String breed=rs.getString("Breed");
        String daysWalk=rs.getString("DaysWalk");
        String biteWire=rs.getString("BiteWire");

		out.println("<br>");
		out.println("<hr>");
		out.println("<p><b>Pet Name:</b>  " + petname+"</p>");
        out.println("<p><b>Breed:</b>  " + breed+"</p>");
		out.println("<p><b>Birthday:</b>  " + petbday+"</p>");
		out.println("<p><b>Has been sterilized?:</b>  " + sterile+"</p>");
		out.println("<p><b>How often do they to be walked?:</b>  " + daysWalk+"</p>");
        out.println("<p><b>Do they bite wires?:</b>  " + biteWire+"</p>");
		out.println("<br>");
        out.println("<p><b>Owner Email:</b> " + owneremail+"</p>");
		out.println("<hr>");
		out.println("<br><br>");
	}
		
						
	out.println("<table>");
	out.println("<tr>");
	out.println("<td>");
	out.println("<form name=\"mainmenu\" action=../LoginServlet method=get>");
	out.println("<input type=submit name=\"MainMenu\" value=\"Main Menu\">");
	out.println("</form>");
	out.println("</td>");
	out.println("</tr>");


	out.println("<td>");
	out.println("<form name=\"logout\" action=../LogoutServlet method=get>");
	out.println("<input type=submit name=\"logoutPA\" value=\"Logout\">");
	out.println("</form>");
	out.println("</td>");

	out.println("</table>");

%>
		</center>
		</p>
		</body>
		</html>

</html>
