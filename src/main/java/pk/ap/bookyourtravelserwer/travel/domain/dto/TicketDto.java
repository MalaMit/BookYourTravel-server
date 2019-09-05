package pk.ap.bookyourtravelserwer.travel.domain.dto;

public class TicketDto {
    String date_of_purchase;
    String typeSeatPlace;
    int trainConnection;
    int userID;

    public String getDate_of_purchase() {
        return date_of_purchase;
    }

    public void setDate_of_purchase(String date_of_purchase) {
        this.date_of_purchase = date_of_purchase;
    }

    public String getTypeSeatPlace() {
        return typeSeatPlace;
    }

    public void setTypeSeatPlace(String typeSeatPlace) {
        this.typeSeatPlace = typeSeatPlace;
    }

    public int getTrainConnection() {
        return trainConnection;
    }

    public void setTrainConnection(int trainConnection) {
        this.trainConnection = trainConnection;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
