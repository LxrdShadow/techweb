package com.project.techweb.prof;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProfService {

    private final ProfRepository profRepository;

    public ProfService(ProfRepository profRepository) {
        this.profRepository = profRepository;
    }

    public Prof findByCodeprof(Integer codeprof) {
        return profRepository.findById(codeprof).orElse(null);
    }

    public List<Prof> findAll() {
        return profRepository.findAll();
    }

    public Prof createProf(Prof prof) {
        return profRepository.save(prof);
    }

    public Prof updateProf(Integer codeprof, Prof prof) {
        return profRepository.findById(codeprof).map(professor -> {
            prof.setCodeprof(codeprof);
            return profRepository.save(prof);
        }).orElseThrow(() -> new ProfNotFoundException(codeprof));
    }

    public void deleteProf(Integer codeprof) {
        profRepository.deleteById(codeprof);
    }
}
