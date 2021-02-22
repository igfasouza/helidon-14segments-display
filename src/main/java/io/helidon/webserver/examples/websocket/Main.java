package io.helidon.webserver.examples.websocket;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.websocket.Encoder;
import javax.websocket.server.ServerEndpointConfig;
import io.helidon.webserver.Routing;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.StaticContentSupport;
import io.helidon.webserver.tyrus.TyrusSupport;

import static io.helidon.webserver.examples.websocket.MessageBoardEndpoint.UppercaseEncoder;

/**
 * Application demonstrates combination of websocket and REST.
 */
public class Main {
	
	static ComponentService component = new ComponentService();

    private Main() {
    }

    /**
     * Creates new {@link Routing}.
     *
     * @return the new instance
     */
    static Routing createRouting() {
        List<Class<? extends Encoder>> encoders = Collections.singletonList(UppercaseEncoder.class);

        return Routing.builder()
                .register("/rest", component)
                .register("/websocket",
                        TyrusSupport.builder().register(
                                ServerEndpointConfig.Builder.create(MessageBoardEndpoint.class, "/board")
                                        .encoders(encoders).build())
                                .build())
                .register("/web", StaticContentSupport.builder("/WEB").build())
                .build();
    }

    static WebServer startWebServer() {
        WebServer server = WebServer.builder(createRouting())
                .port(8080)
                .build();

        // Start webserver
        CompletableFuture<Void> started = new CompletableFuture<>();
        server.start().thenAccept(ws -> {
            System.out.println("WEB server is up! http://localhost:" + ws.port());
            started.complete(null);
        });

        // Wait for webserver to start before returning
        try {
            started.toCompletableFuture().get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return server;
    }

    /**
     * A java main class.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        WebServer server = startWebServer();

        // Server threads are not demon. NO need to block. Just react.
        server.whenShutdown()
                .thenRun(() -> System.out.println("WEB server is DOWN. Good bye!"));

    }
    
}