package com.ugo.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "state")

public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //I changed to IdState because in that way use the referencecolumn it would be more easier.
    @Column(name = "IdState")
    private Long id;

    private String name;

    private Date created;

    private Date updated;
}
