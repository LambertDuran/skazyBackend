package lambert.skazy.service;

import lambert.skazy.model.Solution;
import lambert.skazy.repository.SolutionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SolutionService {

    private final SolutionRepository solutionRepository;
    private final GenerateService generateService;

    public SolutionService(SolutionRepository solutionRepository, GenerateService generateService) {
        this.solutionRepository = solutionRepository;
        this.generateService = generateService;
    }

    public List<Solution> findAll(){
        return solutionRepository.findAll();
    }

    public void save(Solution solution){
        solutionRepository.save(solution);
    }

    public void deleteAll(){
        solutionRepository.deleteAll();
    }

    public void deleteById(Integer id){
        solutionRepository.deleteById(id);
    }

    public void update(Solution solution){
        solutionRepository
                .findById(solution.getId())
                .orElseThrow(() -> new NoSuchElementException("Solution not found."));

        solutionRepository.save(solution);
    }

    public List<Solution> generate(){

        solutionRepository.deleteAll();

        List<List<Integer>> integerSolutions = generateService.generate();

        integerSolutions.forEach(s -> {
            Solution solution = new Solution();
            solution.setUnknowns(s);
            solution.setBIsValid(true);
            solutionRepository.save(solution);
        });

        return solutionRepository.findAll();
    }
}
