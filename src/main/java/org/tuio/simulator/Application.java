package org.tuio.simulator;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.tuio.simulator.ui.TuioSimulator;

@SpringBootApplication
public class Application {

	@Value("${server.port}")
	private Integer port;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//@Bean
	public SocketIOServer socketIOServer() {
		com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
		config.setHostname("localhost");
		config.setPort(port);
		return new SocketIOServer(config);
	}

	@Component
	private static class Runner implements CommandLineRunner {

		@Value("${tuiosim.config:#{null}}")
		private String config;

		@Override
		public void run(String... args) {
			System.setProperty("java.awt.headless", "false");
			TuioSimulator.start(config);
		}
	}

}
