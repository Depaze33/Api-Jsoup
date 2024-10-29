package fr.afpa.scraper.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Ville> ville;


    public Department(int id, String name, List<Ville> ville) {
        this.id = id;
        this.name = name;
        this.ville = ville;
    }

    public Department() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Ville> getVille() {
        return ville;
    }

    public void setVille(List<Ville> ville) {
        this.ville = ville;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}