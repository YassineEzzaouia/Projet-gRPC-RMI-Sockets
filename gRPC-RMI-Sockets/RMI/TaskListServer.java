package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TaskListServer {
    public static void main(String[] args) {
        try {
            // Création de l'instance du service
            TaskListService taskListService = new TaskListServiceImpl();

            // Enregistrement du service dans le registre RMI
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("TaskListService", taskListService);

            System.out.println("Serveur prêt...");
        } catch (Exception e) {
            System.err.println("Erreur survenue lors du démarrage du serveur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
