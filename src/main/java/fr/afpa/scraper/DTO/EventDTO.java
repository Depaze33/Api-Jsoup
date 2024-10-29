package fr.afpa.scraper.DTO;

import fr.afpa.scraper.entities.Style;

import java.time.LocalDateTime;

public class EventDTO {

    private int id;
    private String name;
    private String price;
    private LocalDateTime dateTime;
    private boolean isFestival;
    private Style style;

    public EventDTO() {

    }

    public EventDTO(int id, String name, String price, LocalDateTime dateTime, boolean isFestival) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.dateTime = dateTime;
        this.isFestival = isFestival;
        this.style = style;
    }

    public EventDTO(int id, String name, String price, LocalDateTime dateTime, boolean isFestival, int id1) {
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isFestival() {
        return isFestival;
    }

    public void setFestival(boolean festival) {
        isFestival = festival;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
