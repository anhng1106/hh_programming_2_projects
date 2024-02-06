package student_database_program;

import java.util.Scanner;

import data_access.StudentDAO;
import model.Student;

public class StudentInsertProgram {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("=== Add student (DAO version) ===");
		
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
		
        // Create a Student object with the entered details
		Student student = new Student(id, firstName, lastName, address, postCode, postOffice);
		
		 // Create an instance of StudentDAO
        StudentDAO studentDAO = new StudentDAO();

        // Call the insertStudent method to insert the student into the list
        int result = studentDAO.insert(student);

        // Handle the result appropriately
        if (result == 0) {
            System.out.println("\nStudent data added!");
        } else if (result == 1) {
            System.out.println("\nCannot insert the student. The student id " + id + " is already in use.");
        } else {
            System.out.println(student);
        }
        	
        input.close();
	}

}