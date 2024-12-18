package app.miniappspring.utils.mapper;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.arguments.UpdateDataUserArgument;
import app.miniappspring.dto.jwtToken.SignUpRequest;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.dto.user.UpdateDataUserDto;
import app.miniappspring.dto.user.UserDto;
import app.miniappspring.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

User toEntity(CreateUserArgument createUserArgument);
User toEntity(UpdateDataUserDto updateDataUserDto);
CreateUserDto toCreateUserDto(CreateUserArgument createUserArgument);
CreateUserDto toCreateUserDto(User user);
UpdateDataUserDto toUpdateDataUserDto(UpdateDataUserArgument updateDataUserArgument);
UpdateDataUserDto toUpdateDataUserDto(User user);
User toEntity(CreateUserDto createUserDto);
UserDto toUserDto(User user);
CreateUserArgument toCreateUserArgument(SignUpRequest signUpRequest);

}
