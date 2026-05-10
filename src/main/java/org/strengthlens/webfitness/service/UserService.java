package org.strengthlens.webfitness.service;

import org.strengthlens.webfitness.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserByID(Long userId);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Long userId, UserDTO updatedUser);

    void deleteUserById(Long userId);
}
