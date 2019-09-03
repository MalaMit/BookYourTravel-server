package pk.ap.bookyourtravelserwer.user.domain.dto;

import pk.ap.bookyourtravelserwer.user.domain.User;

public class UserInDto {
    private final String id;
    private final String name;
    private final String password;
    private final String email;

    public UserInDto(String id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User getUser(){
        return new User().setId(Long.parseLong(id))
                .setName(name)
                .setPassword(password)
                .setEmail(email);
    }

    //getters are because of Jackson purposes
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
