import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.Cookie;

public class allSongsServlet extends HttpServlet{
    Library myLibrary;
    public void init() throws ServletException {
        // Do required initialization
        myLibrary = new Library();
        myLibrary.readFromFile("./src/infile.csv");

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
        List<Song> songs = myLibrary.getSongs();
        for (Song song: songs) {
            sb.append(song.toHTML() + "<br>");
        }



        // Actual logic goes here.
        out.println(sb.toString());
        out.println(cookieVal);
    }
}
