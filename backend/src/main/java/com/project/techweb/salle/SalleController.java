package com.project.techweb.salle;

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
@RequestMapping("/api/salles")
public class SalleController {

    private final SalleService salleService;

    @Autowired
    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }

    @GetMapping
    public List<Salle> getAllProfs() {
        return salleService.findAll();
    }

    @GetMapping("{codesal}")
    public Salle getSalleByCodesal(@PathVariable Integer codesal) {
        return salleService.findByCodesal(codesal);
    }

    @PostMapping
    public Salle createSalle(@RequestBody Salle salle) {
        return salleService.createSalle(salle);
    }

    @PutMapping("/{codesal}")
    public Salle updateSalle(@PathVariable Integer codesal, @RequestBody Salle salle) {
        return salleService.updateSalle(codesal, salle);
    }

    @DeleteMapping("/{codesal}")
    public void deleteSalle(@PathVariable Integer codesal) {
        salleService.deleteSalle(codesal);
    }
}
