package com.metrodatambkm.security.repository;

import com.metrodatambkm.security.model.UserModel;
import com.metrodatambkm.security.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByUser(UserModel user);
    VerificationToken findByToken(String token);
}
