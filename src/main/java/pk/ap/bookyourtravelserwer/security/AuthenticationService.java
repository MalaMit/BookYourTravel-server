package pk.ap.bookyourtravelserwer.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import  pk.ap.bookyourtravelserwer.user.infrastracture.persistance.UserRepository;

@Service
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public AuthenticationService(PasswordEncoder passwordEncoder, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public JwtToken authenticateUser(String name, String password) {
        log.info("New ask for Token for " + name);
        return userRepository.getByName(name)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> jwtTokenProvider.generateJwtToken(name))
                .orElseThrow(() -> new IllegalArgumentException("Wrong Credentials"));
    }
}