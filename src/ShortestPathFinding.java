import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 远志 on 12/23/2016.
 */
public class ShortestPathFinding {
    public static void main(String[] args) throws IOException {



        if(args[0].equals("braessWithoutAdditionalLink")){;
            doBraess1();
            return;
        }
        if(args[0].equals("braessWithAdditionalLink")){;
            doBraess2();
            return;
        }
        if (args.length != 4 || !(args[1].equals("congested") || args[1].equals("noncongested"))) {
            System.out.println("Correct command: java ShortestPathFinding  {.brite file name}  {congested/noncongested}  {src node}  {dst node}");
            return;
        }
        BriteReader reader=new BriteReader();
        List<Link> links= reader.loadTopo(args[0]);


        int source, target;

        try {
            source=Integer.parseInt(args[2]);
            target=Integer.parseInt(args[3]);
        } catch (Exception e) {
            System.out.println("Correct command: java ShortestPathFinding  {.brite file name}  {congested/noncongested}  {src node}  {dst node}");
            return;
        }

        int numOfNodes=reader.numberOfNodes;
        int numOfLinks=reader.numberOfLinks;

        Graph g = new Graph(numOfLinks,numOfNodes,links);
        List<Node> nodes=new PathFinder().connectLinksToNodes(g);
        Solution solution1;

        if(args[1].equals("noncongested")) {
            solution1 = new CongestedPathFinder().findShortestPath(nodes, source, target);
            System.out.println("the shortest path contains the following link ");
            for(Integer integer:solution1.path) {
                System.out.print(String.valueOf(integer)+",");
            }
        }
        else {
            Map<Set<Integer>,Integer> solution2=new CongestedPathFinder().findCongestedShortedPath(g,source,target);
            System.out.println("the best solution contains (denoted by link ids)"+String.valueOf(solution2.size())+" path");
            for(Map.Entry<Set<Integer>,Integer> entry : solution2.entrySet()) {
                System.out.print("path:");
                for(Integer integer:entry.getKey()) {
                    System.out.print(String.valueOf(integer)+",");
                }
                System.out.print("with a probability "+ String.valueOf(entry.getValue()) +"%\n");
            }
        }


    }

    private static void doBraess2() {
        List<Link> links=new ArrayList<>();
        links.add(new Link(0,1,0d,true));
        links.add(new Link(0,3,1000d,false));
        links.add(new Link(2,1,1000d,false));
        links.add(new Link(2,3,0d,true));
        links.add(new Link(1,3,0d,false));
        Graph g = new Graph(5,4,links);
        Map<Set<Integer>,Integer> solution2=new CongestedPathFinder().findCongestedShortedPath(g,0,2);
        System.out.println("the best solution contains "+String.valueOf(solution2.size())+" path");
        for(Map.Entry<Set<Integer>,Integer> entry : solution2.entrySet()) {
            System.out.print("path:");
            for(Integer integer:entry.getKey()) {
                System.out.print(String.valueOf(integer)+",");
            }
            System.out.print("with a probability "+ String.valueOf(entry.getValue()) +"%\n");
        }
    }

    private static void doBraess1() {
        List<Link> links=new ArrayList<>();
        links.add(new Link(0,1,0d,true));
        links.add(new Link(0,3,1000d,false));
        links.add(new Link(2,1,1000d,false));
        links.add(new Link(2,3,0d,true));
        Graph g = new Graph(4,4,links);
        Map<Set<Integer>,Integer> solution2=new CongestedPathFinder().findCongestedShortedPath(g,0,2);
        System.out.println("the best solution contains "+String.valueOf(solution2.size())+" path");
        for(Map.Entry<Set<Integer>,Integer> entry : solution2.entrySet()) {
            System.out.print("path:");
            for(Integer integer:entry.getKey()) {
                System.out.print(String.valueOf(integer)+",");
            }
            System.out.print("with a probability "+ String.valueOf(entry.getValue()) +"%\n");
        }
    }
}
