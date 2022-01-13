import javax.lang.model.type.ArrayType;
import java.util.ArrayList;

public class Album extends Entity {
    ArrayList<Song> songs;
    Artist artist;
    String totalLength;
    /* you complete this */
    public Album() {
        songs = new ArrayList<>();
        artist = new Artist();
        totalLength = "";
    }

    /* you complete this */
    public Album(String n, ArrayList<Song> songs, Artist artist, String totalLength) {
        super(n);
        this.songs = songs;
        this.artist = artist;
        this.totalLength = totalLength;
    }

    /* you complete this */;
    public Album(String n) {
        super(n);
        songs = new ArrayList<>();
        artist = new Artist();
        totalLength = "";
    }

    /* add setters and getters here */

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public String getTotalLength() {
        return totalLength;
    }

    public String getName() {
        return name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setTotalLength(String totalLength) {
        this.totalLength = totalLength;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    /* you complete this */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Album: ");
        sb.append(getName() + " ");
        sb.append("Artist: ");
        sb.append(artist.getName() + " ");
        // add songs
        sb.append("Songs: ");
        for (Song s: songs)
        {
            sb.append(s.name + ", ");
        }
        return sb.toString();
    }

    /* you complete this. Assume that two albums are equal if they have the same name and the same artist and songs. */
    public boolean equals(Album album) {
        if (name.equals(album.name) && artist.equals(album.artist) && songs.containsAll(album.getSongs())
        && album.getSongs().containsAll(songs)) {
            return true;
        }
        return false;
    }


}
