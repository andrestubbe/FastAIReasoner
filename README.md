# FastAIReasoner 0.1.0 — Multi-Step Reasoning & Cognitive Planning Engine for Java

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

## Quick Start — Example

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
- [Reasoning Strategies](#reasoning-strategies)
- [Architecture Overview](#architecture-overview)
- [API Quick Reference](#api-quick-reference)
- [Installation](#installation)
- [Documentation](#documentation)
- [Platform Support](#platform-support)
- [License](#license)
- [Related Projects](#related-projects)

---

## Why FastAIReasoner?

Standard LLM prompting produces linear, error-prone responses on multi-step logic and coding tasks. `FastAIReasoner` delivers:

- **State-Space Exploration** — Explores alternative reasoning branches before committing to an action.
- **Self-Consistency Scoring** — Samples multiple rationale paths and ranks the most reliable outcome.
- **Agent Integration** — Directly plugs into `FastAIAgent`'s planning and reflection phases.
- **Zero Framework Overhead** — Pure Java 17+ with sub-millisecond graph evaluation.

---

## Key Features

- **🌲 Tree-of-Thoughts (ToT)** — Explores tree-structured rationale paths with branch pruning.
- **🔗 Chain-of-Thought (CoT)** — Step-by-step sequential deduction with explicit verification gates.
- **🎯 Monte Carlo Tree Search (MCTS)** — Rollout simulations and heuristic value scoring for complex goal decomposition.
- **⚡ Fast Reflection & Self-Healing** — Evaluates execution anomalies and generates counter-plans.

---

## Architecture Overview

**FastAIReasoner (The Reasoner & Planning Engine)**  
Evaluates cognitive hypotheses, scores branches, and outputs optimal execution plans.

**[FastAIAgent](https://github.com/andrestubbe/FastAIAgent) (The Mind)**  
Consumes reasoning plans and orchestrates the ReAct loop (`Observe → Plan → Act → Reflect → Memory`).

**[FastAIRuntime](https://github.com/andrestubbe/FastAIRuntime) (The Body)**  
Executes the deterministic tool actions selected by the reasoner.

---

## API Quick Reference

| Method / Factory | Return Type | Description |
|---|---|---|
| `FastAIReasoner.chainOfThought(AI)` | `FastAIReasoner` | Sequential reasoning pipeline with step validation. |
| `FastAIReasoner.treeOfThoughts(AI, int, int)` | `FastAIReasoner` | Branching tree search exploring multiple candidate thoughts. |
| `FastAIReasoner.mcts(AI, int)` | `FastAIReasoner` | Heuristic Monte Carlo search for strategic planning. |
| `reasoner.reason(String goal)` | `ReasoningResult` | Evaluates the goal and returns the highest-scoring plan path. |

---

## Installation

### Option 1: Maven (Recommended)

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.andrestubbe</groupId>
        <artifactId>FastAIReasoner</artifactId>
        <version>0.1.0</version>
    </dependency>
    <dependency>
        <groupId>com.github.andrestubbe</groupId>
        <artifactId>fastai</artifactId>
        <version>0.1.7</version>
    </dependency>
</dependencies>
```

---

## License

MIT License — See [LICENSE](LICENSE) file for details.

---

## Related Projects

- [FastAIAgent](https://github.com/andrestubbe/FastAIAgent) — Autonomous agentic workflow execution framework
- [FastAIRuntime](https://github.com/andrestubbe/FastAIRuntime) — Deterministic tool execution engine and OS harness
- [FastAI](https://github.com/andrestubbe/FastAI) — Unified lightweight AI model client interface
