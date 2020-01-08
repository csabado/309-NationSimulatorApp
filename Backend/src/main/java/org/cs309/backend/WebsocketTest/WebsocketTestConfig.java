package org.cs309.backend.WebsocketTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebsocketTestConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
	return new ServerEndpointExporter();
    }
}
