package app.miniappspring.utils.jwtToken.mapper;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.arguments.UpdateDataUserArgument;
import app.miniappspring.dto.image.UpdateImagerDto;
import app.miniappspring.dto.jwtToken.SignUpRequest;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.dto.user.UpdateDataUserDto;
import app.miniappspring.dto.user.UserDto;
import app.miniappspring.entity.Image;
import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-06T09:19:57+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(CreateUserArgument createUserArgument) {
        if ( createUserArgument == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.firstname( createUserArgument.getFirstname() );
        user.lastname( createUserArgument.getLastname() );
        user.username( createUserArgument.getUsername() );
        user.password( createUserArgument.getPassword() );
        user.email( createUserArgument.getEmail() );
        Set<Role> set = createUserArgument.getRoles();
        if ( set != null ) {
            user.roles( new LinkedHashSet<Role>( set ) );
        }

        return user.build();
    }

    @Override
    public User toEntity(UpdateDataUserDto updateDataUserDto) {
        if ( updateDataUserDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.firstname( updateDataUserDto.getFirstname() );
        user.lastname( updateDataUserDto.getLastname() );
        user.email( updateDataUserDto.getEmail() );
        user.avatar( updateImagerDtoToImage( updateDataUserDto.getAvatar() ) );

        return user.build();
    }

    @Override
    public CreateUserDto toCreateUserDto(CreateUserArgument createUserArgument) {
        if ( createUserArgument == null ) {
            return null;
        }

        CreateUserDto createUserDto = new CreateUserDto();

        createUserDto.setFirstname( createUserArgument.getFirstname() );
        createUserDto.setLastname( createUserArgument.getLastname() );
        createUserDto.setUsername( createUserArgument.getUsername() );
        createUserDto.setPassword( createUserArgument.getPassword() );
        createUserDto.setEmail( createUserArgument.getEmail() );
        Set<Role> set = createUserArgument.getRoles();
        if ( set != null ) {
            createUserDto.setRoles( new LinkedHashSet<Role>( set ) );
        }

        return createUserDto;
    }

    @Override
    public CreateUserDto toCreateUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        CreateUserDto createUserDto = new CreateUserDto();

        createUserDto.setFirstname( user.getFirstname() );
        createUserDto.setLastname( user.getLastname() );
        createUserDto.setUsername( user.getUsername() );
        createUserDto.setPassword( user.getPassword() );
        createUserDto.setEmail( user.getEmail() );
        Set<Role> set = user.getRoles();
        if ( set != null ) {
            createUserDto.setRoles( new LinkedHashSet<Role>( set ) );
        }

        return createUserDto;
    }

    @Override
    public UpdateDataUserDto toUpdateDataUserDto(UpdateDataUserArgument updateDataUserArgument) {
        if ( updateDataUserArgument == null ) {
            return null;
        }

        UpdateDataUserDto.UpdateDataUserDtoBuilder updateDataUserDto = UpdateDataUserDto.builder();

        updateDataUserDto.firstname( updateDataUserArgument.getFirstname() );
        updateDataUserDto.lastname( updateDataUserArgument.getLastname() );
        updateDataUserDto.email( updateDataUserArgument.getEmail() );
        updateDataUserDto.avatar( updateDataUserArgument.getAvatar() );

        return updateDataUserDto.build();
    }

    @Override
    public UpdateDataUserDto toUpdateDataUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UpdateDataUserDto.UpdateDataUserDtoBuilder updateDataUserDto = UpdateDataUserDto.builder();

        updateDataUserDto.firstname( user.getFirstname() );
        updateDataUserDto.lastname( user.getLastname() );
        updateDataUserDto.email( user.getEmail() );
        updateDataUserDto.avatar( imageToUpdateImagerDto( user.getAvatar() ) );

        return updateDataUserDto.build();
    }

    @Override
    public User toEntity(CreateUserDto createUserDto) {
        if ( createUserDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.firstname( createUserDto.getFirstname() );
        user.lastname( createUserDto.getLastname() );
        user.username( createUserDto.getUsername() );
        user.password( createUserDto.getPassword() );
        user.email( createUserDto.getEmail() );
        Set<Role> set = createUserDto.getRoles();
        if ( set != null ) {
            user.roles( new LinkedHashSet<Role>( set ) );
        }

        return user.build();
    }

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.firstname( user.getFirstname() );
        userDto.lastname( user.getLastname() );
        userDto.username( user.getUsername() );
        userDto.email( user.getEmail() );
        Set<Role> set = user.getRoles();
        if ( set != null ) {
            userDto.roles( new LinkedHashSet<Role>( set ) );
        }

        return userDto.build();
    }

    @Override
    public CreateUserArgument toCreateUserArgument(SignUpRequest signUpRequest) {
        if ( signUpRequest == null ) {
            return null;
        }

        CreateUserArgument createUserArgument = new CreateUserArgument();

        createUserArgument.setFirstname( signUpRequest.getFirstname() );
        createUserArgument.setLastname( signUpRequest.getLastname() );
        createUserArgument.setUsername( signUpRequest.getUsername() );
        createUserArgument.setPassword( signUpRequest.getPassword() );
        createUserArgument.setEmail( signUpRequest.getEmail() );
        Set<Role> set = signUpRequest.getRoles();
        if ( set != null ) {
            createUserArgument.setRoles( new LinkedHashSet<Role>( set ) );
        }

        return createUserArgument;
    }

    protected Image updateImagerDtoToImage(UpdateImagerDto updateImagerDto) {
        if ( updateImagerDto == null ) {
            return null;
        }

        Image.ImageBuilder image = Image.builder();

        image.name( updateImagerDto.getName() );
        image.contentType( updateImagerDto.getContentType() );

        return image.build();
    }

    protected UpdateImagerDto imageToUpdateImagerDto(Image image) {
        if ( image == null ) {
            return null;
        }

        UpdateImagerDto.UpdateImagerDtoBuilder updateImagerDto = UpdateImagerDto.builder();

        updateImagerDto.name( image.getName() );
        updateImagerDto.contentType( image.getContentType() );

        return updateImagerDto.build();
    }
}
