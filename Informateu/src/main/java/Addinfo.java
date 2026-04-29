

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
@WebServlet("/Addinfo")
public class Addinfo extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		HttpSession session = request.getSession(false); // don't create new session

		if(session == null) {
		    System.out.println("Session is NULL");
		    response.sendRedirect("login.html");
		    return;
		}

		Integer id = (Integer) session.getAttribute("userId");
		if(id == null) {
		    response.sendRedirect("login.html");
		    return;
		}

        String info_domain = request.getParameter("info_domain");
        String info = request.getParameter("info");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sample", "root", "D5e669yr@888"
            );

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO info_table(user_id, domain,info) VALUES (?, ?,?)"
            );

            ps.setInt(1, id);
            ps.setString(2, info_domain);
            ps.setString(3, info);

            int result = ps.executeUpdate();

            if (result > 0) {
                response.sendRedirect("Displayinfo");
            } else {
                response.sendRedirect("error.html");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
