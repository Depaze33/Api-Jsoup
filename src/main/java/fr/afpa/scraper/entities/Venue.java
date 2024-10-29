package fr.afpa.scraper.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "venue")
public class Venue {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String address;

    @Column(name = "url_image")
    private String urlImage;

    @OneToMany(mappedBy = "venue")
    private List<Event> events;

    // Relation ManyToOne avec City (1,1 - 0,n)
    @ManyToOne
    @JoinColumn(name = "id_city")
    private City city;

    public Venue() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
