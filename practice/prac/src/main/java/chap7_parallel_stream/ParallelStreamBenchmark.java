package chap7_parallel_stream;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = { "-Xms4G", "-Xmx4G" })
@Measurement(iterations = 2)
@Warmup(iterations = 3)
public class ParallelStreamBenchmark {

  private static final long N = 10_000_000L;

  /**
   * "ParallelStreamBenchmark.iterativeSum":
    6.054 ±(99.9%) 0.388 ms/op [Average]
    (min, avg, max) = (5.995, 6.054, 6.135), stdev = 0.060
    CI (99.9%): [5.666, 6.442] (assumes normal distribution)
   */
  @Benchmark
  public long iterativeSum() {
    long result = 0;
    for (long i = 1L; i <= N; i++) {
      result += i;
    }
    return result;
  }

  /**
   * "ParallelStreamBenchmark.sequentialSum":
  147.319 ±(99.9%) 27.673 ms/op [Average]
  (min, avg, max) = (141.101, 147.319, 150.699), stdev = 4.282
  CI (99.9%): [119.646, 174.992] (assumes normal distribution)
   */
  @Benchmark
  public long sequentialSum() {
    return Stream.iterate(1L, i -> i + 1).limit(N).reduce(0L, Long::sum);
  }

  /**
   * "ParallelStreamBenchmark.parallelSum":
    123.609 ±(99.9%) 14.724 ms/op [Average]
    (min, avg, max) = (120.609, 123.609, 126.044), stdev = 2.279
    CI (99.9%): [108.885, 138.333] (assumes normal distribution)
   */
  @Benchmark
  public long parallelSum() {
    return Stream.iterate(1L, i -> i + 1).limit(N).parallel().reduce(0L, Long::sum);
  }

  /**
   * "ParallelStreamBenchmark.rangedSum":
    8.059 ±(99.9%) 0.392 ms/op [Average]
    (min, avg, max) = (7.981, 8.059, 8.129), stdev = 0.061
    CI (99.9%): [7.666, 8.451] (assumes normal distribution)
   */
  @Benchmark
  public long rangedSum() {
    return LongStream.rangeClosed(1, N).reduce(0L, Long::sum);
  }

  /**
   * "ParallelStreamBenchmark.parallelRangedSum":
      1.418 ±(99.9%) 0.254 ms/op [Average]
      (min, avg, max) = (1.377, 1.418, 1.455), stdev = 0.039
      CI (99.9%): [1.164, 1.672] (assumes normal distribution)
   */
  @Benchmark
  public long parallelRangedSum() {
    return LongStream.rangeClosed(1, N).parallel().reduce(0L, Long::sum);
  }

  @TearDown(Level.Invocation)
  public void tearDown() {
    System.gc();
  }

}

/**
Benchmark                                  Mode  Cnt    Score    Error  Units
ParallelStreamBenchmark.iterativeSum       avgt    4    6.054 ±  0.388  ms/op
ParallelStreamBenchmark.parallelRangedSum  avgt    4    1.418 ±  0.254  ms/op
ParallelStreamBenchmark.parallelSum        avgt    4  123.609 ± 14.724  ms/op
ParallelStreamBenchmark.rangedSum          avgt    4    8.059 ±  0.392  ms/op
ParallelStreamBenchmark.sequentialSum      avgt    4  147.319 ± 27.673  ms/op
 */