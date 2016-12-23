import java.util.ArrayList;
import java.util.List;

/**
 * Created by 远志 on 12/22/2016.
 */
public class Test {
    public static void main(String[] args) {
        List<Link> links=new ArrayList<>();
        links.add(new Link(0,1,5d));
        links.add(new Link(0,2,1d));
        links.add(new Link(2,1,2d));
        Graph g = new Graph(3,3,links);
        Solution solution=new PathFinder().findShortestPath(g,0,1);
        System.out.println(solution.length);
        System.out.println(solution.length);

    }
}
