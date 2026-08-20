package fastaireasoner;

import java.util.List;

public record ReasoningResult(
    String goal,
    String bestPath,
    List<String> rationaleSteps,
    List<String> alternativeBranches,
    double confidenceScore
) {
    public boolean isConfident() {
        return confidenceScore >= 0.7;
    }
}
