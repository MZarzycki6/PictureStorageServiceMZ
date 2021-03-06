package Api;


import javax.persistence.*;
/* Standardowa definicja obiektu obrazka*/
@Entity
@Table
public class Picture {

    @Id
    @SequenceGenerator(
            name= "picture_sequence",
            sequenceName = "picture_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator = "picture_sequence"
    )
    private Long id;
    private String name;
    private String description;
    @Lob
    @Column
    private String encodedImage;

    public Picture (){}
    public Picture(Long id, String name, String description, String encodedImage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.encodedImage = encodedImage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

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

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", encodedImage='" + encodedImage + '\'' +
                '}';
    }
}
