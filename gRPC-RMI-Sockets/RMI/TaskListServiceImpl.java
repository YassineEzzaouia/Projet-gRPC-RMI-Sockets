package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TaskListServiceImpl extends UnicastRemoteObject implements TaskListService {
    private List<String> tasks;

    protected TaskListServiceImpl() throws RemoteException {
        super();
        tasks = new ArrayList<>();
    }

    @Override
    public synchronized void addTask(String task) throws RemoteException {
        tasks.add(task);
    }

    @Override
    public synchronized void removeTask(int index) throws RemoteException {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    @Override
    public synchronized List<String> getTaskList() throws RemoteException {
        return new ArrayList<>(tasks);
    }
}
