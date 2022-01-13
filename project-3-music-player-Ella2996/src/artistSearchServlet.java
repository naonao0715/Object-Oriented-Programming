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
public class artistSearchServlet extends HttpServlet {
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
            Scanner sc = new Scanner(new File("src/artistSearch.html"));

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
        String name = request.getParameter("artist");

        response.setContentType("text/html");


        PrintWriter out = response.getWriter();
        boolean isValid = false;
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String selectArtistSQL = "select * from artists where name == '" + name + "'";
            ResultSet rs = statement.executeQuery(selectArtistSQL);

            StringBuilder sb = new StringBuilder();

            if (rs.next()) {
                isValid = true;
                int id = rs.getInt("id");
                int numSongs = rs.getInt("songs");


                sb.append("<b> Artist: " +  name  + "<br /b>");


                String selectAlbumForArtistSQL = "select * from albums where artist = '" + Integer.toString(id) +"'";
                ResultSet rsAlbum = statement.executeQuery(selectAlbumForArtistSQL);
                sb.append("<b> Albums: <br /b>");
                while (rsAlbum.next()) {
                    String albumName = rsAlbum.getString("name");
                    sb.append("<b>" + albumName + "<br /b>");
                }

                String selectSongsForArtistSQL = "select * from songs where artist = '" + Integer.toString(id) +"'";
                ResultSet rsSongs = statement.executeQuery(selectSongsForArtistSQL);
                sb.append("<b> Song: <br /b>");
                while (rsSongs.next()) {
                    String songName = rsSongs.getString("name");
                    sb.append("<b>" + songName + "<br /b>");
                }
                out.println(sb.toString());


            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        if (!isValid) {
            out.println("no such artist");
            return;
        }





    }
}
