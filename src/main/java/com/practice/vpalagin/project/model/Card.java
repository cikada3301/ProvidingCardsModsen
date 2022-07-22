package com.practice.vpalagin.project.model;

import com.practice.vpalagin.project.model.enums.Access;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "CARDS")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "SERIALNUMBER")
    private Long serialNumber;

    @Column(name = "TYPE_CARD")
    private String typeCard;

    @Column(name = "VALIDITY_DATE")
    private Date validityDate;

    @Column(name = "DEPARTMENT")
    private String department;

    @Enumerated(EnumType.STRING)
    private Access access;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", nullable = false, updatable = false)
    private User owner;
}
