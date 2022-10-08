package org.tuio.simulator.websocket;

import com.illposed.osc.websocket.IWebSocketHandler;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler implements IWebSocketHandler {

	private static final Logger log = LoggerFactory.getLogger(SocketHandler.class);
	private static final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("connection established");
		sessions.add(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("connection closed");
		sessions.remove(session);
	}

	@Override
	public void broadcast(byte[] payload) {
		for (WebSocketSession session : sessions) {
			try {
				session.sendMessage(new BinaryMessage(payload));
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		}
	}
}
