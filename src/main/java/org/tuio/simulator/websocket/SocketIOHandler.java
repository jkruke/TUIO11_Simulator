package org.tuio.simulator.websocket;

import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component
public class SocketIOHandler {

	private static final Logger log = LoggerFactory.getLogger(SocketIOHandler.class);
	private final SocketIONamespace namespace;

	public SocketIOHandler(SocketIOServer server) {
		this.namespace = server.addNamespace("/tuio");
		this.namespace.addConnectListener(onConnected());
		this.namespace.addDisconnectListener(onDisconnected());
	}

	private ConnectListener onConnected() {
		return client -> {
			log.info("connection established");
			HandshakeData handshakeData = client.getHandshakeData();
			log.debug("Client[{}] - Connected to chat module through '{}'", client.getSessionId().toString(), handshakeData.getUrl());
		};
	}

	private DisconnectListener onDisconnected() {
		return client -> {
			log.info("connection closed");
			log.debug("Client[{}] - Disconnected from chat module.", client.getSessionId().toString());
		};
	}

	public void broadcast(byte[] payload) {
		Object[] data = {payload};
		namespace.getBroadcastOperations().sendEvent("/", data);
	}
}
