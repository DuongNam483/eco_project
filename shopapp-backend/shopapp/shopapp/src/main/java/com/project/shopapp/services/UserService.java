package com.project.shopapp.services;

import com.project.shopapp.dto.UserDTO;
import com.project.shopapp.exceptions.DataNotFound;
import com.project.shopapp.models.User;

public interface UserService {
    User createUser(UserDTO userDTO) throws Exception;
    String login(String phoneNumber, String password, Long roleId) throws DataNotFound;

}
