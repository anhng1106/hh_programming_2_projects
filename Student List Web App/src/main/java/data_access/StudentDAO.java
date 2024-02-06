package data_access;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import model.Student;

public class StudentDAO {
	
	public StudentDAO()
	{
		// In Tomcat 8 environment, the JDBC driver must be explicitly loaded as below
		try {
			Class.forName(ConnectionParameters.jdbcDriver);
		} catch (ClassNotFoundException cnfe) {
			System.out.println(cnfe.getMessage());
		}
	}
		
	/**
	 * Open a new database connection
	 * 
	 * @throws SQLException
	 */
	private Connection openConnection() throws SQLException {
		return DriverManager.getConnection(ConnectionParameters.connectionString, ConnectionParameters.username,
				ConnectionParameters.password);
	}

	/**
	 * Retrieve all students from the database
	 * 
	 * @return List<Student>
	 * @throws SQLException
	 */
	public List<Student> getAllStudents() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> studentList = new ArrayList<Student>();

		try {
			connection = openConnection();

			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice FROM Student ORDER BY lastname";

			preparedStatement = connection.prepareStatement(sqlText);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String address = resultSet.getString("streetaddress");
				String postOffice = resultSet.getString("postoffice");
				int postCode = resultSet.getInt("postcode");
				int id = resultSet.getInt("id");

				studentList.add(new Student(id, firstName, lastName, address, postCode, postOffice));
			}

		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: getaAllStudents() failed. " + sqle.getMessage() + "\n");
			studentList = null;

		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}

		return studentList;
	}
	
	/**
	 * Retrieve all students from the database using JSON
	 */
	public List<Student> getAllStudentsJSON() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> studentList = new ArrayList<Student>();

		try {
			connection = openConnection();

			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice FROM Student ORDER BY lastname";

			preparedStatement = connection.prepareStatement(sqlText);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String address = resultSet.getString("streetaddress");
				String postOffice = resultSet.getString("postoffice");
				int postCode = resultSet.getInt("postcode");
				int id = resultSet.getInt("id");

				studentList.add(new Student(id, firstName, lastName, address, postCode, postOffice));
				
				//System.out.println(id + ": " + firstName + " " + lastName + ", " + address + ", " + postCode + " " + postOffice);
			}

		} catch (SQLException sqle) {
			System.out.println("\n[ERROR] StudentDAO: getaAllStudents() failed. " + sqle.getMessage() + "\n");
			studentList = null;

		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}

		return studentList;
	}
	
	/**
	 * Retrieve all students from the given ID from the database
	 * 
	 * @param givenID - the ID to be used as the filter in the query
	 * @return List<Student>
	 * @throws SQLException
	 */
	public List<Student> getStudentByID(int givenId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> studentList = new ArrayList<Student>();

		try {
			connection = openConnection();

			String sqlText = "SELECT id, firstname, lastname, streetaddress, postcode, postoffice FROM Student WHERE id = ? ORDER BY lastname";

			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, givenId);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				String firstName = resultSet.getString("firstname");
				String lastName = resultSet.getString("lastname");
				String address = resultSet.getString("streetaddress");
				String postOffice = resultSet.getString("postoffice");
				int postCode = resultSet.getInt("postcode");
				int id = resultSet.getInt("id");

				studentList.add(new Student(id, firstName, lastName, address, postCode, postOffice));
			}

		} catch (SQLException sqle) {
			System.out.println("[ERROR] StudentDAO: getStudentByID() failed. " + sqle.getMessage() + "\n");
			studentList = null;

		} finally {
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}

		return studentList;
	}

	/**
	 * Insert a student into the database
	 * 
	 * @return 0 = Ok | 1 = Duplicate id | -1 = Other error
	 * @throws SQLException
	 */
	public int insertStudent(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int errorCode = -1;

		try {
			connection = openConnection();

			String sqlText = "INSERT INTO Student (id, firstname, lastname, streetaddress, postcode, postoffice) VALUES (?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getFirstname());
			preparedStatement.setString(3, student.getLastname());
			preparedStatement.setString(4, student.getStreetaddress());
			preparedStatement.setInt(5, student.getPostcode());
			preparedStatement.setString(6, student.getPostoffice());

			System.out.println(student);

			preparedStatement.executeUpdate();
			errorCode = 0;

		} catch (SQLException sqle) {
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				errorCode = 1;
			} else {
				System.out.println("\n{\"errorCode\": 0}\n");
				errorCode = -1;
			}

		} finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}

		return errorCode;
	}
	
	public int deleteStudent(int studentID)
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int errorCode = -1;

		try {
			connection = openConnection();

			String sqlText = "DELETE FROM Student WHERE id = ?";

			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, studentID);

			int rowsAffected = preparedStatement.executeUpdate();
			
			if (rowsAffected == 1)
			{
				errorCode = 0;
			} else if (rowsAffected == 0)
			{
				errorCode = 1;
			}

		} catch (SQLException sqle) {
			if (sqle.getErrorCode() == ConnectionParameters.PK_VIOLATION_ERROR) {
				System.out.println("\n[ERROR] StudentDAO: deleteStudent() failed. " + sqle.getMessage() + "\n");
			}
		} finally {
			DbUtils.closeQuietly(preparedStatement, connection);
		}

		return errorCode;
	}

}