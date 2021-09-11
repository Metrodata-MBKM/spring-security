package com.metrodatambkm.security.repository;

import com.metrodatambkm.security.models.credentials.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " + "SET a.enabled = TRUE WHERE a.id in (SELECT e.id FROM Employee e WHERE e.email = ?1)")
    int enableAppUser(String email);

    @Query(value = "SELECT u FROM AppUser u where u.username= ?1 and u.password = ?2 ")
    AppUser login(String username,String password);
}
