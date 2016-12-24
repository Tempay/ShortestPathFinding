import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 远志 on 12/22/2016.
 */
public class Test {

    public static void main(String[] args) throws IOException{
        //List<Link> links= new BriteReader().loadTopo("1000.brite");
        List<Link> links=new ArrayList<>();
        links.add(new Link(0,1,0d,true));
        links.add(new Link(0,3,1000d,false));
        links.add(new Link(2,1,1000d,false));
        links.add(new Link(2,3,0d,true));
        links.add(new Link(1,3,0d,false));
        Graph g = new Graph(5,4,links);
        List<Node> nodes=new PathFinder().connectLinksToNodes(g);
        Solution solution1=new PathFinder().findShortestPath(nodes,0,2);
        Map<Set<Integer>,Integer> solution2=new CongestedPathFinder().findCongestedShortedPath(g,0,2);
        System.out.println(solution1.length);
        System.out.println("the best solution contains "+String.valueOf(solution2.size())+" path");
        for(Map.Entry<Set<Integer>,Integer> entry : solution2.entrySet()) {
            System.out.print("path:");
            for(Integer integer:entry.getKey()) {
                System.out.print(String.valueOf(integer)+",");
            }
            System.out.print("with a probability "+ String.valueOf(entry.getValue()) +"%\n");
        }
    //    System.out.println(solution2.length);

    }
}
