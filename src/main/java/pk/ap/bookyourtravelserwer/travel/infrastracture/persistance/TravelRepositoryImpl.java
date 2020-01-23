package pk.ap.bookyourtravelserwer.travel.infrastracture.persistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pk.ap.bookyourtravelserwer.travel.domain.Travel;
import pk.ap.bookyourtravelserwer.travel.domain.dto.SearchConnectionDto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class TravelRepositoryImpl extends SimpleJpaRepository<Travel, Long> implements TravelRepository{

    private EntityManager em;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public TravelRepositoryImpl(EntityManager em) {
        super(Travel.class, em);
        this.em = em;
    }

    @Override
    public List<Travel> searchTravel(SearchConnectionDto searchConnectionDto) throws ParseException {

       LocalDate newDate = parseDate(searchConnectionDto.getDepart_date(), "yyyy-MM-dd");

        String sqlQuery = "SELECT t From Travel t where t.from_City=:from_City and t.to_City=:to_City and t.depart_date='"+newDate+"'";
        Query query = em.createQuery(sqlQuery);
        query.setParameter("from_City", searchConnectionDto.getFrom_city());
        query.setParameter("to_City", searchConnectionDto.getTo_City());
        List<Travel> list = query.getResultList();

        assert  list.isEmpty() : "No find travel";

        // todo MK there needs to be created mechanism of throwing 404 via REST
        if (list.isEmpty()) {
            throw new IllegalArgumentException("No find travel");
        } else {
            return list;
        }
    }

    @Override
    public Optional<Travel> getById(Long id) {
        String sqlQuery = "SELECT t From Travel t where t.id=:id";
        Query query = em.createQuery(sqlQuery);
        query.setParameter("id", id);

        List<Travel> travelList = query.getResultList();

        assert travelList.isEmpty() : "No find travel by ID";

        return Optional.of(travelList.get(0));
    }

    private LocalDate parseDate(String date, String format) throws ParseException
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}
