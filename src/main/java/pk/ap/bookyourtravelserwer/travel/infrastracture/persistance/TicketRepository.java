package pk.ap.bookyourtravelserwer.travel.infrastracture.persistance;

import pk.ap.bookyourtravelserwer.travel.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    Ticket saveTicket(Ticket ticket);

    List<Ticket> getBoughtTicketByUser(long userID);

    void deleteUserTicket(long ticketID);

    Optional<Ticket> getTicketByID(long ticketID);
}
