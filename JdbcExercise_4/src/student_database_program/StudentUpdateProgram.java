package student_database_program;

import java.util.Scanner;

import data_access.StudentDAO;
import model.Student;

public class StudentUpdateProgram {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("=== Update student ===");
		
		System.out.print("Id: ");
		int id = Integer.parseInt(input.nextLine());
		
		System.out.print("First name: ");
		String firstName = input.nextLine();
		
		System.out.print("Last name: ");
		String lastName = input.nextLine();
		
		System.out.print("Street: ");
		String address = input.nextLine();
		
		System.out.print("Postcode: ");
		int postCode = Integer.parseInt(input.nextLine());
		
		System.out.print("Post office: ");
		String postOffice = input.nextLine();
		
		Student student = new Student (id, firstName, lastName, address, postCode, postOffice);
		
		StudentDAO studentDAO = new StudentDAO();
				
		int result = studentDAO.updateStudent(student);
		
		if (result == 0)
        {
        	System.out.println("\nStudent data updated!");
        } 
        else if (result == 1)
        {
			System.out.println("\nNothing updated. Unknown student id " + id);
        } 
		
		input.close();

	}

}
