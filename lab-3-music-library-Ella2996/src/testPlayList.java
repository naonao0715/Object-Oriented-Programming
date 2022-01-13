public class testPlayList {
    public static void main(String[] args) {
        Playlist pl = new Playlist();
        Song s1 = new Song();
        s1.setName("AA");
        Song s2 = new Song();
        s2.setName("BB");
        Song s3 = new Song();
        s3.setName("CC");
        pl.add(s1);
        pl.add(s2);
        pl.add(s3);
        System.out.println(pl);
        pl.shuffle();
        System.out.println(pl);
        pl.remove(s1);
        System.out.println(pl);

    }
}
