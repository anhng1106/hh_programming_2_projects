package student_servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import data_access.StudentDAO;

@WebServlet("/deleteStudent")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		StudentDAO studentDao = new StudentDAO();

		int id = Integer.parseInt(request.getParameter("id"));

		int errorCode = studentDao.deleteStudent(id);

		Gson gson = new Gson();
		
		String json = gson.toJson(new Status(errorCode)); // 3.
		
		out.print(json);
	}

	public class Status {
		private int errorCode;
		public Status(int errorCode) {
		this.errorCode = errorCode;
	 }
}
}
