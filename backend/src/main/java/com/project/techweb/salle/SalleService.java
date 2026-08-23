package com.project.techweb.salle;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SalleService {

    private final SalleRepository salleRepository;

    public SalleService(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    public Salle findByCodesal(Integer codesal) {
        return salleRepository.findById(codesal).orElse(null);
    }

    public List<Salle> findAll() {
        return salleRepository.findAll();
    }

    public Salle createSalle(Salle prof) {
        return salleRepository.save(prof);
    }

    public Salle updateSalle(Integer codesal, Salle salle) {
        return salleRepository.findById(codesal).map(professor -> {
            salle.setCodesal(codesal);
            return salleRepository.save(salle);
        }).orElseThrow(() -> new SalleNotFoundException(codesal));
    }

    public void deleteSalle(Integer codesal) {
        salleRepository.deleteById(codesal);
    }
}
