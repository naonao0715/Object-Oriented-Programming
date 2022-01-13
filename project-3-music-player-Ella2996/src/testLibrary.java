import java.util.ArrayList;

public class testLibrary {
    public static void main(String[] args) {
        Library l1 = new Library();
        ArrayList<Song> songs = new ArrayList<>();
        ArrayList<Artist> artists = new ArrayList<>();
        ArrayList<Album> albums = new ArrayList<>();

        Artist artist = new Artist();
        artist.setName("Mei Mei");

        Song s1 = new Song();
        s1.setName("AA");
        s1.setRunningTime("07:15");
        s1.setArtist(artist);

        Song s2 = new Song();
        s2.setName("BB");
        s2.setArtist(artist);


        songs.add(s1);
        songs.add(s2);

        artist.setSongs(songs);



        Artist artist1 = new Artist();
        artist1.setName("Ge Ge");

        artists.add(artist);
        artists.add(artist1);

        Album album = new Album();
        album.setName("XiaoMoTuo");
        album.setTotalLength("07:15");
        album.setSongs(songs);
        album.setArtist(artist);

        Album album2 = new Album("XiaoGuJi");

        albums.add(album);
        albums.add(album2);
        ArrayList<Album> albumForMeimei = new ArrayList<>();
        albumForMeimei.add(album);
        artist.setAlbums(albumForMeimei);

        l1.setAlbums(albums);
        l1.setArtists(artists);
        l1.setSongs(songs);
        l1.display();


        l1.writeToFile("./src/test.csv");
        l1.add(s1);
        l1.display();

        l1.delete(s1);
        l1.display();

        l1.add(artist);
        l1.delete(artist1);

        l1.add(album);
        l1.delete(album);
        l1.display();

        System.out.println(l1.findDuplicate(artist));
        System.out.println(l1.findDuplicate(s1));

        Library l2 = new Library();
        l2.readFromFile("./src/infile.csv");
        l2.display();
        l2.writeToFile("./src/outfile.csv");



    }
}
