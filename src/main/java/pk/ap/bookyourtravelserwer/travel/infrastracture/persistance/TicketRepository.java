package pk.ap.bookyourtravelserwer.travel.infrastracture.persistance;

import pk.ap.bookyourtravelserwer.travel.domain.Ticket;

public interface TicketRepository {
    Ticket saveTicket(Ticket ticket);
}
