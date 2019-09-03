package pk.ap.bookyourtravelserwer.user.domain.dto;


import pk.ap.bookyourtravelserwer.security.JwtToken;

public class LoggedUser {
    public JwtToken token;
    public UserOutDto user;

    public LoggedUser(JwtToken token, UserOutDto user) {
        this.token = token;
        this.user = user;
    }
}
