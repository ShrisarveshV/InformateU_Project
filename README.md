InformateU – Idea Sharing Platform

Overview:
InformateU is a web-based idea sharing platform where users can post, explore, edit, and manage ideas.
Built using Java Servlets, JSP, and MySQL.

Features:
- User Login (Session-based authentication)
- Add Ideas
- View All Ideas
- My Ideas
- Edit Ideas
- Delete Ideas
- Domain Filtering
- Dashboard UI

Tech Stack:
Frontend: HTML, CSS, JSP
Backend: Java Servlets
Database: MySQL
Server: Apache Tomcat

Project Structure:
src/main/java/
- LoginServlet.java
- Homeserv.java
- InfoServlet.java
- Displayinfo.java
- Editinfoserv.java
- UpdateServlet.java
- Deleteinfo.java

src/main/webapp/
- login.html
- homepg.jsp
- myidea.jsp
- edit.jsp
- info.html
- success.html
- error.html

Database Setup:
CREATE DATABASE sample;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50)
);

CREATE TABLE info_table (
    info_id INT AUTO_INCREMENT PRIMARY KEY,
    domain VARCHAR(50),
    info TEXT,
    user_id INT
);

How to Run:
1. Import into Eclipse/IntelliJ
2. Configure Tomcat
3. Add MySQL JDBC Driver
4. Run project
5. Open browser:
http://localhost:8080/YourProject/login.html

Author:
Shri Sarvesh V

