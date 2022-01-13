
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class loginServlet extends HttpServlet {
    private Connection connection = null;
    public void init() throws ServletException {
        // Do required initialization
        // create table Users (id INTEGER NOT NULL PRIMARY KEY, username VARCHAR(50) NOT NULL, password VARCHAR(50) NOT NULL);
        // insert into Users values (1, "meimei", "0715");
        // insert into Users (id, username, password) values (2, "gege", "0715");
        // insert into Users (id, username, password) values (3, "ivan", "yzt");
        // insert into Users (id, username, password) values (4, "ivan", "wqy");


        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:music.db");
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }



    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("username");
        String pw = request.getParameter("password");

        response.setContentType("text/html");


        PrintWriter out = response.getWriter();
        boolean isValid = false;
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String selectUserSQL = "select * from Users where username == '" + name + "'";
            ResultSet rs = statement.executeQuery(selectUserSQL);

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");

                if (password.equals(pw)) {
                    isValid = true;
                    break;
                }
            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        if (!isValid) {
            out.println("no such user or user password is not correct");
            return;
        }

        Cookie cookie = new Cookie("name", name);
        cookie.setMaxAge(30);
        // cookie.setMaxAge(60*60*24*365);
        response.addCookie(cookie);

        String resp = "<b>login success<br /b><b>" + name + "<br /><b>" + pw + "</b>";

        out.println(resp);


    }

    public String getContent() {
        String result = "";
        try {
            Scanner sc = new Scanner(new File("src/sampleForm.html"));

            while (sc.hasNextLine()) {
                result += sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return result;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String content = getContent();
        out.println(content);
    }
}
