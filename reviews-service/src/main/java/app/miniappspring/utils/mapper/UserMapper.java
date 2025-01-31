package app.miniappspring.utils.mapper;

import app.miniappspring.dto.user.UserDto;
import app.miniappspring.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.demo.numbers.GRPCUser;
import ru.demo.numbers.GRPRole;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ImageMapper.class})
public interface UserMapper {

    @Mapping(target = "roles", source = "rolesList", qualifiedByName = "mapRolesToSet")
    @Mapping(target = "avatar", source = "avatar")
    UserDto fromGRPCUser(GRPCUser grpcUser);

    @Named("mapRolesToSet")
    default Set<Role> mapRolesToSet(List<GRPRole> grpcRoles) {
        return grpcRoles == null ? Set.of() :
                grpcRoles.stream()
                        .map(role -> Role.valueOf(role.name()))
                        .collect(Collectors.toSet());
    }


}