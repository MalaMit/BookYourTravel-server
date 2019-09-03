package pk.ap.bookyourtravelserwer.user.domain.dto;

public class LoginInDto {
    private final String login;
    private final String password;

    public LoginInDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
