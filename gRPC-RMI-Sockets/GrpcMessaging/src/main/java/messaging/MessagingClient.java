package messaging;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class MessagingClient {

    private final ManagedChannel channel;
    private final MessagingServiceGrpc.MessagingServiceBlockingStub blockingStub;
    private final Scanner scanner;

    public MessagingClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = MessagingServiceGrpc.newBlockingStub(channel);
        scanner = new Scanner(System.in);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void sendMessage(String senderId, String recipientId, String message) {
        MessagingServiceOuterClass.MessageRequest request = MessagingServiceOuterClass.MessageRequest.newBuilder()
                .setSenderId(senderId)
                .setRecipientId(recipientId)
                .setMessage(message)
                .build();

        MessagingServiceOuterClass.MessageResponse response = blockingStub.sendMessage(request);
        System.out.println("Message sent. Status: " + response.getStatus());
    }

    public void getMessagesForUser(String userId) {
        MessagingServiceOuterClass.UserRequest request = MessagingServiceOuterClass.UserRequest.newBuilder()
                .setUserId(userId)
                .build();

        MessagingServiceOuterClass.MessagesResponse response = blockingStub.getMessagesForUser(request);
        for (MessagingServiceOuterClass.Message msg : response.getMessageList()) {
            System.out.println("Message ID: " + msg.getMessageId());
            System.out.println("Sender ID: " + msg.getSenderId());
            System.out.println("Message: " + msg.getMessage());
        }
    }

    public void run() {
        System.out.println("Welcome to Messaging App!");
        boolean running = true;
        while (running) {
            System.out.println("Press 's' to send a message, 'r' to retrieve messages, or 'q' to quit:");
            String choice = scanner.nextLine();
            switch (choice) {
                case "s":
                    sendMessagePrompt();
                    break;
                case "r":
                    retrieveMessagesPrompt();
                    break;
                case "q":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void sendMessagePrompt() {
        System.out.print("Enter sender ID: ");
        String senderId = scanner.nextLine();
        System.out.print("Enter recipient ID: ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter message: ");
        String message = scanner.nextLine();
        sendMessage(senderId, recipientId, message);
    }

    private void retrieveMessagesPrompt() {
        System.out.print("Enter user ID to fetch messages: ");
        String userId = scanner.nextLine();
        getMessagesForUser(userId);
    }

    public static void main(String[] args) throws Exception {
        MessagingClient client = new MessagingClient("localhost", 50051);
        client.run();
        client.shutdown();
    }
}
