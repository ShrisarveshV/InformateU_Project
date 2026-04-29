

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.*;
import java.util.*;

@WebServlet("/Editinfoserv")
public class Editinfoserv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Editinfoserv() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("info_id");

        if (idStr == null || idStr.isEmpty()) {
            response.getWriter().println("ID missing");
            return;
        }

        int id = Integer.parseInt(idStr);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sample", "root", "password");

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM info_table WHERE info_id=?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                request.setAttribute("info_id", id);
                request.setAttribute("domain", rs.getString("domain"));
                request.setAttribute("info", rs.getString("info"));

                request.getRequestDispatcher("edit.jsp").forward(request, response);
            } else {
                response.getWriter().println("No record found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
