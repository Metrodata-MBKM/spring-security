/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.repositories;

import com.mbkm.hr.models.Department;
import com.mbkm.hr.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kevitha
 */
@Repository
public interface AppUserDetailsRepository extends JpaRepository<User, Integer>{
    User findByUsernameOrEmail(String username, String email);
}
