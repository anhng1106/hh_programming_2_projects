package student_database_program;

import java.util.Scanner;

import data_access.StudentDAO;

public class StudentDeleteProgram {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("=== Delete student (DAO version) ===");
		
		System.out.print("Id: ");
		int id = Integer.parseInt(input.nextLine());
		
		// Create an instance of StudentDAO
        StudentDAO studentDAO = new StudentDAO();
		
		// Call the delelteStudent method to delete the student from the list
        int result = studentDAO.deleteStudent(id);
        
        if (result == 0)
        {
        	System.out.println("\nStudent deleted!");
        } 
        else if (result == 1)
        {
			System.out.println("\nNothing deleted. Unknown student id " + id);
        } 
		
		input.close();
	}

}
