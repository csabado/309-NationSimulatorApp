package org.cs309.backend.WebsocketTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@ServerEndpoint("/websocket/{id}")
@Component
public class WebsocketTestController {
    private Session session;
    private static Map<Session, Long> sessionIdMap = new HashMap<>();
    private static Map<Long, Session> idSessionMap = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(WebsocketTestController.class);
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) throws IOException {
	sessionIdMap.put(session, Long.parseLong(id));
	idSessionMap.put(Long.parseLong(id), session);

	String message = "id " + id + " connected";
	logger.info(message);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException {
	logger.info("Received message: " + message);
	sendMessageToUser(sessionIdMap.get(session), "Message received");
    }

    @OnClose
    public void onClose(Session session) throws IOException {
	Long id = sessionIdMap.get(session);
	logger.info(Long.toString(id) + " session closed");
    }

    private static void sendMessageToUser(Long id, String message) {
	try {
	    idSessionMap.get(id).getBasicRemote().sendText(message);
	}
	catch (IOException e) {
	    logger.info("Exception: " + e.getMessage().toString());
	}
    }

    public static void broadcastMessageToUsers(String message) throws IOException {
	sessionIdMap.forEach((session, id) -> {
		synchronized (session) {
		    try {
			session.getBasicRemote().sendText(message);
		    }
		    catch (IOException e) {
			logger.info(e.getMessage().toString());
		    }
		}
	    });
    }
}
