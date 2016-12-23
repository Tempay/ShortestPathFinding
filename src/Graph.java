import java.util.List;

/**
 * Created by 远志 on 12/22/2016.
 */
public class Graph {
    int numOfNodes;
    int numOfLinks;
    List<Link> links;
    public Graph(int numOfLinks,int numOfNodes, List<Link> links) {
        this.numOfLinks=numOfLinks;
        this.numOfNodes=numOfNodes;
        this.links=links;
    }
}
