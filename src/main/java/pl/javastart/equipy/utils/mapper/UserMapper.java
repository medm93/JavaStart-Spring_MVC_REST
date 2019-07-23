package pl.javastart.equipy.utils.mapper;

import org.mapstruct.Mapper;
import pl.javastart.equipy.model.dto.UserDTO;
import pl.javastart.equipy.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO dto);
    UserDTO toDTO(User entity);

}
