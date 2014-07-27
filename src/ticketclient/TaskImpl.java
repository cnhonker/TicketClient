package ticketclient;

import java.io.Serializable;
import ry.ticket.Task;

/**
 *
 * @author ry
 */
public class TaskImpl implements Task<String>, Serializable{

    @Override
    public String execute() {
        return "Task Completed!";
    }
}
