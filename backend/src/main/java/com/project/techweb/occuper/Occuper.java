package com.project.techweb.occuper;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.techweb.prof.Prof;
import com.project.techweb.salle.Salle;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "occuper")
public class Occuper {

    @EmbeddedId
    @JsonIgnore
    private OccuperId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("codeprof")
    @JoinColumn(name = "codeprof", nullable = false)
    @JsonIgnore
    private Prof prof;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("codesal")
    @JoinColumn(name = "codesal", nullable = false)
    @JsonIgnore
    private Salle salle;

    public Occuper() {
    }

    public Occuper(Prof prof, Salle salle, LocalDate date) {
        this.prof = prof;
        this.salle = salle;
        this.id = new OccuperId(prof.getCodeprof(), salle.getCodesal(), date);
    }

    public Integer getCodeprof() {
        return id.getCodeprof();
    }

    public Integer getCodesal() {
        return id.getCodesal();
    }

    public LocalDate getDate() {
        return id.getDate();
    }
}
