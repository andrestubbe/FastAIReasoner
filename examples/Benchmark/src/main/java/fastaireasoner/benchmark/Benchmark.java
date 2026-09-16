package fastaireasoner.benchmark;

import fastai.AI;
import fastai.AIRequest;
import fastai.AIResponse;
import fastai.Usage;
import fastaireasoner.FastAIReasoner;
import fastaireasoner.ReasoningResult;
import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 3, time = 1)
@Fork(1)
public class Benchmark {

    private FastAIReasoner cotReasoner;
    private FastAIReasoner totReasoner;

    @Setup
    public void setup() {
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

        cotReasoner = FastAIReasoner.chainOfThought(mockAI);
        totReasoner = FastAIReasoner.treeOfThoughts(mockAI, 3, 2);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public ReasoningResult benchmarkChainOfThought() {
        return cotReasoner.reason("Build lock-free ring buffer");
    }

    @org.openjdk.jmh.annotations.Benchmark
    public ReasoningResult benchmarkTreeOfThoughts() {
        return totReasoner.reason("Select architectural concurrency model");
    }
}