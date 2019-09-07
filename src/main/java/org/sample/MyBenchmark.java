
package org.sample;

/*
import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 1)
@Warmup(iterations = 2)
@Measurement(iterations = 5)
public class MyBenchmark {
    @Benchmark@BenchmarkMode(Mode.AverageTime) @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testMethod() {
        doMagic();
    }
    public static void doMagic() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignored) {
        }
    }
}*/




import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class MyBenchmark {
    final HashMap<String, Direction> directions = new HashMap<String, Direction>() {
        {
            put("0-1-1" , Direction.NORTH);
            put("011" , Direction.SOUTH);
            put("100" , Direction.EAST);
            put("-100" , Direction.WEST);
            put("000" , Direction.NONE);

            //val = new int[]{0,0};
            //directions.put(val , Direction.WAIT);

            put("111" , Direction.SOUTH_EAST);
            put("1-1-1" , Direction.NORTH_EAST);
            put("-111" , Direction.SOUTH_WEST);
            put("-1-1-1" , Direction.NORTH_WEST);
        }
    };

    @Param({"011", "-100", "000", "-100", "111","0-1-1", "-111", "1-1-1", "-1-1-1"})
    public String arg;

    @Benchmark
    public Direction mapDirectionsTest() {
        return directions.get(arg);
    }

    @Benchmark
    public Direction switchTest() {
        Direction direction = Direction.NONE;

        switch (arg) {
            case "0-1-1":
                direction = Direction.NORTH;
                break;
            case "011":
                direction = Direction.SOUTH;
                break;
            case "100":
                direction = Direction.EAST;
                break;
            case "-100":
                direction = Direction.WEST;
                break;
            case "000":
                direction = Direction.NONE;
                break;

            case "111":
                direction = Direction.SOUTH_EAST;
                break;
            case "1-1-1":
                direction = Direction.NORTH_EAST;
                break;
            case "-111":
                direction = Direction.SOUTH_WEST;
                break;
            case "-1-1-1":
                direction = Direction.NORTH_WEST;
                break;

            default:
                System.err.println("Default direction");
                break;
        }
        return direction;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .warmupIterations(2)
                .measurementIterations(2)
                .build();

        new Runner(opt).run();
    }

}