import java.awt.*;

public class testSong {
    public static void main(String[] args) {
        Playlist pl = new Playlist();
        Song s1 = new Song();
        s1.setName("AA");
        s1.setArtist(new Artist("Meimei"));
        s1.setRunningTime("07:15");
        System.out.println(s1.getName());
        System.out.println(s1.getArtist());
        System.out.println(s1);
        Song s2 = new Song();
        s2.setName("BB");
        s2.setArtist(new Artist("Gege"));
        System.out.println(s1.equals(s1));
        System.out.println(s1.equals(s2));



    }
}
