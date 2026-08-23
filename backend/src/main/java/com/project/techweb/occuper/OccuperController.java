package com.project.techweb.occuper;

import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/occupations")
public class OccuperController {

    private final OccuperService occuperService;

    public OccuperController(OccuperService occuperService) {
        this.occuperService = occuperService;
    }

    @GetMapping
    public List<Occuper> getAll() {
        return occuperService.findAll();
    }

    @GetMapping("/{codeprof}/{codesal}/{date}")
    public Occuper getById(@PathVariable Integer codeprof, @PathVariable Integer codesal,
                           @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return occuperService.findById(codeprof, codesal, date);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Occuper create(@RequestBody OccuperRequest request) {
        return occuperService.create(request);
    }

    @PutMapping("/{codeprof}/{codesal}/{date}")
    public Occuper update(@PathVariable Integer codeprof, @PathVariable Integer codesal,
                          @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                          @RequestBody OccuperRequest request) {
        return occuperService.update(codeprof, codesal, date, request);
    }

    @DeleteMapping("/{codeprof}/{codesal}/{date}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer codeprof, @PathVariable Integer codesal,
                       @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        occuperService.delete(codeprof, codesal, date);
    }
}
