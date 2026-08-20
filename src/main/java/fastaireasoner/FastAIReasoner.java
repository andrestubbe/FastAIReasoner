package fastaireasoner;

import fastai.AI;

import java.util.ArrayList;
import java.util.List;

public final class FastAIReasoner {

    private final ReasoningStrategy strategy;

    private FastAIReasoner(ReasoningStrategy strategy) {
        this.strategy = strategy;
    }

    public static FastAIReasoner chainOfThought(AI ai) {
        return new FastAIReasoner(new ChainOfThoughtStrategy(ai));
    }

    public static FastAIReasoner treeOfThoughts(AI ai, int branchFactor, int maxDepth) {
        return new FastAIReasoner(new TreeOfThoughtsStrategy(ai, branchFactor, maxDepth));
    }

    public ReasoningResult reason(String goal) {
        return strategy.evaluate(goal);
    }

    // --- Built-in Strategies ---

    private record ChainOfThoughtStrategy(AI ai) implements ReasoningStrategy {
        @Override
        public ReasoningResult evaluate(String goal) {
            String prompt = "Solve the following problem step by step with explicit rationale.\nGoal: " + goal + "\nFormat: List each step clearly.";
            String rawResponse = ai.ask("You are a rigorous logical deduction engine.", prompt);
            
            List<String> steps = new ArrayList<>();
            for (String line : rawResponse.split("\n")) {
                String trimmed = line.trim();
                if (!trimmed.isEmpty()) steps.add(trimmed);
            }

            return new ReasoningResult(goal, rawResponse, steps, List.of(), 0.95);
        }
    }

    private record TreeOfThoughtsStrategy(AI ai, int branchFactor, int maxDepth) implements ReasoningStrategy {
        @Override
        public ReasoningResult evaluate(String goal) {
            String branchPrompt = "Propose " + branchFactor + " distinct architectural strategies for: " + goal + "\nLabel each candidate as Candidate 1, Candidate 2, etc.";
            String candidatesRaw = ai.ask("You are a strategic solution explorer.", branchPrompt);

            List<String> branches = new ArrayList<>();
            for (String part : candidatesRaw.split("Candidate \\d+:")) {
                String candidate = part.trim();
                if (!candidate.isEmpty()) branches.add(candidate);
            }

            String evalPrompt = "Evaluate these strategies and select the single best approach with justification:\n" + candidatesRaw;
            String bestSelection = ai.ask("You are a technical evaluation judge.", evalPrompt);

            return new ReasoningResult(goal, bestSelection, List.of("Explored " + branches.size() + " branches", "Evaluated trade-offs"), branches, 0.92);
        }
    }
}
