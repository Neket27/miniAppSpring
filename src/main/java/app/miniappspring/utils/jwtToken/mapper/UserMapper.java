package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.arguments.UpdateUserArgument;
import app.miniappspring.dto.jwtToken.SignUpRequest;
import app.miniappspring.dto.jwtToken.SigninRequest;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.dto.user.UpdateUserDto;
import app.miniappspring.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

User toEntity(CreateUserArgument createUserArgument);
CreateUserDto toCreateUserDto(CreateUserArgument createUserArgument);
CreateUserDto toCreateUserDto(User user);
UpdateUserDto toUpdateUserDto(UpdateUserArgument updateUserArgument);
UpdateUserDto toUpdateUserDto(User user);
User toEntity(CreateUserDto createUserDto);

CreateUserArgument toCreateUserArgument(SignUpRequest signUpRequest);

}
