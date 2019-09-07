package pk.ap.bookyourtravelserwer.travel.domain;

import org.springframework.format.annotation.DateTimeFormat;
import pk.ap.bookyourtravelserwer.user.domain.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "train_connection",
            joinColumns = {@JoinColumn(name = "ticket_id")},
            inverseJoinColumns = {@JoinColumn(name = "travel_id")})
    private Set<Travel> travel = new HashSet<>();;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "train_place",
            joinColumns = {@JoinColumn(name = "ticket_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> user = new HashSet<>();

    @NotNull
    @DateTimeFormat
    private String date_of_purchase;

    @NotNull
    private String typeSeatPlace;

    public Ticket() {
    }

    public Ticket(@NotNull Set<Travel> travel, @NotNull Set<User> user, @NotNull String date_of_purchase, @NotNull String typeSeatPlace) {
        this.travel = travel;
        this.user = user;
        this.date_of_purchase = date_of_purchase;
        this.typeSeatPlace = typeSeatPlace;
    }

    public void setTravel(Set<Travel> travel) {
        this.travel = travel;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public void setDate_of_purchase(String date_of_purchase) {
        this.date_of_purchase = date_of_purchase;
    }

    public void setTypeSeatPlace(String typeSeatPlace) {
        this.typeSeatPlace = typeSeatPlace;
    }

    public Long getId() {
        return id;
    }

    public Set<Travel> getTravel() {
        return travel;
    }

    public Set<User> getUser() {
        return user;
    }

    public String getDate_of_purchase() {
        return date_of_purchase;
    }

    public String getTypeSeatPlace() {
        return typeSeatPlace;
    }

    public void buyTicketOn(Travel travel){
        this.travel.add(travel);
    }

    public void buyTicketBy(User user){
        this.user.add(user);
    }

}
