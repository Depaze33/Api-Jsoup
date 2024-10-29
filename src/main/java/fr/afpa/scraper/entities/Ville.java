package fr.afpa.scraper.entities;

import jakarta.persistence.*;

import java.util.List;

public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "city")
    private List<Venue> venues;

    @ManyToOne
    @JoinColumn(name = "id_department")
    private Department department;

    public Ville(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Ville() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(Department departement) {
    }
}
