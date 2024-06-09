package lambert.skazy.service;

import lambert.skazy.model.Solution;
import org.apache.commons.collections4.iterators.PermutationIterator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GenerateService {

    public List<List<Integer>> generate(){

        PermutationIterator<Integer> permutations = this.createPermutation();

        List<List<Integer>> solutions = new ArrayList<>();

        while (permutations.hasNext()){
            List<Integer> perm = permutations.next();
            double[] permArray = perm.stream().mapToDouble(s -> s).toArray();
            if(this.solveEquation(permArray)){
                solutions.add(perm);
            }
        }

        return solutions;
    }

    private PermutationIterator<Integer> createPermutation(){
        List<Integer> digits = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        return new PermutationIterator<>(digits);
    }

    public boolean solveEquation(double[] x){
        return x[0] + 13 * x[1] / x[2] + x[3] + 12 * x[4] - x[5] - 11 + x[6] * x[7] / x[8] - 10 == 66;
    }
}
