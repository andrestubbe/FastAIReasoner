package fastaireasoner;

import fastai.AI;
import fastai.AIRequest;
import fastai.AIResponse;
import fastai.Usage;

import java.util.List;
import java.util.function.Consumer;

public final class Demo {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("     FastAIReasoner - Cognitive Planning Demo    ");
        System.out.println("=================================================");

        // High-speed simulated AI client implementing the unified FastAI interface
        AI mockAI = new AI() {
            @Override
            public AIResponse generate(AIRequest request) {
                String sys = request.systemPrompt;

                if (sys != null && sys.contains("strategic solution explorer")) {
                    return new AIResponse("Candidate 1: Array-based ring buffer\nCandidate 2: Linked node concurrent queue\nCandidate 3: Disruptor pattern ring", Usage.ZERO, 0.0);
                }
                if (sys != null && sys.contains("evaluation judge")) {
                    return new AIResponse("Selected Approach: Candidate 3 (Disruptor pattern) for lowest memory latency and zero lock contention.", Usage.ZERO, 0.0);
                }
                return new AIResponse("1. Define interface\n2. Implement lock-free queue\n3. Add unit tests", Usage.ZERO, 0.0);
            }

            @Override
            public void stream(String prompt, Consumer<String> tokenHandler) {
                tokenHandler.accept("Step output");
            }

            @Override
            public List<String> getModels() {
                return List.of("mock-reasoner");
            }
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
