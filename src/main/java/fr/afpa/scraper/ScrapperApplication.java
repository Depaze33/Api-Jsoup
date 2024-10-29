package fr.afpa.scraper;

import fr.afpa.scraper.services.ScrapperService;
import jakarta.annotation.PostConstruct;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ScrapperApplication {

    @Autowired
    private ScrapperService service;

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
        Document doc1 = ScrapperService.getWebDocument(homePageURL);
        if (doc1 != null) {
            ScrapperService.getEventsInformation(doc1);

        }
        // URL de la deuxième page (ajustez selon votre besoin)
//        String url2 = "http://www.tyzicos.com/concerts-par-style";
    }
}


//dans la main
//@autowierd

