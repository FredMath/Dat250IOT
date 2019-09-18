package entities;

import javax.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {

    @TableGenerator(
            name = "yourTableGenerator",
            allocationSize = 1,
            initialValue = 1)

    @Id
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
