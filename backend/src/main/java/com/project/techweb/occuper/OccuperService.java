package com.project.techweb.occuper;

import java.time.LocalDate;
import java.util.List;
import com.project.techweb.prof.Prof;
import com.project.techweb.prof.ProfRepository;
import com.project.techweb.salle.Salle;
import com.project.techweb.salle.SalleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OccuperService {

    private final OccuperRepository occuperRepository;
    private final ProfRepository profRepository;
    private final SalleRepository salleRepository;

    public OccuperService(OccuperRepository occuperRepository, ProfRepository profRepository,
                           SalleRepository salleRepository) {
        this.occuperRepository = occuperRepository;
        this.profRepository = profRepository;
        this.salleRepository = salleRepository;
    }

    public List<Occuper> findAll() {
        return occuperRepository.findAll();
    }

    public Occuper findById(Integer codeprof, Integer codesal, LocalDate date) {
        OccuperId id = new OccuperId(codeprof, codesal, date);
        return occuperRepository.findById(id).orElseThrow(() -> new OccuperNotFoundException(id));
    }

    public Occuper create(OccuperRequest request) {
        validate(request);
        Prof prof = profRepository.findById(request.codeprof())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Professeur introuvable"));
        Salle salle = salleRepository.findById(request.codesal())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Salle introuvable"));
        return occuperRepository.save(new Occuper(prof, salle, request.date()));
    }

    @Transactional
    public Occuper update(Integer oldCodeprof, Integer oldCodesal, LocalDate oldDate,
                           OccuperRequest request) {
        validate(request);
        OccuperId oldId = new OccuperId(oldCodeprof, oldCodesal, oldDate);
        if (!occuperRepository.existsById(oldId)) {
            throw new OccuperNotFoundException(oldId);
        }
        occuperRepository.deleteById(oldId);
        return create(request);
    }

    public void delete(Integer codeprof, Integer codesal, LocalDate date) {
        OccuperId id = new OccuperId(codeprof, codesal, date);
        if (!occuperRepository.existsById(id)) {
            throw new OccuperNotFoundException(id);
        }
        occuperRepository.deleteById(id);
    }

    private void validate(OccuperRequest request) {
        if (request == null || request.codeprof() == null || request.codesal() == null || request.date() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "codeprof, codesal et date sont obligatoires");
        }
    }
}
