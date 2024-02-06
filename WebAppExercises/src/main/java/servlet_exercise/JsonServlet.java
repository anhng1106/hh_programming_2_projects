package servlet_exercise;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class JsonServlet
 */
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JsonServlet() {
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
		
		// Create arraylist and 2 student objects
		ArrayList <Student> students = new ArrayList<>();
		Student s1 = new Student (101, "An", "Tran", "SingleTree Ln", 55344, "Minneapolis");
		Student s2 = new Student (102, "Na", "Young", "Klaneettitie 1", 00420, "Helsinki");
		
		students.add(s1);
		students.add(s2);
		
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
