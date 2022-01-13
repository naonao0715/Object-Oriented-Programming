/* This class represents a Song */

public class Song extends Entity {
    String filename;
    Artist artist;
    int timesPlayed;
    String runningTime;

    /* you complete this */
    public Song() {
        this("", "", new Artist(),0, "");
    }

    /* you complete this */
    public Song(String title, String filename, Artist artist, int timesPlayed, String runningTime) {
        super(title);
        this.filename = filename;
        this.artist = artist;
        this.timesPlayed = timesPlayed;
        this.runningTime = runningTime;
    }

    public Artist getArtist() {
        return artist;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public String getFilename() {
        return filename;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    /* add setters and getters */

    /* you complete this */
    public String toString() {
        return "Song: " + getName() + ", " + artist.getName() + ", " + timesPlayed + ", " + runningTime;
    }

    /* you complete this. Assume that two songs are equal if they have the same name and the same artist. */
    public boolean equals(Song song) {
        if (name.equals(song.name) && artist.equals(song.artist)) {
            return true;
        } else {
            return false;
        }
    }

}
