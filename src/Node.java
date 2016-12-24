import java.util.ArrayList;
import java.util.List;

/**
 * Created by 远志 on 12/22/2016.
 */
public class Node {
    int id;
    List<Connection> connections;
    public Node(int id) {
        this.id=id;
        this.connections=new ArrayList<>();
    }

    public void addConnection(int node1, double length,int id) {
        this.connections.add(new Connection(node1,length,id));
    }

    public void changeConnection(Double length, int linkId) {
        for(Connection connection: connections) {
            if(connection.id==linkId) {
                connection.length=length;
            }
        }
    }
}
