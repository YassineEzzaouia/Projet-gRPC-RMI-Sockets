import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ALICE {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             Scanner scanner = new Scanner(System.in);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Connecté au serveur de chat.");
            new Thread(() -> {
                try (Scanner in = new Scanner(socket.getInputStream())) {
                    while (in.hasNextLine()) {
                        String receivedMessage = in.nextLine();
                        if (!receivedMessage.startsWith("[ALICE]")) {
                            System.out.println(receivedMessage);
                        }
                    }                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            while (true) {
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("/quit")) {
                    break;
                }
                out.println("[ALICE] " + message); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
