package pk.ap.bookyourtravelserwer.travel.infrastracture.persistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pk.ap.bookyourtravelserwer.travel.domain.Ticket;

import javax.persistence.EntityManager;

@Transactional
@Repository
public class TicketRepositoryImpl extends SimpleJpaRepository<Ticket, Long> implements TicketRepository {

    private EntityManager em;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public TicketRepositoryImpl(EntityManager em) {
        super(Ticket.class, em);
        this.em = em;
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return save(ticket);
    }
}
