package com.project.techweb.prof;

public class ProfNotFoundException extends IllegalStateException {
    public ProfNotFoundException(Integer codeprof) {
        super("Professeur avec le code " + codeprof + "introuvable");
    }
}

