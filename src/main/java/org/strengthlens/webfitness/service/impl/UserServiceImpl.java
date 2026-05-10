package org.strengthlens.webfitness.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.strengthlens.webfitness.dto.UserDTO;
import org.strengthlens.webfitness.entity.User;
import org.strengthlens.webfitness.exception.ResourceNotFoundException;
import org.strengthlens.webfitness.mapper.UserMapper;
import org.strengthlens.webfitness.repository.UserRepository;
import org.strengthlens.webfitness.service.UserService;

import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    /* if we need more control, we can use CacheManager
    private final CacheManager cacheManager;
    Cache cache = cacheManager.getCache("ALL_USER");
    cache.put(stuff);*/


    @Override
    @Caching(
            put = {
                    @CachePut(value = "USER_CACHE", key = "#result.id")
            },
            evict = {
                    @CacheEvict(value = "USER_LIST_CACHE", allEntries = true)
            }
    )
    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.mapToUser(userDTO);
        User savedUser =  userRepository.save(user);
        return UserMapper.mapToUserDTO(savedUser);
    }

    @Override
    @Cacheable(value = "USER_CACHE", key = "#userId")
    public UserDTO getUserByID(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource " + userId + "not found"));
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    @Cacheable(value = "USER_LIST_CACHE", key = "'ALL_USERS'")
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDTO).collect(Collectors.toList());
    }

    @Override
    @Caching(
            put = {
                    @CachePut(value = "USER_CACHE", key = "#result.id")
            },
            evict = {
                    @CacheEvict(value = "USER_LIST_CACHE", allEntries = true)
            }
    )
    public UserDTO updateUser(Long employeeId, UserDTO updateUserDto) {
        User userToUpdate = userRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource " + employeeId + "not found"));

        userToUpdate.setFirstName(updateUserDto.getFirstName());
        userToUpdate.setLastName(updateUserDto.getLastName());
        userToUpdate.setEmail(updateUserDto.getEmail());
        User updatedUser =  userRepository.save(userToUpdate);

        return UserMapper.mapToUserDTO(updatedUser);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "USER_CACHE", key = "#userId"),
            @CacheEvict(value = "USER_LIST_CACHE", allEntries = true)
    })
    public void deleteUserById(Long userId) {
        User deletedUser =  userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource " + userId + "not found"));
        userRepository.delete(deletedUser);
    }
}
