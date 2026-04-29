

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.*;
@WebServlet("/Updateserv")
public class Updateserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Updateserv() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String domain = request.getParameter("domain");
        String info = request.getParameter("info");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sample", "root", "password");

            PreparedStatement ps = con.prepareStatement(
                "UPDATE info_table SET domain=?, info=? WHERE info_id=?");

            ps.setString(1, domain);
            ps.setString(2, info);
            ps.setInt(3, id);

            ps.executeUpdate();

            response.sendRedirect("Displayinfo"); // back to My Ideas

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
