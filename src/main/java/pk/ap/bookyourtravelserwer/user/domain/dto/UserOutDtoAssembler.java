package pk.ap.bookyourtravelserwer.user.domain.dto;

import org.springframework.stereotype.Service;
import pk.ap.bookyourtravelserwer.user.domain.User;
import pk.ap.bookyourtravelserwer.util.DtoAssembler;

@Service
public class UserOutDtoAssembler implements DtoAssembler<UserOutDto, User> {
    @Override
    public UserOutDto assemble(User entity) {
        return new UserOutDto(entity.getId(), entity.getName(), entity.getEmail());
    }
}
