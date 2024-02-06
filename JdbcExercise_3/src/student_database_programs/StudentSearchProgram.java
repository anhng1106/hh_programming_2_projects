package student_database_programs;


import java.util.List;
import java.util.Scanner;

import data_access.StudentDAO;
import model.Student;

public class StudentSearchProgram {

	public static void main(String[] args) {
	
		Scanner input = new Scanner(System.in);
		
		System.out.println("=== Find student by id (DAO version) ===");
		
		System.out.print("Enter student id: ");
		int id = Integer.parseInt(input.nextLine());
		
		StudentDAO studentDAO = new StudentDAO();

		List<Student> studentList = studentDAO.getAllStudents();
		
		boolean foundID = false;

		if (studentList == null) {
			System.out.println("The database is temporarily unavailable. Please try again later.");
		} else {
			for (Student student : studentList) {
				if (id == student.getId())
				{
					foundID = true;
					System.out.println(student.getId() + ": " + student.getFirstname() + " " + student.getLastname() + ", " +
							student.getStreetaddress() + ", " + student.getPostcode() + " " + student.getPostoffice());
				}
			}
			if (foundID == false)
			{
				System.out.println("Unknown student id (" + id + ")");
			}
		
		}
		
		input.close();
	}
}
