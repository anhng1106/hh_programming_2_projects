package student_servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import data_access.StudentDAO;

@WebServlet("/deleteStudent")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		response.setContentType("application/json");
		 
		response.setCharacterEncoding("UTF-8");
		
	    try {
            int id = Integer.parseInt(request.getParameter("id")); // 1.

            // Create an instance of StudentDAO
            StudentDAO studentDAO = new StudentDAO();
            int errorCode = studentDAO.deleteStudent(id); //2

            out.print("{\"errorCode\": " + errorCode + "}");
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\": \"Invalid student ID format\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"An error occurred processing your request\"}");
        } finally {
            out.close();
        }
	}
}
