package com.vnairlines.csdl.services;

import java.util.List;
import java.util.UUID;

import com.vnairlines.csdl.models.UserDto;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(UUID userId);
}
