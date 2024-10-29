package fr.afpa.scraper;

import fr.afpa.scraper.entities.Event;
import fr.afpa.scraper.repository.EventRepository;
import fr.afpa.scraper.services.ScrapperService;
import jakarta.annotation.PostConstruct;

import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ScrapperApplication {

    @Autowired
    private ScrapperService service;

    @Autowired
    private EventRepository eventRepository;

    public static void main(String[] args) {
        // Démarrage de l'application Spring Boot
        SpringApplication.run(ScrapperApplication.class, args);
    }

    /**
     * Appel le scrapperService pour extraire les objets et pour les mettre en BDD
     */
    @PostConstruct
    public void initialize() {
        String homePageURL = "http://www.tyzicos.com/";
        Document doc1 = service.getWebDocument(homePageURL);
        if (doc1 != null) {
            ArrayList<Event> events = service.getEventsInformation(doc1);
            
            // TODO peut être faudrait-il appeler le repository pour faire l'insertion en BDD des évènements ?
        }



//        String url2 = "http://www.tyzicos.com/concerts-par-style";
    }
}
