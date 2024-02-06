package student_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import data_access.StudentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;

@WebServlet("/studentAdd")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		
		response.setContentType("application/json");
		 
		response.setCharacterEncoding("UTF-8");
		
		try {
	            int id = Integer.parseInt(request.getParameter("id"));
	            String firstname = request.getParameter("fname");
	            String lastname = request.getParameter("lname");
	            String address = request.getParameter("address");
	            int postcode = Integer.parseInt(request.getParameter("postcode"));
	            String postoffice = request.getParameter("postoffice");

	            Student student = new Student(id, firstname, lastname, address, postcode, postoffice);

	            StudentDAO studentDAO = new StudentDAO();
	            int errorCode = studentDAO.insertStudent(student);

	            out.print("{\"errorCode\": " + errorCode + "}");
	        } catch (NumberFormatException e) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            out.print("{\"error\": \"Invalid input format\"}");
	        } catch (Exception e) {
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            out.print("{\"error\": \"An error occurred processing your request\"}");
	        } finally {
	            out.close();
	        }
	}
}

	
