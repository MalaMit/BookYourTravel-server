package pk.ap.bookyourtravelserwer.travel.domain.dto;

public class TrainConnectionDto {
    private long id;
    private String from_City;
    private String to_City;
    private String depart_date;
    private String depart_time;
    private String arrival_date;
    private String arrival_time;

    public TrainConnectionDto(long id, String from_City, String to_City, String depart_date, String depart_time, String arrival_date, String arrival_time) {
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

    public String getDepart_date() {
        return depart_date;
    }

    public void setDepart_date(String depart_date) {
        this.depart_date = depart_date;
    }

    public String getDepart_time() {
        return depart_time;
    }

    public void setDepart_time(String depart_time) {
        this.depart_time = depart_time;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }
}
