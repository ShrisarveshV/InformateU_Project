

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int id;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		String username = request.getParameter("username");
        String password = request.getParameter("password");

        Cookie userCookie = new Cookie("username", username);
        
        userCookie.setMaxAge(60 * 60);

        response.addCookie(userCookie);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sample", "root", "D5e669yr@888"
            );

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?"
            );

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                int id = rs.getInt("id");

                HttpSession session = request.getSession();
                session.setAttribute("userId", id);
                response.sendRedirect("Homeserv");
                return;
            } else {
                request.setAttribute("error", "Invalid Username or Password");
                request.getRequestDispatcher("lerror.html").forward(request, response);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
		
		
        }
        
	}

}
