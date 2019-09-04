package pk.ap.bookyourtravelserwer.travel.infrastracture.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pk.ap.bookyourtravelserwer.travel.domain.Travel;
import pk.ap.bookyourtravelserwer.travel.domain.dto.SearchConnectionDto;
import pk.ap.bookyourtravelserwer.travel.domain.dto.TicketDto;
import pk.ap.bookyourtravelserwer.travel.domain.dto.TrainConnectionDto;
import pk.ap.bookyourtravelserwer.travel.infrastracture.persistance.TravelRepository;
import pk.ap.bookyourtravelserwer.util.DtoAssembler;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/travel")
public class TravelControler {

    private final TravelRepository travelRepository;
    private final DtoAssembler<TrainConnectionDto, Travel> travelOutDtoAssembler;

    @Autowired
    public TravelControler(TravelRepository travelRepository, DtoAssembler<TrainConnectionDto, Travel> travelOutDtoAssembler) {
        this.travelRepository = travelRepository;
        this.travelOutDtoAssembler = travelOutDtoAssembler;
    }

    @PostMapping(value = "/search")
    @ResponseBody
    public List<Travel> searchTravelConnection(@RequestBody SearchConnectionDto searchConnectionDto){
        return travelRepository.searchTravel(searchConnectionDto);
    }

    @PostMapping(value = "/ticketBuy")
    @ResponseBody
    public void ticketBuy(@RequestBody TicketDto ticketBuy){
        throw new IllegalArgumentException("Działa");
    }
}
