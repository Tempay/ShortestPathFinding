import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 远志 on 12/22/2016.
 */
public class Test {

    public static void main(String[] args) throws IOException{
        List<Link> links= new BriteReader().loadTopo("1.brite");
        //links.add(new Link(0,1,5d));
        //links.add(new Link(0,2,1d));
        //links.add(new Link(2,1,2d));
        Graph g = new Graph(20,10,links);
        Solution solution=new PathFinder().findShortestPath(g,0,8);

        System.out.println(solution.length);
        System.out.println(solution.length);

    }
}
