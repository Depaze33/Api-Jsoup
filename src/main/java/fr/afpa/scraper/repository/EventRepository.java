package fr.afpa.scraper.repository;
import fr.afpa.scraper.entities.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * TODO implémenter un "repository" (similaire à un DAO) permettant d'interagir avec les données de la BDD
 * Tutoriel -> https://www.geeksforgeeks.org/spring-boot-crudrepository-with-example/
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {


    /**
     * @param id Identifiant du compte à retrouver
     * @return Un objet de compte correspondant à l'identifiant en paramètre
     */
    Optional<Event> findById(long id);

    /**
     * Enregistre les informations d'un compte
     *
     * @param event Le compte à enregistrer
     * @return L'objet enregistré
     */
    Event save(Event event);

    /**
     * Suppression d'un compte
     *
     * @param event
     */
    void delete(Event event);


}

