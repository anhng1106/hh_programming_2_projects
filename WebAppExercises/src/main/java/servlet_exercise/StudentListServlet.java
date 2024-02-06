package servlet_exercise;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import data_access.StudentDAO;

/**
 * Servlet implementation class StudentListServlet
 */
public class StudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		// Set the response data type to JSON and the character encoding to UTF-8
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		
		// Create a StudentDAO object
        StudentDAO studentDAO = new StudentDAO();
        
        // Get the list of students from StudentDAO
        List<Student> students = studentDAO.getAllStudentsJSON();
		
		// Create a Gson object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Convert the list of students to JSON
        String studentsJSON = gson.toJson(students);
        
        out.println(studentsJSON);
        out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
