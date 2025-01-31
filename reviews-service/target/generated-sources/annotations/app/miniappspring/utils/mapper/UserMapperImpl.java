package app.miniappspring.utils.mapper;

import app.miniappspring.dto.user.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.demo.numbers.GRPCUser;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-31T16:15:33+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public UserDto fromGRPCUser(GRPCUser grpcUser) {
        if ( grpcUser == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.roles( mapRolesToSet( grpcUser.getRolesList() ) );
        if ( grpcUser.hasAvatar() ) {
            userDto.avatar( imageMapper.fromGRPCImage( grpcUser.getAvatar() ) );
        }
        userDto.id( grpcUser.getId() );
        userDto.firstname( grpcUser.getFirstname() );
        userDto.lastname( grpcUser.getLastname() );
        userDto.username( grpcUser.getUsername() );
        userDto.email( grpcUser.getEmail() );

        return userDto.build();
    }
}
