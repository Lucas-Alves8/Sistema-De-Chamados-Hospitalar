package controller;

import controller.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import controller.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.UserService;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody UserDto dto){
        var user = mapper.toEntity(dto);
        service.save(user);
    }
}
