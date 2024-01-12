package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

User toEntity(CreateUserArgument createUserArgument);
CreateUserDto toCreateUserDto(CreateUserArgument createUserArgument);
User toEntity(CreateUserDto createUserDto);

}
