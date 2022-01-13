import java.awt.*;

public class testEntity {
    public static void main(String[] args) {
        Entity e1 = new Entity("Gege");
        Entity e2 = new Entity("Meimei");
        System.out.println(e1.getName());
        System.out.println(e1.getDateCreated());
        System.out.println(e1);
        System.out.println(e1.equals(e1));
        System.out.println(e1.equals(e2));


    }
}
