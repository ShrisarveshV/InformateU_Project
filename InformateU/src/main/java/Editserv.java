

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Editserv")
public class Editserv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("info_id"));
		String info = request.getParameter("info");

        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sample", "root", "D5e669yr@888");

            String query = "UPDATE info_table SET info=? WHERE info_id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, info);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.print("Updated successfully!");
            } else {
                out.print("No record found with that ID");
            }

            con.close();

        } catch (Exception e) {
        		e.printStackTrace();
            response.getWriter().print("Error: " + e.getMessage());
        }
	}

}
