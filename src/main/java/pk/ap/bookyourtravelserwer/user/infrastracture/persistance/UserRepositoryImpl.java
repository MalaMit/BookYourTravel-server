package pk.ap.bookyourtravelserwer.user.infrastracture.persistance;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pk.ap.bookyourtravelserwer.user.domain.User;
import pk.ap.bookyourtravelserwer.user.domain.dto.UserInDto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class UserRepositoryImpl extends SimpleJpaRepository<User, Long> implements UserRepository {

    private EntityManager em;

    Optional<User> loggedUser = Optional.empty();

    public UserRepositoryImpl(EntityManager em) {
        super(User.class, em);
        this.em = em;
    }


    public User createUser(User user) {
        return save(user);
    }

    @Override
    public List<User> getAllUsers() {
        String getAllUsersQuery = "SELECT U from User U";
        Query query = em.createQuery(getAllUsersQuery);
        return query.getResultList();
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> foundedUserOptional = getUserByID(id);
        User foundedUser = foundedUserOptional.orElseThrow(() -> new IllegalArgumentException("No user with id:" + id));
        delete(foundedUser);
    }

    @Override
    public User updateUser(UserInDto updatedUser) {
        return save(updatedUser.getUser());
    }

    public Optional<User> getUserByID(Long id) {
        String sqlQuery = "SELECT u From User u where u.id = :id";
        Query query = em.createQuery(sqlQuery);
        query.setParameter("id", id);
        List<User> list = query.getResultList();

        assert list.isEmpty() : "No user founs";

        // todo MK there needs to be created mechanism of throwing 404 via REST
        if (list.isEmpty()) {
            throw new IllegalArgumentException("No user with id:" + id);
        } else {
            return Optional.of(list.get(0));
        }
    }

    @Override
    public Optional<User> signIn(String login, String password) {
        return getUserByLoginAndPassword(login, password);
    }

    @Override
    public void signOut(Long id) {
        loggedUser = Optional.empty();
    }

    public Optional<User> getUserByLoginAndPassword(String login, String password) {
        String sqlQuery = "Select u from User u where u.password = :password and u.name = :login";
        Query query = em.createQuery(sqlQuery);
        query.setParameter("password", password);
        query.setParameter("login", login);
        List<User> list = query.getResultList();

        if (list.isEmpty()) {
            throw new IllegalArgumentException("No user with such credentials");
        }
        return Optional.of(list.get(0));
    }

    @Override
    public Optional<User> getByName(String name) {
        String sqlQuery = "Select u from User u where u.name = :name";
        Query query = em.createQuery(sqlQuery);
        query.setParameter("name", name);
        List<User> resultList = query.getResultList();

        if (resultList.isEmpty()) {
            throw new IllegalArgumentException("No user with such name");
        }
        return Optional.of(resultList.get(0));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = this.getByName(s).orElseThrow(() -> new UsernameNotFoundException("User ["+ s + "] not found during authorization"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .accountExpired(false)
                .credentialsExpired(false)
                .authorities(Collections.emptyList())
                .accountLocked(false)
                .build();
    }
}
