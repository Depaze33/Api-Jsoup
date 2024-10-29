package fr.afpa.scraper.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String price;
    private LocalDateTime dateTime;
    private boolean isFestival;
    @ManyToOne
    @JoinColumn(name = "id_venue")
    private Venue venue;

    // Relation ManyToOne avec Style (1,1 - 0,n)
    @ManyToOne
    @JoinColumn(name = "id_style")
    private Style style;


    public Event(int id, Style style, Venue venue, boolean isFestival, LocalDateTime dateTime, String price, String name) {
        this.id = id;
        this.style = style;
        this.venue = venue;
        this.isFestival = isFestival;
        this.dateTime = dateTime;
        this.price = price;
        this.name = name;
    }

    public Event() {

    }

    public Event(String name, String price, LocalDateTime dateTime, boolean isFestival) {
        this.name = name;
        this.price = price;
        this.dateTime = dateTime;
        this.isFestival = isFestival;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime date_time) {
        this.dateTime = date_time;
    }

    public boolean isIsFestival() {
        return isFestival;
    }

    public void setIsFestival(boolean is_festival) {
        this.isFestival = is_festival;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
