package com.metrodatambkm.security.entities.credentials;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
@NoArgsConstructor @AllArgsConstructor @Data
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user")
    private User user;

    private Date expireDate;

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expireDate = calculateExpireDate(EXPIRATION);
    }

    private Date calculateExpireDate(int expiryTimeInMinutes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
