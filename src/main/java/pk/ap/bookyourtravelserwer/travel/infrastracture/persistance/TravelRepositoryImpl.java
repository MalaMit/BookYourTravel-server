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
    public List<Travel> searchTravel(SearchConnectionDto searchConnectionDto) {
        String sqlQuery = "SELECT t From Travel t where t.from_City =:from_City and t.to_City =:to_City and t.depart_date =:depart_date";
        Query query = em.createQuery(sqlQuery);
        query.setParameter("from_City", searchConnectionDto.getFrom_city());
        query.setParameter("to_City", searchConnectionDto.getTo_City());
        query.setParameter("depart_date", searchConnectionDto.getDepart_date());
        List<Travel> list = query.getResultList();

        // todo MK there needs to be created mechanism of throwing 404 via REST
        if (list.isEmpty()) {
            throw new IllegalArgumentException("No find travel");
        } else {
            return list;
        }
    }
}
