package jmh.java.sChatTest.pararrelTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
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

	@Setup(Level.Invocation)
	public void setup() {
		//초기화
		copyOnWriteList = new CopyOnWriteArrayList<>();
		synchronizedList = Collections.synchronizedList(new ArrayList<>());
		// //유저 10명 가정
		// for(int i=0; i<10000; i++) {
		// 	copyOnWriteList.add("user"+i);
		// 	synchronizedList.add("user"+i);
		// }
	}

	@Benchmark
	public void testCopyOnWriteArrayList() {
		// 유저 5명 접속, 세션 정보 조회, 세션에 메시지 발송(총 2번 조회)
		copyOnWriteList.add("user"+11);
		copyOnWriteList.add("user"+12);
		copyOnWriteList.add("user"+13);
		copyOnWriteList.add("user"+14);
		copyOnWriteList.add("user"+15);

		for(int i=0; i<5; i++) {
			for(int j=0; j<2; j++) {
				for(String s : copyOnWriteList) {
					// 메세지 전송
				}
			}
		}

		copyOnWriteList.remove("user"+11);
		copyOnWriteList.remove("user"+12);
		copyOnWriteList.remove("user"+13);
		copyOnWriteList.remove("user"+14);
		copyOnWriteList.remove("user"+15);
		for(int i=0; i<5; i++) {
			for(int j=0; j<2; j++) {
				for(String s : copyOnWriteList) {
					// 메세지 전송
				}
			}
		}
	}

	@Benchmark
	public void testSynchronizedList() {
		// 유저 5명 접속, 세션 정보 조회, 세션에 메시지 발송(총 2번 조회)
		copyOnWriteList.add("user"+11);
		copyOnWriteList.add("user"+12);
		copyOnWriteList.add("user"+13);
		copyOnWriteList.add("user"+14);
		copyOnWriteList.add("user"+15);

		for(int i=0; i<5; i++) {
			for(int j=0; j<2; j++) {
				for(String s : copyOnWriteList) {
					// 메세지 전송
				}
			}
		}

		copyOnWriteList.remove("user"+11);
		copyOnWriteList.remove("user"+12);
		copyOnWriteList.remove("user"+13);
		copyOnWriteList.remove("user"+14);
		copyOnWriteList.remove("user"+15);

		for(int i=0; i<5; i++) {
			for(int j=0; j<2; j++) {
				for(String s : copyOnWriteList) {
					// 메세지 전송
				}
			}
		}
	}
}
