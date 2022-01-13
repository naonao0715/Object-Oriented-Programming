
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
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class loginServlet extends HttpServlet {
    private HashMap<String, String> users;
    public void init() throws ServletException {
        // Do required initialization
        users = new HashMap<>();
        Scanner s;
        File infile = new File("./src/users");
        try {
            s = new Scanner(infile);
            String buf;
            while (s.hasNext()) {
                buf = s.nextLine();
                buf.strip();
                String name = buf.split("\\s+")[0];
                String pwd = buf.split("\\s+")[1];
                users.put(name, pwd);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("username");
        String pw = request.getParameter("password");

        response.setContentType("text/html");


        PrintWriter out = response.getWriter();
        if (!users.containsKey(name) || !users.get(name).equals(pw)) {
            out.println("no such user or user password is not correct");
            return;
        }


        Cookie cookie = new Cookie("name", name);
        //cookie.setMaxAge(30);
        cookie.setMaxAge(60*60*24*365);
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
