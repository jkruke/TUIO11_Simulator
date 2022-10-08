package org.tuio.simulator;

import com.illposed.osc.IOSCPortOut;
import com.illposed.osc.OSCPortOut;
import com.illposed.osc.websocket.IWebSocketHandler;
import com.illposed.osc.websocket.OSCPortWSOut;
import java.net.InetAddress;
import java.net.SocketException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	private static final Logger log = LoggerFactory.getLogger(BeanConfig.class);

	private final IWebSocketHandler socketHandler;
	@Value("${tuiosim.useWebsocket}")
	private boolean useWebsocket;
	@Value("${tuiosim.host}")
	private InetAddress host;
	@Value("${tuiosim.port}")
	private int port;

	public BeanConfig(IWebSocketHandler socketHandler) {
		this.socketHandler = socketHandler;
	}

	@Bean
	public IOSCPortOut oscPortOut() {
		if (useWebsocket) {
			log.info("Sending TUIO messages to port {} via WebSocket", port);
			return new OSCPortWSOut(socketHandler);
		}
		try {
			log.info("Sending TUIO messages to {}:{} via UDP", host, port);
			return new OSCPortOut(host, port);
		} catch (SocketException e) {
			throw new IllegalStateException("Configuration error", e);
		}
	}
}
