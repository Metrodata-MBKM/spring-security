/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.models;

import com.metrodatambkm.security.models.User;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author gabri
 */

@Entity
@Data 
@Table(name = "tokenVerification")
@AllArgsConstructor 
@NoArgsConstructor
public class TokenVerification {
    private static final int EXPIRATION =60 *24;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    private String token;
    
    @OneToOne(targetEntity=User.class, fetch=FetchType.EAGER)
    @JoinColumn(nullable=false, name="user")
    private User user;
    
    private Date expireDate;

    public TokenVerification(String token, User user) {
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
