package fastaireasoner;

import fastai.AI;

import java.util.List;

public final class Demo {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("     FastAIReasoner - Cognitive Planning Demo    ");
        System.out.println("=================================================");

        // Simulated high-speed AI engine for deterministic demo verification
        AI mockAI = new AI() {
            @Override
            public String ask(String prompt) {
                return "1. Define interface\n2. Implement lock-free queue\n3. Add unit tests";
            }

            @Override
            public String ask(String systemPrompt, String userPrompt) {
                if (userPrompt.contains("Candidate")) {
                    return "Candidate 1: Array-based ring buffer\nCandidate 2: Linked node concurrent queue\nCandidate 3: Disrupter pattern ring";
                }
                return "Selected Approach: Candidate 3 (Disruptor pattern) for lowest memory latency and zero lock contention.";
            }

            @Override public String ask(String prompt, java.io.File file) { return ""; }
            @Override public void stream(String prompt, java.util.function.Consumer<String> consumer) {}
            @Override public void stream(String systemPrompt, String userPrompt, java.util.function.Consumer<String> consumer) {}
            @Override public void stream(String prompt, java.io.File file, java.util.function.Consumer<String> consumer) {}
            @Override public List<String> getModels() { return List.of("mock-reasoner"); }
        };

        // 1. Chain-of-Thought (CoT) Demo
        System.out.println("\n[1] Running Chain-of-Thought (CoT)...");
        FastAIReasoner cotReasoner = FastAIReasoner.chainOfThought(mockAI);
        ReasoningResult cotResult = cotReasoner.reason("Build lock-free ring buffer");
        System.out.println("CoT Result Steps:");
        cotResult.rationaleSteps().forEach(s -> System.out.println("  -> " + s));

        // 2. Tree-of-Thoughts (ToT) Demo
        System.out.println("\n[2] Running Tree-of-Thoughts (ToT)...");
        FastAIReasoner totReasoner = FastAIReasoner.treeOfThoughts(mockAI, 3, 2);
        ReasoningResult totResult = totReasoner.reason("Select architectural concurrency model");
        System.out.println("Explored Branches (" + totResult.alternativeBranches().size() + "):");
        totResult.alternativeBranches().forEach(b -> System.out.println("  * " + b));
        System.out.println("\nBest Evaluated Path:\n" + totResult.bestPath());
        System.out.println("Confidence: " + totResult.confidenceScore());
    }
}
