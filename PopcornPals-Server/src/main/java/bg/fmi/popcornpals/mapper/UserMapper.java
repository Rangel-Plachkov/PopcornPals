package bg.fmi.popcornpals.mapper;

import bg.fmi.popcornpals.dto.UserDTO;
import bg.fmi.popcornpals.dto.UserRequestDTO;
import bg.fmi.popcornpals.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "birthday", target = "birthday")
    UserDTO toDTO(User user);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "birthday", target = "birthday")
    UserRequestDTO toRequestDTO(User user);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "email", ignore = true)
    User toEntity(UserDTO userDTO);

    @Mapping(source = "ID", target = "ID")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "birthday", target = "birthday")
    User toEntity(UserRequestDTO userRequestDTO);

    List<UserDTO> toDTOList(List<User> userList);
    List<UserRequestDTO> toRequestDTOList(List<User> userList);
    List<User> fromUserDTOtoEntityList(List<UserDTO> userDTOList);
    List<User> toEntityList(List<UserRequestDTO> userRequestDTOList);
}
