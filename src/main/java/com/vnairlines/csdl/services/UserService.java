package com.vnairlines.csdl.services;

import java.util.List;

import com.vnairlines.csdl.models.UserDto;

public interface UserService {

    List<UserDto> getAllUsers();
}
