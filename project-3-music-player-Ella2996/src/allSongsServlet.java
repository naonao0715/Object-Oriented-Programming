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

public class allSongsServlet extends HttpServlet{
    Library myLibrary;
    private Connection connection = null;

    public void init() throws ServletException {
        // Do required initialization
        myLibrary = new Library();
        myLibrary.readFromFile("./src/infile.csv");

        try
        {
            connection = DriverManager.getConnection("jdbc:sqlite:music.db");
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }

    }
    public String toHTML(String song, String artist, String album) {
        return "<tr><td> " + song + "</td><td> " + artist + "</td><td> " + album  + "</td></tr>";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");

        // StringBuilder

        PrintWriter out = response.getWriter();

        String cookieVal = "";
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            out.println("<h1>" + " please log in " + "</h1>");
            out.println("<a href=\"/login\">log in</a>");
            return;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("name")) {
                cookieVal = cookies[i].getValue();
            }
        }

        if (cookieVal.equals("")) {

            out.println("<h1>" + " please log in " + "</h1>");
            out.println("<a href=\"/login\">log in</a>");
           return;
        } else if (cookieVal.equals("meimei")) {
            out.println("it's meimei<br>");
        } else if (cookieVal.equals("gege")) {
            out.println("it's gege<br>");
        } else {
            out.println("others<br>");
        }


        StringBuilder sb = new StringBuilder();

        try
        {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String songSQL = "select songs.name as song, artists.name as artist, albums.name as album from songs join artists on songs.artist = artists.id join albums on songs.album = albums.id;";

            ResultSet rs = statement.executeQuery(songSQL);

            while (rs.next()) {
                String songName = rs.getString("song");
                String artistName = rs.getString("artist");
                String albumName = rs.getString("album");
                sb.append(toHTML(songName, artistName, albumName)+ "<br>");

            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }

        // Actual logic goes here.
        out.println(sb.toString());
        out.println(cookieVal);
    }
}
