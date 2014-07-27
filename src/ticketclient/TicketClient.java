package ticketclient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import ry.ticket.Compute;
import ry.ticket.web.TicketFileServer;

/**
 *
 * @author ry
 */
public class TicketClient {

    public static void main(String[] args) {
        String ud = System.getProperty("user.dir");
        System.setProperty("java.security.policy", ud + "\\rmipolicy.txt");
        Thread srv = new Thread(new Runnable() {

            @Override
            public void run() {
                new TicketFileServer(4242);
            }
        });
        srv.start();
        
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry();
            Compute comp = (Compute) registry.lookup(name);
            TaskImpl task = new TaskImpl();
            String str = comp.executeTask(task);
            System.out.println(str);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
