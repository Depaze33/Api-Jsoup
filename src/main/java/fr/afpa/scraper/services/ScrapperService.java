package fr.afpa.scraper.services;

import fr.afpa.scraper.entities.Department;
import fr.afpa.scraper.entities.Event;
import fr.afpa.scraper.entities.Style;
import fr.afpa.scraper.entities.Ville;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Locale;

@Service
public class ScrapperService {

    /**
     * Recupere les informations de la page html
     * @param homePageURL l'url de la page d'accueil
     * @return Retourne soit les évenements soit null si pas d'evenements
     */
    public static ArrayList<Event> getContent(String homePageURL) {



        Document documentHome = getWebDocument(homePageURL);
        if (documentHome != null) {
            ArrayList<Event> events = getEventsInformation(documentHome);
            extractCityAndDepartment(documentHome);
            extractStyle(documentHome);
            return events;
        }
        return null;
    }

    ///////////////A reexploiter plus tard //////////////////////
// Appel toutes les méthodes pour recuperer la deuxième page du site web ici http://www.tyzicos.com/
    // Scrapping de la deuxième page
//        Document doc2 = getWebDocument(url2);
//        if (doc2 != null) {
//            ArrayList<Event> events2 = getInformation(doc2);
//            extractCityAndDepartment(doc2);
//            extractStyle(doc2);
//        }

    /**
     * Recupere document html a partir d'une url
     *
     * @param url url du site à analyser
     * @return un objet du document html
     */
    public static Document getWebDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            System.err.println("Erreur de connexion au site : " + e.getMessage());
            return null;
        }
    }

    public static ArrayList<Event> getEventsInformation(Document doc) {
        ArrayList<Event> allEvents = new ArrayList<>();

        Elements allElements = doc.select("div.date-row");

        for (Element elementDateEvents : allElements) {
            String dateStr = elementDateEvents.select("div.date").text();
            Elements events = elementDateEvents.select("div.one-concert");

            for (Element event : events) {
                String title = event.select("div.concert").text();
                String placeText = event.select("div.lieu").text();
                String citys = event.select("div.ville > a").text();
                String hours = event.select("div.heure").text();
                String prices = event.select("div.prix").text();

                LocalDate dateObject = transformStringToLocalDate(dateStr);
                LocalTime timeObject = transformStringToTime(hours);
                String urlImage = null;
                boolean isFestival = false;

                if (urlImage != null) {
                    isFestival = true;
                }

                if (dateObject != null && timeObject != null) {
                    LocalDateTime finalDateTime = LocalDateTime.of(dateObject, timeObject);
                    System.out.println(finalDateTime);

                    Event newEvent = new Event(title, prices, finalDateTime, isFestival);
                    allEvents.add(newEvent);
                }

                System.out.println(dateStr + " " + title + "\n " + placeText + " " + citys + " \n " + hours + " " + prices);
            }
        }

        return allEvents;
    }

    public static void extractCityAndDepartment(Document doc) {
        Elements cityElements = doc.select("a.ville");

        for (Element cityElement : cityElements) {
            String cityName = cityElement.text().trim();
            String departmentName = cityElement.nextSibling().toString().trim();

            Ville ville = new Ville();
            ville.setName(cityName);

            Department departement = new Department();
            departement.setName(departmentName);

            ville.setDepartment(departement);

            System.out.println("Ville: " + cityName);
            System.out.println("Département: " + departmentName);
        }
    }

    public static ArrayList<Style> extractStyle(Document doc) {
        Elements styleElements = doc.select("div.wrapper"); // Ajustez le sélecteur selon votre structure
        ArrayList<Style> styles = new ArrayList<Style>();
        styleElements.forEach(styleElement -> {
            // Supposons que le nom soit dans une balise <span> ou <b>
            Elements names = styleElement.select("span.nom");
            for (Element name : names) {
                Element oneName = name.select("b").first();
                String justName = oneName.text();
                Style style = new Style(justName);
                styles.add(style);
                System.out.println(style);

            }

            // Utiliser un stream pour filtrer et afficher les noms
            names.stream()
                    .map(Element::text);// Récupérer le texte de chaque élément
            // Afficher chaque nom


        });


        return styles;
    }

    public static LocalDate transformStringToLocalDate(String transformStringDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy", Locale.FRANCE);
        try {
            TemporalAccessor temporalAccessorDate = dateTimeFormatter.parse(transformStringDate);
            return LocalDate.from(temporalAccessorDate);
        } catch (DateTimeParseException e) {
            System.err.println("Exception lors du parsing de la date: " + e.getMessage());
            return null;
        }
    }

    public static LocalTime transformStringToTime(String transformStringTime) {
        String[] timeParts = transformStringTime.split(" à ");
        String firstTimePart = timeParts[0];

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("H'h'mm", Locale.FRANCE);
        try {
            return LocalTime.parse(firstTimePart, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.err.println("Exception lors du parsing de l'heure: " + e.getMessage());
            return null;
        }
    }
}

