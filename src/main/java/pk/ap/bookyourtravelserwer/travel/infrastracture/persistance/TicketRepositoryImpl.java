package pk.ap.bookyourtravelserwer.travel.infrastracture.persistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pk.ap.bookyourtravelserwer.travel.domain.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Ticket> getBoughtTicketByUser(long userID) {
        String sqlQuery = "Select t from Ticket t join t.user u where u.id=:userID";
        Query query = em.createQuery(sqlQuery);
        query.setParameter("userID", userID);

        List<Ticket> tickets = query.getResultList();

        assert tickets.isEmpty() : "No ticket found";

        if (tickets.isEmpty())
        {
            throw new IllegalArgumentException("No ticket found for user = " + userID);
        }else {
            return tickets;
        }
    }

    @Override
    public void deleteUserTicket(long ticketID) {
        Optional<Ticket> foundedTicketOptional = getTicketByID(ticketID);
        Ticket foundedTicket = foundedTicketOptional.orElseThrow(() -> new IllegalArgumentException("No ticket with id:" + ticketID));
        delete(foundedTicket);
    }

    @Override
    public Optional<Ticket> getTicketByID(long ticketID) {
        String sqlQuery = "SELECT t From Ticket t where t.id = :id";
        Query query = em.createQuery(sqlQuery);
        query.setParameter("id",ticketID);
        List<Ticket> list = query.getResultList();

        assert list.isEmpty() : "Can't find this ticket";

        if(list.isEmpty()){
            throw new IllegalArgumentException("No show with id:" + ticketID);
        } else {
            return Optional.of(list.get(0));
        }
    }
}
