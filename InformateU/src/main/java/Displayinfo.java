

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/Displayinfo")
public class Displayinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Displayinfo() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("Displayinfo servlet called");
		    HttpSession session = request.getSession(false);
		    response.setContentType("text/html");
		    
			PrintWriter out = response.getWriter();

	        if (session == null || session.getAttribute("userId") == null) {
	            response.sendRedirect("login.html");
	            return;
	        }

	        int userId = (int) session.getAttribute("userId");

	        List<Map<String, String>> list = new ArrayList<>();

	        try {

	            Class.forName("com.mysql.cj.jdbc.Driver");


	            Connection con = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/sample", "root", "D5e669yr@888"
	            );

	            PreparedStatement ps = con.prepareStatement(
	                "SELECT info_id,domain, info FROM info_table WHERE user_id=? ORDER BY info_id ASC"
	            );

	            ps.setInt(1, userId);

	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                Map<String, String> row = new HashMap<>();
	                row.put("info_id", String.valueOf(rs.getInt("info_id")));
	                row.put("domain", rs.getString("domain"));
	                row.put("info", rs.getString("info"));
	     

	                list.add(row);
	            }

	            request.setAttribute("data", list);
	            request.getRequestDispatcher("myidea.jsp").forward(request, response);
	            out.print("hiiiii");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
