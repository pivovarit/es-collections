package com.pivovarit.escollections.list;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;

@State(Scope.Benchmark)
public class EsListBenchmark {

    @Benchmark
    public List<Integer> foo() {
        return null;
    }

    public static void main(String[] args) throws RunnerException {
        var result = new Runner(
          new OptionsBuilder()
            .include(EsListBenchmark.class.getSimpleName())
            .warmupIterations(4)
            .measurementIterations(4)
            .forks(1)
            .build()).run();
    }
}