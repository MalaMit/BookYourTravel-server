package pk.ap.bookyourtravelserwer.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pk.ap.bookyourtravelserwer.user.infrastracture.persistance.UserRepository;

import java.util.Date;

@Service
public class JwtTokenProvider {
    private final String secret;
    private final Long expiration;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret,
                            @Value("${jwt.expiration}") Long expiration,
                            UserRepository userRepository) {
        this.secret = secret;
        this.expiration = expiration;
        this.userRepository = userRepository;
    }

    public JwtToken generateJwtToken(String username) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiration * 10000);

        log.info("Generation of new JWT token for " + username);

        String stringJwtToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        return new JwtToken(stringJwtToken);
    }

    public Authentication transformTokenToAuthentication(String token){
        String username = Jwts.parser()
                .setSigningKey(this.secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

//        It's debatable if select through db is necessary. There is possibility to get authorities and credentials from token
        UserDetails userDetails = userRepository.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
    }
}
