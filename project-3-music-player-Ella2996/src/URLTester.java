// Illustrates how to do an HTTP GET

import javax.net.ssl.HttpsURLConnection;
import java.io.InputStream;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class URLTester {

    /* you do this one. */
    /* let's assume a word is junk if it contains anything except a letter. */
    //  apple17
    public static boolean isJunk(String word) {
        int numC = word.length();
        for (int i = 0; i < numC; i++) {
            if (!Character.isLetter(word.charAt(i)))
                return true;
        }
        return false;
    }

    /* you do this one. */
    /* remove trailing punctuation. You can assume that there is at most one punctuation character at the end of the word*/
    //apple,.
    public static String stripPunctuation (String w) {
        int num = w.length();
        char c = w.charAt(num - 1);
        if (!Character.isLetter(c)) {
            return w.substring(0, num - 1);
        }
        return w;
    }
    public static void main(String[] args) throws IOException {
        String urlString = "https://www.wunderground.com/weather/us/ca/los-altos/KCALOSAL167";

        URL u = new URL(urlString);
        URLConnection connection = u.openConnection();


        HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
        int code = httpsConnection.getResponseCode();

        String message = httpsConnection.getResponseMessage();
        System.out.println(code + " " + message);
        if (code != HttpURLConnection.HTTP_OK) {
            return;
        }

        InputStream instream = connection.getInputStream();
        Scanner in = new Scanner(instream);

        while (in.hasNextLine()) {
            String data = in.nextLine();
            String[] pieces = data.split("\\s+");
            boolean isValid = false;
            for (String word: pieces) {
                if (word.isEmpty())
                    continue;;
                word = word.toLowerCase();
                word = stripPunctuation(word);
                if (isJunk(word))
                    continue;

                if (word.isEmpty())
                    continue;
                isValid = true;
                System.out.print((word + " "));
            }
            if (isValid)
                System.out.println();
        }
        /*
        String word;
        while (in.hasNext()) {
            word = in.next();
            word = word.toLowerCase();
            word = stripPunctuation(word);
            if (isJunk(word))
                continue;

            if (word.isEmpty())
                continue;
            System.out.print(word + " ");
        }*/

    }
}