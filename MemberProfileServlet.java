
import java.io.*;             // For PrintWriter
import java.sql.*;            // You need to import the java.sql package to use JDBC

import javax.servlet.*;       // For ServletException and other things
import javax.servlet.http.*;  // For HttpServlet and other HttpServlet classes

/**
 * This class is used to generate a response to a query given in the
   JDBCServletDemo servlet.  It will make a connection to the database,
   execute the query to find the customers that live in the given city,
   and will generate an HTML page with an HTML list for the result.
 */
public class MemberProfileServlet extends HttpServlet {
  private Connection conn;
  //private Statement stmt;

  /**
   * The init() method is called when the servlet is first created and
   * is NOT called again for each user request.  So, it is used for
   * one-time initializations. For example, since we could use the same
   * database Connection and Statement for each call to this servlet,
   * initialize the database Connection and Statement here.
   *
   * @throws ServletException a general exception a servlet can throw when it
   *                          encounters difficulty
   */
  public void init() throws ServletException {
    try {
      // Load the Oracle JDBC driver
      Class.forName("oracle.jdbc.OracleDriver");  // load drivers

      // Connect to the database
      // You should put your name and password for the 2nd and 3rd parameter.
      conn =
        DriverManager.getConnection (
	     "jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle",
             OracleConnect.user_name,
             OracleConnect.password);

      // Create a Statement
      //stmt = conn.createStatement();
    } catch (Exception e) {
      System.out.println("Exception in MemberProfileServlet: " + e);
    }
  }

  /**
   * Processes the HTTP Get request that is sent to this servlet.
   * This is where we want to put most of our code.  Within this
   * method we will create the query we want to execute and send it to
   * the Statement object created in init().  What we get back is a
   * ResultSet, which is essentially a java.sql object that represents
   * the table (relation) that results from the query.  We iterate
   * through this ResultSet and print out the names of the customers
   * in the given city.
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
                                          throws ServletException, IOException {
    // We use the request object to get the parameter (which should
    // be the name of the city) that should have been sent to this servlet.
    HttpSession session = request.getSession();
    String email = (String)session.getAttribute("email");
    //String email = request.getParameter("email");

    // Make the query to the database and get the results.
    try {
      String query = "SELECT * from Member WHERE Member.EmailAddress = ?";
      PreparedStatement  pstm = conn.prepareStatement(query);
      pstm.setString(1, email);
			ResultSet rset = pstm.executeQuery();
      // ResultSet rset = stmt.executeQuery ("SELECT * from Member " +
      //                                     "WHERE Member.EmailAddress = '" + email +"'");

      // Build the HTML page we want to send as a response to a query given to
      // our JDBCServletDemo servlet.
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      out.println("<html>\n" +
                  "<head><title>Your Profile</title></head>\n" +
                  "<body bgcolor=\"#FDF5E6\">\n" +
                  " <p> \n");
      out.println("   <ul>"); // start an HTML list

      while (rset.next ()){
        //member has 9 columns
        // Here use a different method of ResultSet.  getString gets
        // the String value of the element that is in the current row
        // of the result set and the given column.
        // Note: the first column is column 1.
        String currMember = "";
        for(int i=1; i<9; i++){
          if(i<8){
            // add commas to column items except last one
            currMember += rset.getString (i) + ", ";
          }
          else{
            currMember += rset.getString (i);
          }
        }
        out.println (" <li> " + currMember + "</li>");
      }
      out.println("   </ul>" +
                  " </p>" +
                  "</body></html>");
                  
      out.println("<br>");
      out.println("<form name=\"return\" action=LoginServlet>");
      out.println("<center>");
      out.println("<input type=submit name=\"returnLogin\" value=\"Return\">");
      out.println("</center>");
      out.println("</form>");
      out.println("<br>");

      pstm.close();
    } catch (Exception e) {
      System.out.println("Exception in MemberProfileServlet: " + e);
    }
  }

  /**
   * Called when the servlet is being destroyed.  Here we can close
   * our Statement and Connection.
   */
  public void destroy() {
    // Disconnect from the database.
    try {
      //stmt.close();
      conn.close();
    } catch (Exception e) {
      System.out.println("Exception in MemberProfileServlet: " + e);
    }

  }
}
