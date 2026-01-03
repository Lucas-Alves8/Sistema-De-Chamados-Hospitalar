package controller.mappers;

import controller.dto.UserDto;
import models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto dto);
}
