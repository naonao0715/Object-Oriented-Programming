import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    ArrayList<Song> songs;
    ArrayList<Album> albums;
    ArrayList<Artist> artists;

    public Library() {
        songs = new ArrayList<Song>();
        albums = new ArrayList<Album>();
        artists = new ArrayList<Artist>();
    }

    /* add setters and getters */

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    /* you complete this. Return an empty Album if the search fails. If there is more than one match, return the first */
    public Album findAlbum(String name) {
        for (Album a: albums) {
            if (a.name.equals(name)) {
                return a;
            }
        }
        return new Album();
    }

    /* you complete this. Return an empty Artist if the search fails. If there is more than one match, return the first*/
    public Artist findArtist(String name) {
        for (Artist a: artists) {
            if (a.name.equals(name)) {
                return a;
            }
        }
        return new Artist();
    }

    /* you complete this. Return an empty Song if the search fails. If there is more than one match, return the first*/
    public Song findSong(String name, Artist a) {
        for (Song s: songs) {
            if (s.name.equals(name) && s.getArtist().equals(a)) {
                return s;
            }
        }
        return new Song();
    }

    /* you complete this. */
    public void add(Entity e) {
        if (e instanceof Song) {
            songs.add((Song)e);
        } else if (e instanceof Artist) {
            artists.add((Artist)e);
        } else {
            albums.add((Album)e);
        }
    }
    /* you complete this */
    public void delete(Entity e) {
        if (e instanceof Song) {
            songs.remove((Song)e);
        } else if (e instanceof Artist) {
            artists.remove((Artist)e);
        } else {
            albums.remove((Album)e);
        }
    }

    /* you complete this. Print out the library in a pretty, user-friendly way. */
    public void display() {
        System.out.println("Library");
        for (Song s: songs) {
            System.out.println(s.toString());
        }
        for (Album a: albums) {
            System.out.println(a.toString());
        }
        for (Artist a: artists) {
            System.out.println(a.toString());
        }
    }

    /* you complete this. Return the first match, using the equals() method to determine if something is a duplicate.
        Return an empty Entity if no match is found.
    */

    public Entity findDuplicate(Entity e) {
        boolean isDuplicate = false;
        if (e instanceof Song) {

            for (Song s: songs) {
                if (s.equals((Song)e)) {
                    if (!isDuplicate) {
                        isDuplicate = true;
                    } else {
                        return s;
                    }
                }
            }
        } else if (e instanceof Artist) {
            for (Artist a: artists) {
                if (a.equals((Artist) e)) {
                    if (!isDuplicate) {
                        isDuplicate = true;
                    } else {
                        return a;
                    }

                }
            }
        } else {
            for (Album a: albums) {
                if (a.equals((Album) e)) {
                    if (!isDuplicate) {
                        isDuplicate = true;
                    } else {
                        return a;
                    }

                }
            }
        }
        return new Entity();
    }

    /* you complete this. Read from a file that has a CSV format like:
    Here Comes the Sun, The Beatles, Abbey Road, 3:22
    Tomorrow Never Knows, The Beatles, Revolver, 2:56

     */
    public void readFromFile(String f) {

        Scanner s;
        File infile = new File(f);
        try {
            s = new Scanner(infile);
            String buf;
            while (s.hasNext()) {
                buf = s.nextLine();
                if (buf.startsWith("Song: ")) {
                    String[] stuff = buf.substring(6).split(", ");
                  //  for (int i = 0; i < stuff.length; i++)
                   // {
                     //   System.out.println(stuff[i]);
                    // }
                    Song newSong = new Song();
                    newSong.setName(stuff[0]);

                    if (stuff.length > 1) {
                        // if cannot find the artist, otherwise use existing oneo
                        Artist artist = findArtist(stuff[1]);
                        newSong.setArtist(artist);
                        if (artist.getName().isEmpty()) {
                            artist.setName(stuff[1]);
                            artists.add(artist);
                        }
                    }

                    if (stuff.length > 2) {
                        int playtimes = Integer.parseInt(stuff[2]);
                        newSong.setTimesPlayed(playtimes);
                    }
                    if (stuff.length > 3) {
                        newSong.setRunningTime(stuff[3]);
                    }
                    songs.add(newSong);

                }
                else if (buf.startsWith("Album: ")) {
                    int albumIdx = buf.indexOf("Album: ");
                    int artistIdx = buf.indexOf("Artist: ");
                    int songIdx = buf.indexOf("Songs: ");
                    Album newAlbum = new Album();
                    newAlbum.setName(buf.substring(albumIdx + 7, artistIdx - 1));
                    String artistName = buf.substring(artistIdx + 8, songIdx - 1);
                    if (!artistName.isEmpty()) {
                        Artist artist = findArtist(artistName);
                        if (artist.getName().isEmpty()) {
                            artist.setName(artistName);
                            artists.add(artist);
                        }
                        newAlbum.setArtist(artist);
                    }
                    albums.add(newAlbum);
                    ArrayList<Song> albumSongs = new ArrayList<>();
                    String[] songNames = buf.substring(songIdx + 7).split(", ");
                    for (int k = 0; k < songNames.length; k++) {
                        if (songNames[k].isEmpty())
                            continue;
                        Song song = findSong(songNames[k], newAlbum.getArtist());
                        if (song.getName().isEmpty()) {
                            song.setName(songNames[k]);
                            song.setArtist(newAlbum.getArtist());
                            songs.add(song);
                        }
                        albumSongs.add(song);
                    }
                    newAlbum.setSongs(albumSongs);
                }
                else {
                    int albumIdx = buf.indexOf("Albums: ");
                    int artistIdx = buf.indexOf("Artist: ");
                    int songIdx = buf.indexOf("Songs: ");
                    String artistName = buf.substring(artistIdx + 8, songIdx - 1);
                    Artist artist = findArtist(artistName);
                    if (artist.getName().isEmpty()) {
                        artist.setName(artistName);
                        artists.add(artist);
                    }
                    ArrayList<Song> albumSongs = new ArrayList<>();
                    String[] songNames = buf.substring(songIdx + 7, albumIdx).split(", ");
                    for (int k = 0; k < songNames.length; k++) {
                        if (songNames[k].isEmpty())
                            continue;
                        Song song = findSong(songNames[k], artist);
                        if (song.getName().isEmpty()) {
                            song.setName(songNames[k]);
                            song.setArtist(artist);
                            songs.add(song);
                        }
                        albumSongs.add(song);
                    }
                    artist.setSongs(albumSongs);

                    ArrayList<Album> albumsAll = new ArrayList<>();
                    String[] albumNames = buf.substring(albumIdx + 8).split(", ");
                    for (int k = 0; k < albumNames.length; k++) {
                        if (albumNames[k].isEmpty())
                            continue;
                        Album newAlbum = findAlbum(albumNames[k]);
                        if (newAlbum.getName().isEmpty()) {
                            newAlbum.setName(albumNames[k]);
                            albums.add(newAlbum);
                        }
                        albumsAll.add(newAlbum);
                    }
                    artist.setAlbums(albumsAll);

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* write the data out to a file in the exact same format. */
    public void writeToFile(String name) {
        PrintWriter outfile;
        try {
            outfile = new PrintWriter(name);
            for (Song s: songs) {
                outfile.println(s.toString());
            }
            for (Album a: albums) {
                outfile.println(a.toString());
            }
            for (Artist a: artists) {
                outfile.println(a.toString());
            }
            outfile.close();
        } catch (Exception e) {
            System.out.println("Unable to open file.");
            return;
        }
    }



}
