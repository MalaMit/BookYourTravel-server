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
    private Set<User> users = new HashSet<>();

    @NotNull
    @DateTimeFormat
    private String date_of_purchase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Travel> getTravel() {
        return travel;
    }

    public void setTravel(Set<Travel> travel) {
        this.travel = travel;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getDate_of_purchase() {
        return date_of_purchase;
    }

    public void setDate_of_purchase(String date_of_purchase) {
        this.date_of_purchase = date_of_purchase;
    }
}
