package sia.tacocloud.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.models.Taco;
import sia.tacocloud.repos.TacoRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class TacoController {

    private TacoRepository tacoRepository;

    public TacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos() {
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageRequest).getContent();
    }

    @GetMapping("/{id}")
    public Optional<Taco> tacoById(@PathVariable("id") Long id) {
        return tacoRepository.findById(id);
    }

//    @GetMapping("/{id}")
//    public Optional<Taco> tacoById(@PathVariable("id") Long id) {
//        Optional<Taco> taco = tacoRepository.findById(id);
//        if (taco.isPresent()) {
//            return new ResponseEntity<>(taco.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }
}
