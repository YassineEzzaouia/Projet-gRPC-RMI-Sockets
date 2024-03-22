package messaging;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class MessagingServer {

    private Server server;

    private void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new MessagingServiceImpl())
                .build()
                .start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** Shutting down gRPC server since JVM is shutting down");
            MessagingServer.this.stop();
            System.err.println("*** Server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    // Await termination on the main thread since the grpc library uses daemon threads.
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final MessagingServer server = new MessagingServer();
        server.start();
        server.blockUntilShutdown();
    }
}
