package com.FMeneguzzi.demo_park.api.web.dto.mapper;

import com.FMeneguzzi.demo_park.api.entities.User;
import com.FMeneguzzi.demo_park.api.web.dto.UserCreateDto;
import com.FMeneguzzi.demo_park.api.web.dto.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toUser(UserCreateDto createDto){
        return new ModelMapper().map(createDto, User.class);
    }

    public static UserResponseDto toDto(User user) {
        String role = user.getRole().name().substring("ROLE_".length());
        ModelMapper mapperMain = new ModelMapper();
        TypeMap<User, UserResponseDto> propertyMapper =
                mapperMain.createTypeMap(User.class, UserResponseDto.class);
        propertyMapper.addMappings(
                mapper -> mapper.map(src -> role, UserResponseDto::setRole)
        );
        return mapperMain.map(user, UserResponseDto.class);
    }

    public static List<UserResponseDto> toListDto(List<User> user){
        return user.stream().map(user1 -> toDto(user1)).collect(Collectors.toList());
    }
}
