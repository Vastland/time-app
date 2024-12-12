package org.timeapp.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "times")
public class Times implements Serializable {

    @Serial
    private static final long serialVersionUID = -235978305355732636L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    @Version
    private Integer version;
}
