package student_database_programs;

import java.util.List;
import com.google.gson.Gson;

import data_access.StudentDAO;
import model.Student;

public class StudentJSONProgram {

	public static void main(String[] args) {
		// Create a StudentDAO object
        StudentDAO studentDAO = new StudentDAO();
        
        // Get the list of students from StudentDAO
        List<Student> studentList = studentDAO.getAllStudentsJSON();

        // Create a Gson object
        Gson gson = new Gson();

        // Convert the list of students to JSON
        String studentsJSON = gson.toJson(studentList);
        
        // Print the JSON string
        System.out.println(studentsJSON);

	}

}
