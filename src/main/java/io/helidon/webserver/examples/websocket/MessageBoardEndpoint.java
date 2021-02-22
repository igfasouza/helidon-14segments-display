package io.helidon.webserver.examples.websocket;

import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.Encoder;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;

/**
 * Class MessageBoardEndpoint.
 */
public class MessageBoardEndpoint extends Endpoint {
    private static final Logger LOGGER = Logger.getLogger(MessageBoardEndpoint.class.getName());

    private final MessageQueue messageQueue = MessageQueue.instance();

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String message) {
                try {
                    // Send all messages in the queue
                    if (message.equals("SEND")) {
                        while (!messageQueue.isEmpty()) {
                            session.getBasicRemote().sendObject(messageQueue.pop());
                        }
                    }
                } catch (Exception e) {
                    LOGGER.info(e.getMessage());
                }
            }
        });
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        super.onClose(session, closeReason);
    }

    @Override
    public void onError(Session session, Throwable thr) {
        super.onError(session, thr);
    }

    /**
     * Uppercase encoder.
     */
    public static class UppercaseEncoder implements Encoder.Text<String> {

        @Override
        public String encode(String s) {
            LOGGER.info("UppercaseEncoder encode called");
            return s.toUpperCase();
        }

        @Override
        public void init(EndpointConfig config) {
        }

        @Override
        public void destroy() {
        }
    }
    
}