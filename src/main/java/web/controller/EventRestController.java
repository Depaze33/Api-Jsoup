package web.controller;

import fr.afpa.scraper.DTO.EventDTO;
import fr.afpa.scraper.entities.Event;
import fr.afpa.scraper.repository.EventRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/events")
public class EventRestController {

    private final EventRepository eventRepository;

    public EventRestController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public List<EventDTO> getAll() {
        List<Event> events = (List<Event>) eventRepository.findAll();

        return events.stream()
                .map(event -> new EventDTO(
                        event.getId(),
                        event.getName(),
                        event.getPrice(),
                        event.getDateTime(),
                        event.isIsFestival(),
                          event.getStyle().getId()

                          // On récupère uniquement l'ID du client
                ))
                .collect(Collectors.toList());
    }

}
