package pk.ap.bookyourtravelserwer.travel.domain.dto;

import java.time.LocalDate;
import java.util.Date;

public class TrainConnectionDto {
    private long id;
    private String from_City;
    private String to_City;
    private LocalDate depart_date;
    private String depart_time;
    private LocalDate arrival_date;
    private String arrival_time;

    public TrainConnectionDto(long id, String from_City, String to_City, LocalDate depart_date, String depart_time, LocalDate arrival_date, String arrival_time) {
        this.id = id;
        this.from_City = from_City;
        this.to_City = to_City;
        this.depart_date = depart_date;
        this.depart_time = depart_time;
        this.arrival_date = arrival_date;
        this.arrival_time = arrival_time;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFrom_city() {
        return from_City;
    }

    public void setFrom_city(String from_city) {
        this.from_City = from_city;
    }

    public String getTo_City() {
        return to_City;
    }

    public void setTo_City(String to_City) {
        this.to_City = to_City;
    }

    public LocalDate getDepart_date() {
        return depart_date;
    }

    public void setDepart_date(LocalDate depart_date) {
        this.depart_date = depart_date;
    }

    public String getDepart_time() {
        return depart_time;
    }

    public void setDepart_time(String depart_time) {
        this.depart_time = depart_time;
    }

    public LocalDate getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(LocalDate arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }
}
