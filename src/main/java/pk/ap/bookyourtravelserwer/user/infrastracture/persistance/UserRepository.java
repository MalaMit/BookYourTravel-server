package pk.ap.bookyourtravelserwer.user.infrastracture.persistance;


import org.springframework.security.core.userdetails.UserDetailsService;
import pk.ap.bookyourtravelserwer.user.domain.User;
import pk.ap.bookyourtravelserwer.user.domain.dto.UserInDto;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface UserRepository  extends UserDetailsService {
    Optional<User> getUserByID(Long id);

    Optional<User> getUserByLoginAndPassword(String login, String password);

    Optional<User> getByName(String name);

    User createUser(User user);

    List<User> getAllUsers();

    void deleteUser(Long id);

    Optional<User> signIn(String login, String password);

    void signOut(Long id);

    User updateUser(UserInDto user);
}

