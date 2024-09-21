package jmh.java.sChatTest.pararrelTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput) // 초당 수행횟수
@OutputTimeUnit(TimeUnit.MILLISECONDS) // 밀리초 단위로 결과출력
@Warmup(iterations = 2) // 워밍업
@Measurement(iterations = 5) // 실제 반복 횟수
public class ListBenchmark {

	private List<String> copyOnWriteList;
	private List<String> synchronizedList;

	@Setup
	public void setup() {
		//초기화
		copyOnWriteList = new CopyOnWriteArrayList<>();
		synchronizedList = Collections.synchronizedList(new ArrayList<>());

		//유저 10명 가정
		for(int i=0; i<10; i++) {
			copyOnWriteList.add("user"+i);
			synchronizedList.add("user"+i);
		}
	}

	@Benchmark
	public void testCopyOnWriteArrayList() {
		// 유저 1명 접속, 메시지 전송 10번, 유저 1명 제거
		copyOnWriteList.add("user11");
		for(String s : copyOnWriteList) {
			System.out.println("copyonwrite send message " + s + " >> ");
		}
		copyOnWriteList.remove(0);
	}

	@Benchmark
	public void testSynchronizedList() {
		// 유저 1명 접속, 메시지 전송 10번, 유저 1명 제거
		synchronizedList.add("user11");
		for(String s : synchronizedList) {
			System.out.println("synchronized send message " + s + " >> ");
		}
		synchronizedList.remove(0);
	}
}
