<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Idea</title>
</head>
<body>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Idea</title>
</head>
<body>

<h2>Edit Idea</h2>

<form action="UpdateServlet" method="post">

    <input type="hidden" name="id" value="<%= request.getAttribute("info_id") %>">

    <label>Domain:</label><br>
    <input type="text" name="domain"
           value="<%= request.getAttribute("domain") %>"><br><br>

    <label>Info:</label><br>
    <textarea name="info"><%= request.getAttribute("info") %></textarea><br><br>

    <button type="submit">Update</button>

</form>

</body>
</html>

</body>
</html>