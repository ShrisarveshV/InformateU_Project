

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.*;

@WebServlet("/Deleteinfo")
public class Deleteinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String infoId = request.getParameter("info_id");

        if(infoId == null || infoId.isEmpty()){
            response.getWriter().println("Invalid ID");
            return;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sample", "root", "D5e669yr@888"
            );

            PreparedStatement ps = con.prepareStatement(
                "DELETE FROM info_table WHERE info_id=?"
            );

            ps.setInt(1, Integer.parseInt(infoId));

            int rows = ps.executeUpdate();

            if(rows > 0){
                response.sendRedirect("Displayinfo");
            } else {
                response.getWriter().println("No record found with this ID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
