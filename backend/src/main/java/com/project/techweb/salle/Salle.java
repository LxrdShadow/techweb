package com.project.techweb.salle;

import jakarta.persistence.*;

@Entity
@Table(name = "salle")
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codesal;
    private String designation;

    public Salle() {
    }

    public Salle(Integer codesal, String designation) {
        this.codesal = codesal;
        this.designation = designation;
    }

    public Integer getCodesal() {
        return codesal;
    }

    public void setCodesal(Integer codesal) {
        this.codesal = codesal;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
