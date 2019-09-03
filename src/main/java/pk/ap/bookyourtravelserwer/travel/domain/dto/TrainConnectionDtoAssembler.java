package pk.ap.bookyourtravelserwer.travel.domain.dto;

import org.springframework.stereotype.Service;
import pk.ap.bookyourtravelserwer.travel.domain.Travel;
import pk.ap.bookyourtravelserwer.util.DtoAssembler;

@Service
public class TrainConnectionDtoAssembler implements DtoAssembler<TrainConnectionDto, Travel> {
    @Override
    public TrainConnectionDto assemble(Travel entity) {
        return new TrainConnectionDto(entity.getId(), entity.getFrom_City(), entity.getTo_City(),
                entity.getDepart_date(), entity.getDepart_time(), entity.getArrival_date(), entity.getArrival_time());
    }
}
