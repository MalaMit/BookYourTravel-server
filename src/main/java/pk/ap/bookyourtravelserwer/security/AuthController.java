package pk.ap.bookyourtravelserwer.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pk.ap.bookyourtravelserwer.security.AuthenticationService;
import pk.ap.bookyourtravelserwer.user.domain.User;
import pk.ap.bookyourtravelserwer.user.domain.dto.LoggedUser;
import pk.ap.bookyourtravelserwer.user.domain.dto.UserOutDto;
import pk.ap.bookyourtravelserwer.user.infrastracture.persistance.UserRepository;
import pk.ap.bookyourtravelserwer.util.DtoAssembler;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthenticationService authService;
    private final UserRepository userRepository;
    private final DtoAssembler<UserOutDto, User> assembler;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public AuthController(AuthenticationService authService,
                          UserRepository userRepository,
                          DtoAssembler<UserOutDto, User> assembler) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.assembler = assembler;
    }


    @PostMapping(value = "/signin")
    public LoggedUser signIn(@RequestBody AuthenticationRequest request, HttpServletResponse response){
        log.info("LoginToApp() endpoint started");

        JwtToken token = this.authService.authenticateUser(request.getUserName(), request.getPassword());

        UserOutDto user = userRepository.getByName(request.getUserName())
                .map(assembler::assemble)
                .get();

        return new LoggedUser(token, user);
    }

    //todo there needs to be created global handler
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
