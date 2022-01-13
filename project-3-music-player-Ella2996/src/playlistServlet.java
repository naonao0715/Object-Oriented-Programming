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
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.Cookie;
public class playlistServlet extends HttpServlet {
    private Connection connection = null;

    public void init() throws ServletException {
        // Do required initialization

        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:music.db");
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }

    }

    public String getContent() {
        String result = "";
        try {
            Scanner sc = new Scanner(new File("src/playlist.html"));

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

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("playlist");

        response.setContentType("text/html");


        PrintWriter out = response.getWriter();
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            if (name.length() > 0) {
                String totalCnt = "SELECT COUNT(*) as count FROM playlist";
                ResultSet rsCnt = statement.executeQuery(totalCnt);
                int count = rsCnt.getInt("count");
                System.out.println(count);
                String insertSQL = "insert into playlist values ('" + Integer.toString(count) + "', '" + name + "')";

                statement.executeUpdate(insertSQL);
            }


            String selectSQL = "select * from playlist";
            ResultSet rs = statement.executeQuery(selectSQL);

            StringBuilder sb = new StringBuilder();

            sb.append("<b> Playlist: <br /b>");

            while (rs.next()) {

                String songName = rs.getString("name");
                sb.append("<b>" + songName + "<br /b>");
            }

            out.println(sb.toString());
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }





    }
}
