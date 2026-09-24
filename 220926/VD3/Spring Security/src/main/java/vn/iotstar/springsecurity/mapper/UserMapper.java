package vn.iotstar.springsecurity.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.iotstar.springsecurity.dto.UserDTO;
import vn.iotstar.springsecurity.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roleName", source = "role.name")
    UserDTO toDTO(User user);
}
