> [!WARNING]
> **🚧 WIP — Active AI Pipeline Construction & Architecture Optimization in Progress.**

# FastAIReasoner [ALPHA-2026-09-01] — Multi-Step Reasoning & Cognitive Planning Engine for Java

[![Status](https://img.shields.io/badge/status-0.1.0-brightgreen.svg)](https://github.com/andrestubbe/FastAIReasoner/releases/tag/0.1.0)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.java.com)
[![Platform](https://img.shields.io/badge/Platform-Windows%2010+-lightgrey.svg)]()
[![JitPack](https://img.shields.io/badge/JitPack-ready-green.svg)](https://jitpack.io/#andrestubbe/FastAIReasoner)

---

**⚡ Multi-step reasoning, Chain-of-Thought (CoT), Tree-of-Thoughts (ToT), and heuristic path evaluation — Deep cognitive planning engine for the FastJava AI ecosystem.**

FastAIReasoner provides structured cognitive search and reasoning capabilities for Java AI agents. It evaluates solution paths, explores branch states, and performs self-consistency verification before actions are executed in `FastAIAgent` or `FastAIRuntime`.

<p align="center">
  <img src="docs/reasoner_strategies.jpg" alt="FastAIReasoner Cognitive Strategies" width="850">
</p>

---

## Quick Start

```java
import fastai.AI;
import fastai.FastAI;
import fastaireasoner.FastAIReasoner;
import fastaireasoner.ReasoningResult;

public class Demo {
    public static void main(String[] args) {
        AI brain = FastAI.connect("ollama:qwen2.5-coder:7b");

        // 1. Instantiate reasoning engine with Tree-of-Thoughts (ToT) strategy
        FastAIReasoner reasoner = FastAIReasoner.treeOfThoughts(brain, 3 /* branches */, 2 /* depth */);

        // 2. Perform deep reasoning on complex architectural problem
        ReasoningResult result = reasoner.reason("Design a lock-free event dispatcher in Java");

        System.out.println("Best Plan: " + result.bestPath());
        System.out.println("Confidence: " + result.confidenceScore());
    }
}
```

---

## Table of Contents

- [Why FastAIReasoner?](#why-fastaireasoner)
- [Key Features](#key-features)
- [Architecture Overview](#architecture-overview)
- [API Quick Reference](#api-quick-reference)
- [Running Demos & Benchmarks](#running-demos--benchmarks)
- [Installation](#installation)
- [Documentation](#documentation)
- [Platform Support](#platform-support)
- [License](#license)
- [Related Projects](#related-projects)

---

## Why FastAIReasoner?

Standard LLM prompting generates linear, token-by-token text without internal verification, leading to hallucinations, circular logic, and brittle plans in multi-step enterprise workflows. FastAIReasoner transforms raw generative models into disciplined problem-solving engines through deliberate heuristic search and self-evaluation:

- **State-Space Exploration** — Explores alternative branching thoughts before committing to irreversible actions.
- **Self-Consistency Scoring** — Samples multiple rationale paths and ranks the most dependable strategy.
- **Agent Integration** — Directly plugs into `FastAIAgent`'s planning and reflection phases.
- **Zero Framework Overhead** — Pure Java 17+ architecture with sub-millisecond graph evaluation.

| Feature | Standard LLM Prompting | LangChain / LangGraph | FastAIReasoner |
|:---|:---|:---|:---|
| **Reasoning Model** | Linear single-pass | Graph DSL overhead | Chain-of-Thought & Tree-of-Thoughts |
| **Branch Exploration** | ❌ None | ⚠️ Heavyweight agent graphs | ✅ Native multi-branch state evaluation |
| **Confidence Scoring** | ❌ None | ⚠️ Ad-hoc prompts | ✅ Built-in confidence metrics |
| **Memory Footprint** | Low | High (Python / bulky JVM) | Zero-bloat, pure Java 17+ |
| **Ecosystem Synergy** | Standalone | Fragmented | Native fit with FastAI & FastAIAgent |

---

## Key Features

- 🌲 **Tree-of-Thoughts (ToT)** — Explores tree-structured rationale paths with branch pruning.
- 🔗 **Chain-of-Thought (CoT)** — Step-by-step sequential deduction with explicit verification gates.
- 🎯 **Confidence Scoring** — Evaluates rationale coherence with threshold-based confidence checks.
- ⚡ **Zero-Bloat Cognitive Engine** — Lightweight Java record structures and lock-free execution.
- 🔌 **Seamless FastAI Integration** — Works out-of-the-box with any model supported by `FastAI`.

---

## Architecture Overview

- 🧠 **[FastAIReasoner](https://github.com/andrestubbe/FastAIReasoner)** (The Reasoner & Planning Engine): Evaluates cognitive hypotheses, scores branches, and outputs optimal execution plans.
- 🤖 **[FastAIAgent](https://github.com/andrestubbe/FastAIAgent)** (The Mind): Consumes reasoning plans and orchestrates the ReAct loop (`Observe → Plan → Act → Reflect → Memory`).
- ⚡ **[FastAIRuntime](https://github.com/andrestubbe/FastAIRuntime)** (The Body): Executes deterministic tool actions selected by the reasoner.

---

## API Quick Reference

| Method / Factory | Return Type | Description |
|---|---|---|
| `FastAIReasoner.chainOfThought(AI)` | `FastAIReasoner` | Sequential reasoning pipeline with step validation. |
| `FastAIReasoner.treeOfThoughts(AI, int, int)` | `FastAIReasoner` | Branching tree search exploring multiple candidate thoughts. |
| `reasoner.reason(String goal)` | `ReasoningResult` | Evaluates the goal and returns the highest-scoring plan path. |
| `result.isConfident()` | `boolean` | Verifies whether confidence score meets threshold (>= 0.70). |

---

## Running Demos & Benchmarks

FastAIReasoner provides one-click batch scripts for verification and performance evaluation:

### 1. Run Demo
Showcases Chain-of-Thought and Tree-of-Thoughts planning strategies:
```cmd
run-demo.bat
```

### 2. Run JMH Benchmarks
Measures throughput of reasoning strategies under realistic workloads:
```cmd
run-benchmark.bat
```

---

## Installation

### Option 1: Maven (Recommended)

Add the JitPack repository and the dependencies to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <!-- FastAIReasoner Library -->
    <dependency>
        <groupId>com.github.andrestubbe</groupId>
        <artifactId>FastAIReasoner</artifactId>
        <version>0.1.0</version>
    </dependency>

    <!-- FastAI (Unified AI Client) -->
    <dependency>
        <groupId>com.github.andrestubbe</groupId>
        <artifactId>fastai</artifactId>
        <version>0.1.4</version>
    </dependency>
</dependencies>
```

### Option 2: Gradle (via JitPack)

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.andrestubbe:FastAIReasoner:0.1.0'
    implementation 'com.github.andrestubbe:fastai:0.1.4'
}
```

### Option 3: Direct Download (No Build Tool)

Download the latest JARs directly to add them to your classpath:

1. 📦 **[FastAIReasoner-0.1.0.jar](https://github.com/andrestubbe/FastAIReasoner/releases/download/0.1.0/FastAIReasoner-0.1.0.jar)** (The Core Library)
2. ⚙️ **[fastcore-0.1.0.jar](https://github.com/andrestubbe/FastCore/releases/download/0.1.0/fastcore-0.1.0.jar)** (The Mandatory Native Loader)

---

## Documentation

* **[REFERENCE.md](docs/REFERENCE.md)**: Core API reference manual.
* **[PHILOSOPHY.md](docs/PHILOSOPHY.md)**: Multi-step reasoning and cognitive planning architecture.
* **[COMPILE.md](docs/COMPILE.md)**: Build instructions.
* **[CHANGELOG.md](docs/CHANGELOG.md)**: Project history and releases.
* **[ROADMAP.md](docs/ROADMAP.md)**: Future milestones.

---

## Platform Support

| Operating System | Architecture | Build Status | Support Level |
|:---|:---|:---|:---|
| **Windows 10 / 11** | `x64` | ![Passing](https://img.shields.io/badge/build-passing-brightgreen.svg) | Tier 1 (Primary / Optimized) |
| **Linux (Ubuntu / RHEL)** | `x64` | ![Passing](https://img.shields.io/badge/build-passing-brightgreen.svg) | Tier 1 (Pure Java Engine) |
| **macOS (Sonoma+)** | `Apple Silicon (arm64)` | ![Passing](https://img.shields.io/badge/build-passing-brightgreen.svg) | Tier 1 (Pure Java Engine) |
| **macOS** | `x64` | ![Passing](https://img.shields.io/badge/build-passing-brightgreen.svg) | Tier 2 (Supported) |

---

## License

MIT License — See [LICENSE](LICENSE) file for details.

---

## Related Projects

- [FastAI](https://github.com/andrestubbe/FastAI) — Unified AI client interface for Java
- [FastAIAgent](https://github.com/andrestubbe/FastAIAgent) — Autonomous agent loop, intent-graphs, and tool execution
- [FastAIBot](https://github.com/andrestubbe/FastAIBot) — Zero-bloat bot harnesses and persona runtime
- [FastAIEval](https://github.com/andrestubbe/FastAIEval) — Ultra-fast LLM & agent evaluation framework
- [FastAIGraph](https://github.com/andrestubbe/FastAIGraph) — In-memory knowledge graph and multi-hop relationship engine
- [FastAIGuard](https://github.com/andrestubbe/FastAIGuard) — Fast guardrails, prompt safety, and hallucination containment
- [FastAIHybrid](https://github.com/andrestubbe/FastAIHybrid) — Dense-sparse hybrid search fusion (BM25 + Vectors)
- [FastAIMatcher](https://github.com/andrestubbe/FastAIMatcher) — Automated SOX compliance and hybrid rule matching engine
- [FastAIMCP](https://github.com/andrestubbe/FastAIMCP) — Model Context Protocol (MCP) server & tool integration
- [FastAIMemory](https://github.com/andrestubbe/FastAIMemory) — Conversation history, sliding windows, and rolling summaries
- [FastAIMemoryGraph](https://github.com/andrestubbe/FastAIMemoryGraph) — Graph-based episodic and associative memory engine
- [FastAIMetrics](https://github.com/andrestubbe/FastAIMetrics) — Ultra-fast lock-free token, latency, cost tracking and evaluation engine
- [FastAIModel](https://github.com/andrestubbe/FastAIModel) — Native local inference runtime (GGUF/ONNX)
- [FastAIRag](https://github.com/andrestubbe/FastAIRag) — Ultra-fast document chunking and vector retrieval
- [FastAIRerank](https://github.com/andrestubbe/FastAIRerank) — Cross-encoder relevance filtering and Top-N prompt pruner
- [FastAIRuntime](https://github.com/andrestubbe/FastAIRuntime) — Sandboxed process runner and tool-calling execution pipeline
- [FastAISandbox](https://github.com/andrestubbe/FastAISandbox) — Lightweight isolated execution environment for untrusted AI tools
- [FastAISkill](https://github.com/andrestubbe/FastAISkill) — Modular capability registry and dynamic tool dispatch
- [FastAIState](https://github.com/andrestubbe/FastAIState) — Lock-free shared agent state & blackboard memory
- [FastAIVectorDB](https://github.com/andrestubbe/FastAIVectorDB) — High-throughput SIMD/AVX2 vector database
- [FastAIVision](https://github.com/andrestubbe/FastAIVision) — High-speed local multimodal vision, UI-element grounding, and screen-VLM engine
- [FastCore](https://github.com/andrestubbe/FastCore) — Unified JNI loader and platform abstraction

---

**Part of the FastJava Ecosystem** — *Making the JVM faster. Small package. Maximum speed. Zero bloat. 🚀📋*