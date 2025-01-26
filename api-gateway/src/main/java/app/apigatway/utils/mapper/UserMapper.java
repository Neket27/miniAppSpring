package app.apigatway.utils.mapper;


import app.apigatway.arguments.CreateUserArgument;
import app.apigatway.arguments.UpdateDataUserArgument;
import app.apigatway.dto.jwtToken.SignUpRequest;
import app.apigatway.dto.user.CreateUserDto;
import app.apigatway.dto.user.UpdateDataUserDto;
import app.apigatway.dto.user.UserDto;
import app.apigatway.entity.User;
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
