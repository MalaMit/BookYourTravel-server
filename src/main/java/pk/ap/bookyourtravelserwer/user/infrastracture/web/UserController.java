package pk.ap.bookyourtravelserwer.user.infrastracture.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pk.ap.bookyourtravelserwer.user.domain.User;
import pk.ap.bookyourtravelserwer.user.domain.dto.UserInDto;
import pk.ap.bookyourtravelserwer.user.domain.dto.UserOutDto;
import pk.ap.bookyourtravelserwer.user.infrastracture.persistance.UserRepository;
import pk.ap.bookyourtravelserwer.util.DtoAssembler;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final DtoAssembler<UserOutDto, User> userOutDtoAssembler;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository,
                          DtoAssembler<UserOutDto, User> userOutDtoAssembler,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userOutDtoAssembler = userOutDtoAssembler;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public UserOutDto createUser(@RequestBody UserInDto userInDto) {
        //todo This is place which should be moved to service.
        // In service should be restTemplate to authController
        User newUser = new User(userInDto.getName(),
                passwordEncoder.encode(userInDto.getPassword()),
                userInDto.getEmail());
        User createdUser = userRepository.createUser(newUser);
        return userOutDtoAssembler.assemble(createdUser);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public UserOutDto updateUser(@RequestBody UserInDto user) {
        User updatedUser = userRepository.updateUser(user);
        return userOutDtoAssembler.assemble(updatedUser);
    }

    @GetMapping(value ="/getById/{id}")
    public UserOutDto getUserById(@PathVariable  String id){
        Optional<User> userOptional =  userRepository.getUserByID(Long.valueOf(id));
        return userOutDtoAssembler.assemble(userOptional.get());
    }

    @GetMapping(value = "/getByName/{name}")
    public UserOutDto getUserByName(@PathVariable String name){
        Optional<User> userOptional =  userRepository.getByName(name);
        return userOutDtoAssembler.assemble(userOptional.get());
    }

    @GetMapping(value = "/getAll")
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @DeleteMapping(value ="/deleteById/{id}")
    public void deleteUserById(@PathVariable String id){
        userRepository.deleteUser(Long.valueOf(id));
    }

}
