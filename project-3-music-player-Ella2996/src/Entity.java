import java.time.LocalDate;
import java.util.Objects;

public class Entity {
    String name;
    LocalDate dateCreated;

    public Entity() {
        name="";
        dateCreated = LocalDate.now();
    }

    public Entity(String n) {
        name = n;
        dateCreated = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    /* Add setters and getters */

    public boolean equals(Entity otherEntity) {
        if (name.equals(otherEntity.getName()) && dateCreated.equals(otherEntity.getDateCreated()))
            return true;
        return false;
    }


    /* you complete this */
    public String toString() {
        return "name: " + name + " LocalDate: " + dateCreated;
    }
}
