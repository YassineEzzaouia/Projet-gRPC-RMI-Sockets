package RMI;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class ClientRMI {
    public static void main(String[] args) {
        try {
            // Obtention de la référence du registre RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Obtention de la référence du service
            TaskListService taskListService = (TaskListService) registry.lookup("TaskListService");

            // Ajout de tâches
            taskListService.addTask("Faire les courses");
            taskListService.addTask("Réviser pour l'examen");

            // Récupération de la liste des tâches
            List<String> tasks = taskListService.getTaskList();
            System.out.println("Liste des tâches : ");
            for (String task : tasks) {
                System.out.println("- " + task);
            }

            // Suppression d'une tâche
            taskListService.removeTask(0);

            // Affichage de la liste des tâches après suppression
            tasks = taskListService.getTaskList();
            System.out.println("\nListe des tâches après suppression : ");
            for (String task : tasks) {
                System.out.println("- " + task);
            }
        } catch (Exception e) {
            System.err.println("Erreur survenue lors de l'exécution du client : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
