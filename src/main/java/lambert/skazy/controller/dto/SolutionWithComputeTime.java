package lambert.skazy.controller.dto;

import lambert.skazy.model.Solution;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolutionWithComputeTime {

    List<Solution> solutions;

    Float time;
}
