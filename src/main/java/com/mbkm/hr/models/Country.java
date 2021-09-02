package com.mbkm.hr.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tb_country")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @Column(name = "country_id", length = 4)
    private String id;

    @Column(name = "country_name", length = 35)
    private String name;

    @ManyToOne
    private Region region;
}
