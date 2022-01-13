import java.util.ArrayList;

public class Artist extends Entity {
    ArrayList<Song> songs;
    ArrayList<Album> albums;

    /* you complete this */
    public Artist() {
        this("");
        songs = new ArrayList<>();
        albums = new ArrayList<>();
    }

    /* you complete this */
    public Artist(String name) {
        super(name);
        songs = new ArrayList<>();
        albums = new ArrayList<>();
    }

    /* add setters and getters */

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }


    /* you complete this */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Artist: ");
        sb.append(getName() + " ");
        // add songs
        sb.append("Songs: ");
        for (Song s: songs) {
            sb.append(s.name + ", ");
        }
        sb.append("Albums: ");
        for (Album a: albums) {
            sb.append(a.name + ", ");
        }
        return sb.toString();
    }

    /* you complete this. Assume that two artists are equal if they have the same name and the same album*/
    public boolean equals(Artist artist) {
        if (name.equals(artist.getName()) && this.albums.containsAll(artist.getAlbums())
                && artist.getAlbums().containsAll(this.albums)) {
            return true;
        } else {
            return false;
        }
    }


}
