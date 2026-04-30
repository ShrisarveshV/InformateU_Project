import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/GetInfo")
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set response headers to return JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			// Load MySQL Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Connect to the database using your existing credentials
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample", "root", "D5e669yr@888");
			Statement st = con.createStatement();
			
			// Execute query to get data from the 'info' table
			ResultSet rs = st.executeQuery("SELECT user_id, info_domain, info FROM info");
			
			// Construct a JSON array manually
			StringBuilder json = new StringBuilder();
			json.append("[");
			boolean first = true;

			while(rs.next()) {
				if(!first) {
					json.append(",");
				}
				first = false;
				
				String userId = rs.getString("user_id");
				String infoDomain = rs.getString("info_domain");
				String info = rs.getString("info");
				
				// Escape double quotes and newlines for valid JSON format
				userId = userId != null ? userId.replace("\"", "\\\"").replace("\n", "\\n") : "";
				infoDomain = infoDomain != null ? infoDomain.replace("\"", "\\\"").replace("\n", "\\n") : "";
				info = info != null ? info.replace("\"", "\\\"").replace("\n", "\\n") : "";
				
				json.append("{")
					.append("\"user_id\":\"").append(userId).append("\",")
					.append("\"info_domain\":\"").append(infoDomain).append("\",")
					.append("\"info\":\"").append(info).append("\"")
					.append("}");
			}
			
			json.append("]");
			out.print(json.toString());
			
			rs.close();
			st.close();
			con.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.print("[]");
		}
	}
}
