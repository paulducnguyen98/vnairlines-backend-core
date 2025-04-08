package com.vnairlines.csdl.services;

import java.util.List;
import java.util.UUID;

import com.vnairlines.csdl.dtos.CreateUserRequest;
import com.vnairlines.csdl.dtos.LoginRequest;
import com.vnairlines.csdl.dtos.LoginResponse;
import com.vnairlines.csdl.dtos.MembershipTierDto;
import com.vnairlines.csdl.dtos.SignUpRequest;
import com.vnairlines.csdl.models.UserDto;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(UUID userId);

    UserDto createUser(CreateUserRequest user);

    void assignTierToUser(UUID userId, String tierName);

    List<MembershipTierDto> getAllMembershipTiers();

    UserDto updateUser(UserDto user);

    boolean isPhoneNumberTaken(String phoneNumber, UUID excludeUserId);

    UserDto signUp(SignUpRequest request);

    LoginResponse login(LoginRequest request);
}
