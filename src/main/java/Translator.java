

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class Translator
 */
public class Translator extends HttpServlet {
	 /**
     * @see HttpServlet#HttpServlet()
     */
    public Translator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String textToTranslate=request.getParameter("textToTranslate");
		String languages=request.getParameter("languages");
		PrintWriter pw=response.getWriter();
        response.setContentType("text/html");
		// api
		HttpRequest request1 = HttpRequest.newBuilder()
				.uri(URI.create("https://text-translator2.p.rapidapi.com/translate"))
				.header("content-type", "application/x-www-form-urlencoded")
				.header("X-RapidAPI-Key", "14bceefcbdmshf7e1cb649f45162p12739fjsn12a24817f01c")
				.header("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
				.method("POST", HttpRequest.BodyPublishers.ofString("source_language=en&target_language="+languages+"&text="+textToTranslate+""))
				.build();
		
		try 
		{
		      HttpResponse<String> apiResponse =HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());
		      
		      String translatedText=apiResponse.body();
		      pw.println("<!DOCTYPE html>");
		      pw.println("<html>");
		      pw.println("<head>");
		      pw.println("<title>Translated Text</title>");
		      pw.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" \r\n"
		      		+ "   integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">");
		      pw.println("</head>");
		      pw.println("<body style=\"background: linear-gradient(90deg, rgba(2, 0, 36, 1) 0%, rgba(75, 14, 154, 1) 35%, rgba(0, 212, 255, 1) 100%); font-family: 'Poppins', sans-serif;\">");
		      pw.println("<div class=\"container mt-5 border border-light p-5 rounded-4 shadow bg-white align-text-center\" style=\"transform:translateY(25%); width:50vw;\">");
		      pw.println("<h2 class=\"text-center text-primary mb-2\">Translated Text:</h2>");
		      pw.println("<h3><p class=\"text-center mt-4\">Entered Text :<b>"+textToTranslate+"</b></p></h3>");
		      decode(translatedText, pw,textToTranslate,languages);
		      pw.println("</div>");
		      pw.println("</body>");
		      pw.println("</html>");
		      
		} 
		catch (IOException | InterruptedException  e) 
		{
			e.printStackTrace();
		} 
		
	}
	
	private void decode(String translatedText, PrintWriter pw, String textToTranslate, String languages) 
	{
		String resultstr=null;
		try 
		{
			JSONObject jsonObject  = new JSONObject(translatedText);
			JSONObject dataObject =jsonObject.getJSONObject("data");
		    resultstr=dataObject.getString("translatedText");
			pw.println("<h3><p class='text-center'>Translated Text :<b>"+resultstr+"</b></p></h3>");
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
//	    pw.println("<div class='d-flex justify-content-center mx-auto'");
		pw.println("<a href='/Translator/index.jsp' class='btn btn-primary mx-2 px-5 '>Go Back</a>");
		pw.println("<a href='/Translator/TranslatorRecord' class='btn btn-success mx-2 px-5 '>View Details</a>");
		
			insertIntoDatabase(textToTranslate,languages,resultstr);
		
		
	}

	private void insertIntoDatabase(String textToTranslate, String languages, String resultstr)
	{
	     try 
	     {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cloud247","root","ijustDh53@");
	           String str="insert into Translator (original_text,language,translated_text) values(?,?,?)";
	           PreparedStatement pst=con.prepareStatement(str);
        	   pst.setString(1,textToTranslate);
        	   pst.setString(2, languages);
        	   pst.setString(3, resultstr);
        	   int i=pst.executeUpdate();
        	   if(i>0)
        		   System.out.println("record inserted");
        	   else
        		   System.out.println("not inserted");
         } 
	     catch (ClassNotFoundException | SQLException e)
	     {
		
			e.printStackTrace();
		 }
	     
	    	   
	}

}
