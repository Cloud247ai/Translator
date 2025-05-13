

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class TranslatorRecord
 */
public class TranslatorRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public TranslatorRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		 pw.println("<!DOCTYPE html>");
	      pw.println("<html>");
	      pw.println("<head>");
	      pw.println("<title> RECORDS </title>");
	      pw.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" \r\n"
	      		+ "   integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">");
	      pw.println("</head>");
	      pw.println("<body style=\"background: linear-gradient(90deg, rgba(2, 0, 36, 1) 0%, rgba(75, 14, 154, 1) 35%, rgba(0, 212, 255, 1) 100%); font-family: 'Poppins', sans-serif;\">");
	      pw.println("<div class=\"container mt-5 border border-light p-5 rounded-4 shadow bg-white\" style=\"transform:translateX(4%); width:40vw;\">");
	      pw.println("<h2 class=\"text-center text-primary mb-2\">Translated Records</h2>");
	      pw.println("<br>");
	      try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cloud247","root","ijustDh53@");
				PreparedStatement pst=con.prepareStatement("select * from Translator");
				ResultSet rs=pst.executeQuery();
				pw.println("<table class='table table-bordered '>");
				pw.println("<thead>");
				pw.println("<tr>");
				pw.println("<th>Original Text</th>");
				pw.println("<th>Language</th>");
				pw.println("<th>Translated Text</th>");
				pw.println("</tr>");
				pw.println("</thead");
				pw.println("<tbody>");
				while(rs.next())
				{
					String text=rs.getString("original_text");
					String langCode=rs.getString("language");
					String result=rs.getString("translated_text");
					pw.println("<tr>");
					pw.println("<td>"+text+"</td>");
					pw.println("<td>"+langCode+"</td>");
					pw.println("<td>"+result+"</td>");
					pw.println("</tr>");
				}
					
			    pw.println("</tbody>");
			    pw.println("</table>");
				pw.println("<a href='/Translator/index.jsp' class='btn btn-primary mx-2 px-5 align-text-center '>Go Back</a>");
			    pw.println("</div>");
	            pw.println("</body>");
	            pw.println("</html>");
		
		
		} 
	    catch (ClassNotFoundException e) 
	    {
			e.printStackTrace();
		} 
	    catch (SQLException e)
	    {
			e.printStackTrace();
		}
	}


/* CREATE TABLE translations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    original_text VARCHAR(255) NOT NULL,
    translated_text VARCHAR(255) NOT NULL,
    target_language VARCHAR(10) NOT NULL,
    CONSTRAINT unique_translation UNIQUE (original_text, target_language)
);
*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
