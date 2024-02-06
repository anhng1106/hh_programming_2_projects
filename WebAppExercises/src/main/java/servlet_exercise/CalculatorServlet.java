package servlet_exercise;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Servlet implementation class CalculatorServlet
 */
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculatorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		// Set the response data type to HTML and the character encoding to UTF-8
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
				
		try {
			int x = Integer.parseInt(request.getParameter("x"));
			int y = Integer.parseInt(request.getParameter("y"));
			String operation = request.getParameter("operation");
			
			int result = 0;
			switch (operation) {
				case "add":
					result = x + y;
					break;
				case "multiply":
					result = x * y;
					break;
			}
			
		out.println("<html><body>");
		out.println(x + " " + getSymbol(operation) + " " + y + " = " + result);
		out.println("</body></html>");
		} 
		catch (NumberFormatException nfe)
		{
			out.println("Invalid input");
		}
	}
	
	private String getSymbol(String operation) {
	    switch (operation) {
	        case "add": return "+";
	        case "multiply": return "*";
	        // Add more cases for other operations if needed
	        default: return "?";
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
