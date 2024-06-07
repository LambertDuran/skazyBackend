package lambert.skazy.controller;

import lambert.skazy.controller.dto.SolutionWithComputeTime;
import lambert.skazy.model.Solution;
import lambert.skazy.service.SolutionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    SolutionWithComputeTime generateSolution(){
        long startTime = System.currentTimeMillis();

        List<Solution> solutions = solutionService.generate();

        long endTime = System.currentTimeMillis();

        return new SolutionWithComputeTime(solutions, (endTime - startTime) / 1000.f);
    }

    @PostMapping("api/solution")
    void saveSolution(@RequestBody Solution solution){
        solutionService.save(solution);
    }

    @DeleteMapping("api/solutions")
    void deleteSolutions(){
        solutionService.deleteAll();
    }

    @DeleteMapping("api/solutions/{id}")
    void deleteSolution(@PathVariable Integer id){
        solutionService.deleteById(id);
    }

    @PutMapping("api/solution")
    void updateSolution(@RequestBody Solution solution){
        solutionService.update(solution);
    }
}
