package pk.ap.bookyourtravelserwer.travel.domain.dto;

public class SearchConnectionDto {
    private String depart_date;
    private String depart_time;
    private String from_City;
    private String to_City;
    

    public SearchConnectionDto(String depart_date, String depart_time, String from_City, String to_City) {
        this.depart_date = depart_date;
        this.depart_time = depart_time;
        this.from_City = from_City;
        this.to_City = to_City;
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
}
