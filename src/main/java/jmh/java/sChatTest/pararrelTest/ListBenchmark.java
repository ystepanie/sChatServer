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
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput) // 초당 수행횟수
@OutputTimeUnit(TimeUnit.MILLISECONDS) // 밀리초 단위로 결과출력
@Warmup(iterations = 2) // 워밍업
@Measurement(iterations = 5) // 실제 반복 횟수
public class ListBenchmark {

	private List<String> copyOnWriteList;
	private List<String> synchronizedList;

	@Setup(Level.Trial)
	public void setup() {
		//초기화
		copyOnWriteList = new CopyOnWriteArrayList<>();
		synchronizedList = Collections.synchronizedList(new ArrayList<>());
		// // 유저 500명 가정
		// for(int i=0; i<5000; i++) {
		// 	copyOnWriteList.add("user"+i);
		// 	synchronizedList.add("user"+i);
		// }
	}

	@Benchmark
	@Threads(10)
	public void testCopyOnWriteArrayList() {
		// 유저 5명 접속, 세션 정보 조회, 세션에 메시지 발송(총 2번 조회)
		// copyOnWriteList = new CopyOnWriteArrayList<>();
		//접속
		copyOnWriteList.add("user");

		for(int k=0; k<copyOnWriteList.size(); k++) {
			// 유저 리스트 만들기 위해 조회
			copyOnWriteList.get(k);
		}

		for(int k=0; k<copyOnWriteList.size(); k++) {
			// 각각 유저에게 뿌리기 위해 조회
			copyOnWriteList.get(k);
		}
	}

	@Benchmark
	public void testCOWAChat() {
		//10명의 유저가 총 100개의 채팅을 침
		for(int i=0; i<100; i++) {
			for(int k=0; k<copyOnWriteList.size(); k++) {
				// 각각 유저에게 뿌리기 위해 조회
				copyOnWriteList.get(k);
			}
		}
	}

	@Benchmark
	@Threads(10)
	public void testSynchronizedList() {
		// synchronizedList = Collections.synchronizedList(new ArrayList<>());
		//접속
		synchronizedList.add("user");
		for(int k=0; k<synchronizedList.size(); k++) {
			// 유저 리스트 만들기 위해 조회
			synchronizedList.get(k);
		}

		for(int k=0; k<synchronizedList.size(); k++) {
			// 각각 유저에게 뿌리기 위해 조회
			synchronizedList.get(k);
		}
	}

	@Benchmark
	public void testSLChat() {
		//10명의 유저가 총 100개의 채팅을 침
		for(int i=0; i<100; i++) {
			for(int k=0; k<synchronizedList.size(); k++) {
				// 각각 유저에게 뿌리기 위해 조회
				synchronizedList.get(k);
			}
		}
	}
}
