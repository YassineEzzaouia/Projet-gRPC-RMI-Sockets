package messaging;

import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MessagingServiceImpl extends MessagingServiceGrpc.MessagingServiceImplBase {

    // Une structure de données pour stocker les messages envoyés
    private final Map<String, Map<String, MessagingServiceOuterClass.Message>> userMessages = new HashMap<>();

    @Override
    public void sendMessage(MessagingServiceOuterClass.MessageRequest request, StreamObserver<MessagingServiceOuterClass.MessageResponse> responseObserver) {
        String messageId = UUID.randomUUID().toString();
        String senderId = request.getSenderId();
        String recipientId = request.getRecipientId();
        String messageContent = request.getMessage();

        // Vérifier si l'utilisateur a déjà des messages dans la map
        if (!userMessages.containsKey(recipientId)) {
            userMessages.put(recipientId, new HashMap<>());
        }

        // Enregistrer le message
        Map<String, MessagingServiceOuterClass.Message> messagesForRecipient = userMessages.get(recipientId);
        MessagingServiceOuterClass.Message message = MessagingServiceOuterClass.Message.newBuilder()
                .setMessageId(messageId)
                .setSenderId(senderId)
                .setMessage(messageContent)
                .build();
        messagesForRecipient.put(messageId, message);

        // Répondre avec le statut de succès
        MessagingServiceOuterClass.MessageResponse response = MessagingServiceOuterClass.MessageResponse.newBuilder()
                .setMessageId(messageId)
                .setStatus("Message sent successfully")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getMessagesForUser(MessagingServiceOuterClass.UserRequest request, StreamObserver<MessagingServiceOuterClass.MessagesResponse> responseObserver) {
        String userId = request.getUserId();
        MessagingServiceOuterClass.MessagesResponse.Builder responseBuilder = MessagingServiceOuterClass.MessagesResponse.newBuilder();

        // Vérifier si l'utilisateur a des messages
        if (userMessages.containsKey(userId)) {
            Map<String, MessagingServiceOuterClass.Message> messagesForUser = userMessages.get(userId);
            for (MessagingServiceOuterClass.Message message : messagesForUser.values()) {
                responseBuilder.addMessage(message);
            }
            // Supprimer les messages une fois récupérés
            userMessages.remove(userId);
        }

        // Répondre avec les messages récupérés
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}
