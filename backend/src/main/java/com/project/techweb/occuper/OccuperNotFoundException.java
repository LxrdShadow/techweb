package com.project.techweb.occuper;

public class OccuperNotFoundException extends IllegalStateException {
    public OccuperNotFoundException(OccuperId id) {
        super("Occupation introuvable : prof=" + id.getCodeprof()
                + ", salle=" + id.getCodesal() + ", date=" + id.getDate());
    }
}
