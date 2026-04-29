

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;
import java.sql.*;
@WebServlet("/Homeserv")
public class Homeserv extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Homeserv() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

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
            	    "SELECT info_id, domain, info FROM info_table ORDER BY info_id DESC"
            	);

            
            System.out.println("Logged user ID: " + userId);
            System.out.println("Data size: " + list.size());

 

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("info_id", String.valueOf(rs.getInt("info_id")));
                row.put("domain", rs.getString("domain"));
                row.put("info", rs.getString("info"));

                list.add(row);
                System.out.println(row);
            }
            

            request.setAttribute("data", list);
            request.getRequestDispatcher("homepg.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
