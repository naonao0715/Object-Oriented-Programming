import java.util.ArrayList;

public class testAlbum {
    public static void main(String[] args) {
        Album album = new Album();
        album.setName("XiaoMoTuo");
        System.out.println(album.getName());
        album.setTotalLength("07:15");
        System.out.println(album.getTotalLength());

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

        album.setSongs(songs);
        album.setArtist(artist);
        System.out.println(album);

        Album album2 = new Album("XiaoGuJi");
        System.out.println(album.equals(album));
        System.out.println(album.equals(album2));



    }
}
