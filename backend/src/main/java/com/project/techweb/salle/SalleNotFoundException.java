package com.project.techweb.salle;

public class SalleNotFoundException extends IllegalStateException {
    public SalleNotFoundException(Integer codesal) {
        super("Salle avec le code " + codesal + "introuvable");
    }
}

