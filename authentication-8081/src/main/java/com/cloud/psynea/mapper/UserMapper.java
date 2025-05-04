package com.cloud.psynea.mapper;

import com.cloud.psynea.dto.UserDto;
import com.cloud.psynea.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "authorities", expression = "java(List.of(\"user\"))")
    @Mapping(target = "isAccountNonExpired",expression = "java(true)")
    @Mapping(target = "isAccountNonLocked",expression = "java(true)")
    @Mapping(target = "isCredentialsNonExpired",expression = "java(true)")
    @Mapping(target = "isEnabled",expression = "java(true)")
    User userDtotoUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
