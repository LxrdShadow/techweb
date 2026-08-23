package com.project.techweb.prof;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/profs")
public class ProfController {

    private final ProfService profService;

    @Autowired
    public ProfController(ProfService profService) {
        this.profService = profService;
    }

    @GetMapping
    public List<Prof> getAllProfs() {
        return profService.findAll();
    }

    @GetMapping("{codeprof}")
    public Prof getProfByCodeprof(@PathVariable int codeprof) {
        return profService.findByCodeprof(codeprof);
    }

    @PostMapping
    public Prof createProf(@RequestBody Prof prof) {
        return profService.createProf(prof);
    }

    @PutMapping("/{codeprof}")
    public Prof updateProf(@PathVariable Integer codeprof, @RequestBody Prof prof) {
        return profService.updateProf(prof);
    }

    @DeleteMapping("/{codeprof}")
    public void deleteProf(@PathVariable Integer codeprof) {
        profService.deleteProf(codeprof);
    }
}
