package jmh.java.sChatTest;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import jmh.java.sChatTest.ChatHandlerTest.ChatHandlerBenchmark;
import jmh.java.sChatTest.pararrelTest.ListBenchmark;

public class BenchMarkTestMain {
	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
			.include(ChatHandlerBenchmark.class.getSimpleName())
			.forks(1)
			// .jvmArgs("-Xms2g", "-Xmx2g", "-XX:+PrintGC")
			.build();

		new Runner(opt).run();
	}
}
