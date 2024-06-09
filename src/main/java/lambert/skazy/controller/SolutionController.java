package lambert.skazy.controller;

import lambert.skazy.controller.dto.SolutionWithComputeTime;
import lambert.skazy.model.Solution;
import lambert.skazy.service.SolutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SolutionController {

    private final SolutionService solutionService;

    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @GetMapping("api/solutions")
    List<Solution> getAllSolution(){
        return solutionService.findAll();
    }

    @GetMapping("api/solutions/generate")
    ResponseEntity<SolutionWithComputeTime> generateSolution(){
        long startTime = System.currentTimeMillis();

        List<Solution> solutions = solutionService.generate();

        long endTime = System.currentTimeMillis();

        return ResponseEntity.ok(new SolutionWithComputeTime(solutions, (endTime - startTime) / 1000.f));
    }

    @PostMapping("api/solution")
    void saveSolution(@RequestBody Solution solution){
        solutionService.save(solution);
    }

    @DeleteMapping("api/solutions")
    void deleteSolutions(){
        solutionService.deleteAll();
    }

    @DeleteMapping("api/solution/{id}")
    void deleteSolution(@PathVariable Integer id){
        solutionService.deleteById(id);
    }

    @PutMapping("api/solution")
    Solution updateSolution(@RequestBody Solution solution){
        var test = solutionService.update(solution);
        return test;
    }
}
