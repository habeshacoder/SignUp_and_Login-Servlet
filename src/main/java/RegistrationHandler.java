
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistrationHandler")
public class RegistrationHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationHandler() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");

		String password = request.getParameter("password");

		String sex = request.getParameter("sex");

		String[] favfoods = request.getParameterValues("food");

		String nationality = request.getParameter("nationality");
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/usersinfo", "root", "admin");
			PreparedStatement pst = con
					.prepareStatement("insert into users(username,passwod,sex,nationality,foods) values(?,?,?,?,?)");
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, sex);
			pst.setString(4, nationality);
			String favfood = "";
			for (String food : favfoods) {
				favfood = favfood + food + ", ";
			}
			pst.setString(5, favfood);
			int rowcount = pst.executeUpdate();
			if (rowcount > 0) {
				response.sendRedirect("/ServletWebApp/login.html");
			} else {
				response.sendRedirect("/ServletWebApp/index.html");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		
	}
}
