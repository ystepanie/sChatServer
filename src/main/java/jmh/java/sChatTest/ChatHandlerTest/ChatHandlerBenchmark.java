package jmh.java.sChatTest.ChatHandlerTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketExtension;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ChatHandlerBenchmark {

	private final List<WebSocketSession> synchronizedList = Collections.synchronizedList(new ArrayList<>());
	private final List<WebSocketSession> copyOnWriteList = new CopyOnWriteArrayList<>();

	// WebSocketSession의 Mock 객체 생성 (가정)
	WebSocketSession mockSession = new MockWebSocketSession();

	@Setup(Level.Invocation)
	public void setup() {
		synchronizedList.clear();
		copyOnWriteList.clear();
	}

	@Benchmark
	@Threads(10) // 10개 스레드로 테스트
	public void testSynchronizedList_Add() throws Exception {
		synchronizedList.add(mockSession);
		broadcastParticipantList(synchronizedList);
		synchronizedList.remove(mockSession);
	}

	@Benchmark
	@Threads(10) // 10개 스레드로 테스트
	public void testCopyOnWriteArrayList_Add() throws Exception {
		copyOnWriteList.add(mockSession);
		broadcastParticipantList(copyOnWriteList);
		copyOnWriteList.remove(mockSession);
	}

	// 참가자 목록 전송 (실제 비즈니스 로직에 맞게 적용)
	private void broadcastParticipantList(List<WebSocketSession> sessions) throws Exception {
		synchronized (sessions) {
			for (WebSocketSession session : sessions) {
				session.sendMessage(new TextMessage("Participants: " + sessions.size()));
			}
		}
	}

	// Mock WebSocketSession 구현 (테스트 용도)
	class MockWebSocketSession implements WebSocketSession {
		// 필요한 메서드 구현
		@Override
		public String getId() {
			return "";
		}

		@Override
		public URI getUri() {
			return null;
		}

		@Override
		public HttpHeaders getHandshakeHeaders() {
			return null;
		}

		@Override
		public Map<String, Object> getAttributes() {
			return Map.of();
		}

		@Override
		public Principal getPrincipal() {
			return null;
		}

		@Override
		public InetSocketAddress getLocalAddress() {
			return null;
		}

		@Override
		public InetSocketAddress getRemoteAddress() {
			return null;
		}

		@Override
		public String getAcceptedProtocol() {
			return "";
		}

		@Override
		public void setTextMessageSizeLimit(int messageSizeLimit) {

		}

		@Override
		public int getTextMessageSizeLimit() {
			return 0;
		}

		@Override
		public void setBinaryMessageSizeLimit(int messageSizeLimit) {

		}

		@Override
		public int getBinaryMessageSizeLimit() {
			return 0;
		}

		@Override
		public List<WebSocketExtension> getExtensions() {
			return List.of();
		}

		@Override
		public void sendMessage(WebSocketMessage<?> message) throws IOException {

		}

		@Override
		public boolean isOpen() {
			return false;
		}

		@Override
		public void close() throws IOException {

		}

		@Override
		public void close(CloseStatus status) throws IOException {

		}

		// 그 외의 WebSocketSession 메서드 구현 생략
	}
}
