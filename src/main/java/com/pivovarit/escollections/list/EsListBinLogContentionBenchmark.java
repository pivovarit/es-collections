package com.pivovarit.escollections.list;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;

@State(Scope.Benchmark)
public class EsListBinLogContentionBenchmark {

    private static final int THREADS = 16;

    private static final List<Integer> list = ESList.newInstance();
    private static final List<Integer> arraylist = new ArrayList<>();

    @Threads(1)
    @Benchmark
    public boolean binlog_contention_baseline() {
        return arraylist.add(42);
    }

    @Threads(1)
    @Benchmark
    public boolean binlog_contention() {
        return list.add(42);
    }

    @Threads(16)
    @Benchmark
    public boolean binlog_contented() {
        return list.add(42);
    }

    // TODO reasonable values
    public static void main(String[] args) throws RunnerException {
        var result = new Runner(
          new OptionsBuilder()
            .include(EsListBinLogContentionBenchmark.class.getSimpleName())
            .warmupIterations(1)
            .measurementIterations(1)
            .forks(1)
            .build()).run();
    }
}