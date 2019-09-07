package pk.ap.bookyourtravelserwer.travel.infrastracture.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pk.ap.bookyourtravelserwer.travel.domain.Ticket;
import pk.ap.bookyourtravelserwer.travel.domain.Travel;
import pk.ap.bookyourtravelserwer.travel.domain.dto.SearchConnectionDto;
import pk.ap.bookyourtravelserwer.travel.domain.dto.TicketDto;
import pk.ap.bookyourtravelserwer.travel.domain.dto.TrainConnectionDto;
import pk.ap.bookyourtravelserwer.travel.infrastracture.persistance.TicketRepository;
import pk.ap.bookyourtravelserwer.travel.infrastracture.persistance.TravelRepository;
import pk.ap.bookyourtravelserwer.user.domain.User;
import pk.ap.bookyourtravelserwer.user.infrastracture.persistance.UserRepository;
import pk.ap.bookyourtravelserwer.util.DtoAssembler;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/travel")
public class TravelControler {

    private final TravelRepository travelRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final DtoAssembler<TrainConnectionDto, Travel> travelOutDtoAssembler;

    @Autowired
    public TravelControler(TravelRepository travelRepository, TicketRepository ticketRepository, UserRepository userRepository, DtoAssembler<TrainConnectionDto, Travel> travelOutDtoAssembler) {
        this.travelRepository = travelRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.travelOutDtoAssembler = travelOutDtoAssembler;
    }

    @PostMapping(value = "/search")
    @ResponseBody
    public List<Travel> searchTravelConnection(@RequestBody SearchConnectionDto searchConnectionDto) throws ParseException {
        return travelRepository.searchTravel(searchConnectionDto);
    }

    @PostMapping(value = "/ticketBuy")
    @ResponseBody
    public void ticketBuy(@RequestBody TicketDto ticketBuy){
        User user = userRepository.getUserByID(Long.valueOf(ticketBuy.getUserID())).get();
        Travel travel = travelRepository.getById(Long.valueOf(ticketBuy.getTrainConnection())).get();

        Ticket ticket = new Ticket();
        ticket.buyTicketBy(user);
        ticket.buyTicketOn(travel);
        ticket.setDate_of_purchase(ticketBuy.getDate_of_purchase());
        ticket.setTypeSeatPlace(ticketBuy.getTypeSeatPlace());

        ticketRepository.saveTicket(ticket);

    }

    @GetMapping(value = "/boughtTicket/{id}")
    public List<Ticket> boughtTicketByUser(@PathVariable String id){
        return ticketRepository.getBoughtTicketByUser(Long.valueOf(id));
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteTicket(@PathVariable String id){
        ticketRepository.deleteUserTicket(Long.valueOf(id));
    }
}
