package student_database_programs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import data_access.ConnectionParameters;
import data_access.DbUtils;

public class SimpleStudentDeleteProgram {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		Scanner input = new Scanner(System.in);
		
		System.out.println("=== Delete student ===");
		
		System.out.print("Id: ");
		int id = Integer.parseInt(input.nextLine());
		
		try {
			connection = DriverManager.getConnection(ConnectionParameters.connectionString,
					ConnectionParameters.username, ConnectionParameters.password);
							
			String sqlText = "DELETE FROM Student WHERE id = ?";
			
			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, id);
			
			int rowsAffected = preparedStatement.executeUpdate();
			
			if (rowsAffected > 0)
			{
				System.out.println("\nStudent deleted!");
			} else
			{
				System.out.println("Nothing deleted. Unknown student id " + id);
			}

			
		} catch (SQLException sqle) {
			// First, check if the problem is primary key violation (the error code is vendor-dependent)
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				
				System.out.println("\n[ERROR] Database error. " + sqle.getMessage());
			}
			
		} finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}
		
		input.close();
	}

}
