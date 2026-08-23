package com.project.techweb.occuper;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class OccuperId implements Serializable {

    private Integer codeprof;
    private Integer codesal;
    private LocalDate date;

    public OccuperId() {
    }

    public OccuperId(Integer codeprof, Integer codesal, LocalDate date) {
        this.codeprof = codeprof;
        this.codesal = codesal;
        this.date = date;
    }

    public Integer getCodeprof() {
        return codeprof;
    }

    public Integer getCodesal() {
        return codesal;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OccuperId that)) {
            return false;
        }
        return Objects.equals(codeprof, that.codeprof)
                && Objects.equals(codesal, that.codesal)
                && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeprof, codesal, date);
    }
}
