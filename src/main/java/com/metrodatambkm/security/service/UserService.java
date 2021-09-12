package com.metrodatambkm.security.service;

import com.metrodatambkm.security.dto.UserDTO;
import com.metrodatambkm.security.model.UserModel;

public interface UserService {
    UserModel addUser(UserDTO user);
    String encrypt(String password);
    UserModel getUserByUsername(String username);
    void updatePassword(UserModel user, String newPass);
}
