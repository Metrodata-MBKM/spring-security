package com.metrodatambkm.security.repositories;

import com.metrodatambkm.security.entities.credentials.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmployee_Email(String email);
    User findByUsernameOrEmployee_Email(String username, String email);
    User findByUsernameAndPassword(String username, String password);
}
