import java.util.ArrayList;
public class testArtist {
    public static void main(String[] args) {

        Artist artist = new Artist();
        artist.setName("Mei Mei");

        Song s1 = new Song();
        s1.setName("AA");
        s1.setRunningTime("07:15");
        s1.setArtist(artist);

        Song s2 = new Song();
        s2.setName("BB");
        s2.setArtist(artist);

        ArrayList<Song> songs = new ArrayList<>();
        songs.add(s1);
        songs.add(s2);

        artist.setSongs(songs);

        System.out.println(artist.getName());
        System.out.println(artist);


        Artist artist1 = new Artist();
        artist1.setName("Ge Ge");
        System.out.println(artist.equals(artist));
        System.out.println(artist.equals(artist1));

    }
}
