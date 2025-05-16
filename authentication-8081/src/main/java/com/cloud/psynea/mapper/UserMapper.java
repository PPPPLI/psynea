package com.cloud.psynea.mapper;

import com.cloud.psynea.dto.UserDto;
import com.cloud.psynea.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {java.util.List.class})
@SuppressWarnings("unused")
public interface UserMapper {

    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "authorities", expression = "java(List.of(\"user\"))")
    @Mapping(target = "isAccountNonExpired",expression = "java(true)")
    @Mapping(target = "isAccountNonLocked",expression = "java(true)")
    @Mapping(target = "isCredentialsNonExpired",expression = "java(true)")
    @Mapping(target = "isEnabled",expression = "java(true)")
    User userDtotoUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
