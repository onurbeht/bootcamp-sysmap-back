package br.com.sysmap.bootcamp.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "wallets")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Wallet {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "points")
    private Integer points;
    @Column(name = "last_update")
    private LocalDate lastUpdate;
    @Column(name = "owner")
    private String owner;



}
