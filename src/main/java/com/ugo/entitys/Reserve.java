package com.ugo.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Reserve")
public class Reserve {
    /* In this instance I wrote the variables than it will be used by the table of reserves
    the fk from the table Experiences it's not declared yet, cause the table
    it's not created at the time im writing this.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Basic
    private Date CreationDate;
    private Date UpdateDate;
    private String Currency;
    private int Duration;
    private int floor;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "state",
            referencedColumnName = "IdState"
    )
    private State state;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "ReserveOwner",
            referencedColumnName = "id"
    )
    private User ReserveOwner;

}