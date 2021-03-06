
import java.util.ArrayList;
import java.util.Collections;

public class Playlist extends Entity {
    ArrayList<Song> songs;

    public Playlist() {
        super();
        this.songs = new ArrayList<Song>();
    }

    public Playlist(String n) {
        super(n);
        this.songs = new ArrayList<Song>();
    }

    /* add setters and getters here */

    /* add a new song.  */
    public void add(Song newSong) {
        songs.add(newSong);
    }

    /* remove Song s from the playlist */
    public void remove(Song s) {
        if (songs.contains(s)) {
            songs.remove(s);
        }
    }

    /* shuffle - randomly reorder the playlist in place. */
    public void shuffle() {
        Collections.shuffle(songs);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Song: ");
        for (Song s: songs) {
            sb.append(s.name + ", ");
        }
        return sb.toString();

    }

}
