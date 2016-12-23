import java.util.*;

/**
 * Created by 远志 on 12/22/2016.
 */
public class PathFinder {
    public Solution findShortestPath(Graph graph,int source, int target) {

        if(false){
            //wrong input;
        }

        List<Node> nodes=connectLinksToNodes(graph);


        return findShortestPath(nodes,source,target);
    }

    private Solution findShortestPath(List<Node> nodes,int source,int target) {
        Solution solution=new Solution(new ArrayList<>(),Integer.MAX_VALUE);
        int length=0;
        Map<Integer,Solution> map = new HashMap<>();
        map.put(target,new Solution(new ArrayList<>(),0));
        Set<Integer> path=new HashSet<>();
        path.add(source);
        return helper(nodes,source,target,map,path);


    }

    private Solution helper(List<Node> nodes, int source, int target, Map<Integer, Solution> map, Set<Integer> path) {
        if(map.containsKey(source)) {
            return map.get(source);
        }
        Solution solution=new Solution(new ArrayList<>(),Integer.MAX_VALUE);
        for(Connection connection : nodes.get(source).connections) {

            int newSource=connection.id;
            if(path.contains(newSource)){
                continue;
            }
            Set<Integer> newPath=new HashSet<>(path);
            newPath.add(newSource);
            Solution newSolution = helper(nodes,newSource,target,map, newPath);
            newSolution.length+=connection.length;
            if(newSolution.length<solution.length) {
                solution.length=newSolution.length;
                solution.path=newSolution.path;
            }
        }
        map.put(source,solution);
        return solution;
    }



    private List<Node> connectLinksToNodes(Graph graph) {
        List<Node> nodes =new ArrayList<>();
        int numOfNodes=graph.numOfNodes;
        int numOfLinks=graph.numOfLinks;
        for (int i = 0; i < numOfNodes; i++) {
            nodes.add(new Node(i));
        }
        for (int i = 0; i <numOfLinks ; i++) {
            nodes.get(graph.links.get(i).node1).addConnection(graph.links.get(i).node2,graph.links.get(i).length);
            nodes.get(graph.links.get(i).node2).addConnection(graph.links.get(i).node1,graph.links.get(i).length);
        }

        return nodes;
    }
}
