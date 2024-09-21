package jmh.java.sChatTest;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import jmh.java.sChatTest.pararrelTest.ListBenchmark;

public class BenchMarkTestMain {
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
			.include(ListBenchmark.class.getSimpleName())
			.forks(1)
			.build();

		new Runner(opt).run();
	}
}
