package com.metrodatambkm.security.repositories;

import com.metrodatambkm.security.entities.credentials.User;
import com.metrodatambkm.security.entities.credentials.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
    VerificationToken findByToken(String token);
    VerificationToken findByUser(User user);
}
